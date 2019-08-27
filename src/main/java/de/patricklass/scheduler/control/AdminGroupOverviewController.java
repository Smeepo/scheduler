package de.patricklass.scheduler.control;

import de.patricklass.scheduler.model.Group;
import de.patricklass.scheduler.model.Invitation;
import de.patricklass.scheduler.model.User;
import de.patricklass.scheduler.repository.GroupRepository;
import de.patricklass.scheduler.repository.InvitationRepository;
import de.patricklass.scheduler.service.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;


@Controller
public class AdminGroupOverviewController {


    @FXML
    private Button adminGroupOverviewCreateInvitationButton = new Button();

    @FXML
    private TableView<User> adminGroupOverviewUserTableView = new TableView<>();

    @FXML
    private TableView<Invitation> adminGroupOverviewInvitationTableView = new TableView<>();

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
    private TableColumn<Invitation, String> adminGroupOverviewInvitationColumn = new TableColumn<>();

    @FXML
    private TableColumn<User, String> adminGroupOverviewUserColumn = new TableColumn<>();

    @FXML
    private SceneManager sceneManager;
    private InvitationRepository invitationRepository;
    private GroupRepository groupRepository;
    private AdminCreateEventController adminCreateEventController;
    private LoginService loginService;

    private Group loadedGroup;

    public AdminGroupOverviewController(SceneManager sceneManager, InvitationRepository invitationRepository, GroupRepository groupRepository, AdminCreateEventController adminCreateEventController, @Qualifier("loginService-local") LoginService loginService) {
        this.sceneManager = sceneManager;
        this.invitationRepository = invitationRepository;
        this.groupRepository = groupRepository;
        this.adminCreateEventController = adminCreateEventController;
        this.loginService = loginService;
    }

    @FXML
    private void initialize(){

        initTableViews();

        adminGroupOverviewCreateInvitationButton.setOnAction((event -> {
            adminCreateEventController.loadForUser(loginService.getAuthenticatedUser());
            sceneManager.showScene("adminCreateEvent");
        }));

        adminGroupOverviewDeleteInvitationButton.setOnAction((event -> {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);

            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Möchten Sie diesen Termin wirklich löschen?"));
            Button yesButton = new Button("Ja");
            yesButton.setOnAction((event1 -> {
                Invitation selectedInvitation = adminGroupOverviewInvitationTableView.getSelectionModel().getSelectedItem();
                loadedGroup.getInvitations().remove(selectedInvitation);
                groupRepository.save(loadedGroup);
                invitationRepository.delete(selectedInvitation);
                adminGroupOverviewInvitationTableView.getItems().remove(selectedInvitation);
                dialog.close();
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
                User selectedUser = adminGroupOverviewUserTableView.getSelectionModel().getSelectedItem();
                loadedGroup.getUsers().remove(selectedUser);
                groupRepository.save(loadedGroup);
                adminGroupOverviewUserTableView.getItems().remove(selectedUser);
                dialog.close();
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

    private void initTableViews() {
        adminGroupOverviewInvitationColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        adminGroupOverviewUserColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
    }

    public void loadForGroup(Group group) {
        loadedGroup = group;
        adminGroupOverviewUserTableView.getItems().clear();
        adminGroupOverviewUserTableView.getItems().addAll(group.getUsers());
        adminGroupOverviewInvitationTableView.getItems().clear();
        adminGroupOverviewInvitationTableView.getItems().addAll(group.getInvitations());
    }
}


