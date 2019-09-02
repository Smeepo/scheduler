package de.patricklass.scheduler.control;

import de.patricklass.scheduler.model.Group;
import de.patricklass.scheduler.model.Invitation;
import de.patricklass.scheduler.model.InvitationStatus;
import de.patricklass.scheduler.model.User;
import de.patricklass.scheduler.repository.GroupRepository;
import de.patricklass.scheduler.service.LoginService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;


/**
 * User's view after logging in. Shows user's groups and events/invitations.
 * @author minh
 */
@Controller
public class UserViewController {


    private GroupRepository groupRepository;

    private LoginService loginService;

    private SceneManager sceneManager;

    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private Accordion groupAccordion;

    @FXML
    private ListView<String> invitationListView;

    @FXML
    private TableView<Invitation> invitationTableView = new TableView<>();

    @FXML
    private TableColumn<Invitation, String> userInvDateColumn = new TableColumn<>();



    @FXML
    public void initialize() {
        invitationTableView.setPlaceholder(new Label("Du hast noch keinen Terminen zugesagt"));
    }

    public UserViewController(GroupRepository groupRepository, @Qualifier("loginService-local") LoginService loginService, SceneManager sceneManager) {
        this.groupRepository = groupRepository;
        this.loginService = loginService;
        this.sceneManager = sceneManager;
    }

    /**
     *  Load all groups the current authenticated user is part of and create TitledPanes with content accordingly
     *  Content that is created dynamically: Buttons and action listener, description TextArea, ListView of users who have accepted
     *  Buttons add or remove invites from the invitationTableView
     */
    public void initView() {
        // Initialize TableColumn
        userInvDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        List<Group> groupList = groupRepository.findAllByUsersContains(loginService.getAuthenticatedUser());
        groupAccordion.getPanes().clear();
        invitationTableView.getItems().clear();
        for (Group group : groupList) {
            // Logging
            LOGGER.info("Currently in group:" + group.getGroupName());
            VBox invitationBox = new VBox(10);
            TextArea descArea = new TextArea();
            ListView<User> acceptedUsers = new ListView<>();

            // Get all invitations of the group
            for (Invitation invitation : group.getInvitations()) {
                HBox hbox = new HBox(25);
                Button accept = new Button("Accept");
                accept.setOnAction(event -> {
                    invitation.getStatusMap().put(loginService.getAuthenticatedUser(), InvitationStatus.ACCEPTED);
                    updateTableView(invitation,false);
                    // Logging
                    System.out.println(invitation.toString());
                });
                Button decline = new Button("Decline");
                decline.setOnAction(event -> {
                    invitation.getStatusMap().put(loginService.getAuthenticatedUser(), InvitationStatus.DECLINED);
                    updateTableView(invitation,true);
                    // Logging
                    System.out.println(invitation.toString());
                });

                // Add buttons
                hbox.getChildren().addAll(accept, decline);

                // Add description area
                descArea.setText(invitation.getDescription());
                descArea.setEditable(false);
                descArea.setWrapText(true);

                // Add list of members who have already accepted
                acceptedUsers.getItems().addAll(
                        invitation
                                .getStatusMap()
                                .keySet()
                                .stream()
                                .filter(key -> invitation.getStatusMap().get(key).equals(InvitationStatus.ACCEPTED))
                                .collect(Collectors.toList())
                );
                acceptedUsers.setPrefHeight(acceptedUsers.getItems().size() * 29.5);


                // Add items to the VBox
                invitationBox.getChildren().add(new Label("Termin: " + invitation.getName()));
                invitationBox.getChildren().add(new Label("Vorgeschlagenes Datum: " + invitation.getDate().toString()));
                invitationBox.getChildren().add(hbox);
                invitationBox.getChildren().add(descArea);

                // Hide this box if it's empty
                if(!acceptedUsers.getItems().isEmpty()){
                    invitationBox.getChildren().add(new Label("Diese Leute haben schon zugesagt:"));
                    invitationBox.getChildren().add(acceptedUsers);
                }


                //Logging
                LOGGER.info(group.getGroupName() + " Invitations: " + invitation.getDate());


            }
            // Add generated Panes to the accordion
            groupAccordion.getPanes().add(new TitledPane(group.getGroupName(), invitationBox));
        }


    }

//    public void initTableView() {
//        userInvDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
//    }


    /**
     * Add or remove accepted or declined invitation to the tableView and
     * prevent multiple additions or removals of the same invitation to the tableView
     *
     * @param invitation
     * @param shouldDelete boolean that indicates if the invitation should be added or removed
     *                     shouldDelete == true -> delete invitation
     *                     shouldDelete == false -> add invitation
     */
    private void updateTableView(Invitation invitation, boolean shouldDelete) {
        // Check if invitation already exists
        boolean hasInv = invitationTableView.getItems().contains(invitation);

        if (!hasInv && !shouldDelete) {
            // Fill tableView with the accepted invitation
            invitationTableView.getItems().add(invitation);
        }else if (hasInv && shouldDelete) {
            // Remove declined invitation from tableView
            invitationTableView.getItems().remove(invitation);
        }
    }


    public void logout() {
        //log out
        System.out.println("attempting to log out");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Sie wurden ausgeloggt");
        alert.showAndWait();

        loginService.logout();
        sceneManager.showScene(SceneManager.LOGIN);
    }
}
