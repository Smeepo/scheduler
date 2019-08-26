package de.patricklass.scheduler.control;

import de.patricklass.scheduler.control.SceneManager;
import de.patricklass.scheduler.model.Group;
import de.patricklass.scheduler.model.User;
import de.patricklass.scheduler.repository.GroupRepository;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;


@Controller
public class AdminGroupOverviewController {

    @FXML
    private Button adminGroupOverviewCreateInvitationButton = new Button();

    @FXML
    private TableView<User> adminGroupOverviewUserTableView = new TableView<User>();

    @FXML
    private TableView<?> adminGroupOverviewInvitationTableView = new TableView<>();

    @FXML
    private Button adminGroupOverviewRemoveUserButton = new Button();

    @FXML
    private Button adminGroupOverviewBackButton = new Button();

    @FXML
    private BorderPane adminGroupOverviewBorderPane = new BorderPane();

    @FXML
    private Button adminGroupOverviewDeleteInvitationButton = new Button();

    @FXML
    private TextField adminGroupOverviewNameTextfield = new TextField();

    @FXML
    private TableColumn<?, ?> adminGroupOverviewInvitationColumn = new TableColumn<>();

    @FXML
    private TableColumn<?, ?> adminGroupOverviewUserColumn = new TableColumn<>();


    private SceneManager sceneManager;

    private GroupRepository groupRepository;

    private Group group;

    public AdminGroupOverviewController(SceneManager sceneManager, GroupRepository groupRepository) {
        this.sceneManager = sceneManager;
        this.groupRepository = groupRepository;

    }

    @FXML
    private void initialize(){
        adminGroupOverviewDeleteInvitationButton.setOnAction((event -> {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);

            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Möchten Sie diesen Termin wirklich löschen?"));
            Button yesButton = new Button("Ja");
            yesButton.setOnAction((event1 -> {
                dialog.close();

                //TODO delete invitation from database
            }));
            Button noButton = new Button("Nein");
            yesButton.setOnAction((event1 -> {
                dialog.close();
            }));
            dialogVbox.getChildren().add(yesButton);
            dialogVbox.getChildren().add(noButton);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }));

        adminGroupOverviewRemoveUserButton.setOnAction((event -> {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);

            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Möchten Sie diesen Nutzer wirklich entfernen?"));
            Button yesButton = new Button("Ja");
            yesButton.setOnAction((event1 -> {
                dialog.close();

                User selectedUser = adminGroupOverviewUserTableView.getSelectionModel().getSelectedItem();
                adminGroupOverviewUserTableView.getItems().remove(selectedUser);
                group.getUsers().remove(selectedUser);
                groupRepository.save(group);

            }));
            Button noButton = new Button("Nein");
            yesButton.setOnAction((event1 -> {
                dialog.close();
            }));
            dialogVbox.getChildren().add(yesButton);
            dialogVbox.getChildren().add(noButton);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }));

        adminGroupOverviewBackButton.setOnAction(event -> {
            sceneManager.showLastScene();
        });
    }
}


