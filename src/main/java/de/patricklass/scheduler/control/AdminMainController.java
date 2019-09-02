package de.patricklass.scheduler.control;

import de.patricklass.scheduler.model.Group;
import de.patricklass.scheduler.model.User;
import de.patricklass.scheduler.repository.GroupRepository;
import de.patricklass.scheduler.repository.InvitationRepository;
import de.patricklass.scheduler.repository.UserRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

/**
 * Controller for the admin main page. Handles users and groups.
 * @author Jens
 */

@Controller
public class AdminMainController {

    @FXML
    private BorderPane adminMainBorderPane = new BorderPane();

    @FXML
    private TableView<User> adminUserTableView = new TableView<>();

    @FXML
    private TableView<Group> adminGroupTableView = new TableView<>();

    @FXML
    private TableColumn<User, String> adminUserColumn = new TableColumn<>();

    @FXML
    private TableColumn<Group, String> adminGroupColumn = new TableColumn<>();

    @FXML
    private Button addUserButton = new Button();

    @FXML
    private Button delUserButton = new Button();

    @FXML
    private Button delGroupButton = new Button();

    @FXML
    private Button createGroupButton = new Button();

    private SceneManager sceneManager;

    private GroupRepository groupRepository;

    private UserRepository userRepository;
    private InvitationRepository invitationRepository;


    public AdminMainController(SceneManager sceneManager, GroupRepository groupRepository, UserRepository userRepository, InvitationRepository invitationRepository) {
        this.sceneManager = sceneManager;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.invitationRepository = invitationRepository;
    }

    @FXML
    private void initialize() {
        createGroupButton.setOnAction((event) -> {
            sceneManager.showScene("createGroup");
        });

        delGroupButton.setOnAction((event -> {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);

            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Möchten Sie die Gruppe wirklich löschen?"));
            Button yesButton = new Button("Ja");
            yesButton.setOnAction((event1 -> {
                dialog.close();

                //get the selcted group and remove it from the table and repository
                Group selectedGroup = adminGroupTableView.getSelectionModel().getSelectedItem();
                adminGroupTableView.getItems().remove(selectedGroup);
                groupRepository.delete(selectedGroup);
            }));
            Button noButton = new Button("Nein");
            noButton.setOnAction(event1 -> {
                dialog.close();
            });
            dialogVbox.getChildren().add(yesButton);
            dialogVbox.getChildren().add(noButton);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }));

        delUserButton.setOnAction((event -> {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);

            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Möchten Sie den Nutzer wirklich löschen?"));
            Button yesButton = new Button("Ja");
            yesButton.setOnAction((event1 -> {
                dialog.close();
                //get the selected User and send him to hell er.. delete him
                User selectedUser = adminUserTableView.getSelectionModel().getSelectedItem();
                groupRepository.findAllByUsersContains(selectedUser).forEach(group -> {
                    group.getUsers().remove(selectedUser);
                    group.getInvitations().forEach(invitation -> {
                        invitation.getStatusMap().remove(selectedUser);
                        invitationRepository.save(invitation);
                    });
                    groupRepository.save(group);
                });
                adminUserTableView.getItems().remove(selectedUser);
                userRepository.delete(selectedUser);
            }));
            Button noButton = new Button("Nein");
            noButton.setOnAction(event1 -> {
                dialog.close();
            });

            dialogVbox.getChildren().add(yesButton);
            dialogVbox.getChildren().add(noButton);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }));

        addUserButton.setOnAction((event -> {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);

            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Nutzer erfolgreich zu Gruppe hinzugefügt"));

            //Add the selected User put him into the selected Group. Then save the group
            User selectedUser = adminUserTableView.getSelectionModel().getSelectedItem();
            Group selectedGroup = adminGroupTableView.getSelectionModel().getSelectedItem();
            selectedGroup.getUsers().add(selectedUser);
            groupRepository.save(selectedGroup);

            Button okButton = new Button("OK");
            okButton.setOnAction((event1 -> {
                dialog.close();
            }));

            dialogVbox.getChildren().add(okButton);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();


        }));

        adminUserColumn.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getUserName() + (data.getValue().isAdmin() ? " (admin)" : "")
        ));
        adminGroupColumn.setCellValueFactory(new PropertyValueFactory<>("groupName"));
    }

    public void loadTables(){
        adminGroupTableView.getItems().addAll(groupRepository.findAll());
        adminUserTableView.getItems().addAll(userRepository.findAll());
    }


}

