package de.patricklass.scheduler.control;

import com.sun.xml.internal.bind.v2.TODO;
import de.patricklass.scheduler.control.SceneManager;
import de.patricklass.scheduler.model.Group;
import de.patricklass.scheduler.model.User;
import de.patricklass.scheduler.repository.GroupRepository;
import de.patricklass.scheduler.repository.UserRepository;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Controller for the admin main page. Handles users and groups.
 * @author Jens
 */

@Controller
public class AdminMainController {


    @FXML
    private BorderPane adminMainBorderPane = new BorderPane();

    @FXML
    private TableView<User> adminUserTableView = new TableView<User>();

    @FXML
    private TableView<Group> adminGroupTableView = new TableView<Group>();

    @FXML
    private TableColumn adminUserColumn = new TableColumn();

    @FXML
    private TableColumn adminGroupColumn = new TableColumn();

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


    public AdminMainController(SceneManager sceneManager, GroupRepository groupRepository, UserRepository userRepository) {
        this.sceneManager = sceneManager;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
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
                adminGroupTableView.getItems().remove(selectedUser);
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
    }


}

