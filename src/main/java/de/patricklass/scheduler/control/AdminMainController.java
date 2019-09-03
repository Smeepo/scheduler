package de.patricklass.scheduler.control;

import de.patricklass.scheduler.model.Group;
import de.patricklass.scheduler.model.User;
import de.patricklass.scheduler.repository.GroupRepository;
import de.patricklass.scheduler.repository.InvitationRepository;
import de.patricklass.scheduler.repository.UserCredentialsRepository;
import de.patricklass.scheduler.repository.UserRepository;
import de.patricklass.scheduler.service.LoginService;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @FXML
    private Button toggleAdminButton = new Button();

    private SceneManager sceneManager;

    private GroupRepository groupRepository;

    private UserRepository userRepository;
    private InvitationRepository invitationRepository;
    private LoginService loginService;
    private AdminGroupOverviewController adminGroupOverviewController;
    private UserCredentialsRepository userCredentialsRepository;


    public AdminMainController(SceneManager sceneManager,
                               GroupRepository groupRepository,
                               UserRepository userRepository,
                               InvitationRepository invitationRepository,
                               @Qualifier("loginService-local") LoginService loginService,
                               AdminGroupOverviewController adminGroupOverviewController,
                               UserCredentialsRepository userCredentialsRepository) {
        this.sceneManager = sceneManager;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.invitationRepository = invitationRepository;
        this.loginService = loginService;
        this.adminGroupOverviewController = adminGroupOverviewController;
        this.userCredentialsRepository = userCredentialsRepository;
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
                userCredentialsRepository.removeByUserName(selectedUser.getUserName());
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
            loadTables();
            Button okButton = new Button("OK");
            okButton.setOnAction((event1 -> {
                dialog.close();
            }));

            dialogVbox.getChildren().add(okButton);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }));

        toggleAdminButton.setOnAction(event -> {
            User selectedUser = adminUserTableView.getSelectionModel().getSelectedItem();
            if (selectedUser == null || selectedUser.equals(loginService.getAuthenticatedUser())) return;
            selectedUser.setAdmin(!selectedUser.isAdmin());
            userRepository.save(selectedUser);
            loadTables();
        });

        adminUserColumn.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getUserName() + (data.getValue().isAdmin() ? " (admin)" : "")
        ));
        adminGroupColumn.setCellValueFactory(new PropertyValueFactory<>("groupName"));

        //Show ADMIN_GROUP_OVERVIEW on double click
        adminGroupTableView.setRowFactory( tv -> {
            TableRow<Group> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    adminGroupOverviewController.loadForGroup(row.getItem());
                    sceneManager.showScene(SceneManager.ADMIN_GROUP_OVERVIEW);
                }
            });
            return row ;
        });
    }

    public void loadTables(){
        adminGroupTableView.getItems().clear();
        adminGroupTableView.getItems().addAll(groupRepository.findAll());
        adminUserTableView.getItems().clear();
        adminUserTableView.getItems().addAll(userRepository.findAll());
    }


}

