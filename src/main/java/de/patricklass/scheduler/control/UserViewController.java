package de.patricklass.scheduler.control;

import de.patricklass.scheduler.model.Group;
import de.patricklass.scheduler.model.Invitation;
import de.patricklass.scheduler.model.InvitationStatus;
import de.patricklass.scheduler.repository.GroupRepository;
import de.patricklass.scheduler.service.LoginService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.*;

/**
 * User's view after logging in. Shows user's groups and events/invitations.
 *
 * @author minh
 */
@Controller
public class UserViewController {
    private ArrayList<String> stringArrayList = new ArrayList<>();

    private GroupRepository groupRepository;

    private LoginService loginService;

    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private Accordion groupAccordion;

    @FXML
    private ListView<String> invitationListView;

    @FXML
    public void initialize() {
    }

    public UserViewController(GroupRepository groupRepository, @Qualifier("loginService-local") LoginService loginService) {
        this.groupRepository = groupRepository;
        this.loginService = loginService;
    }

    public void initView() {
        // Load all groups the current authenticated user is part of and create TitledPanes with content accordingly
        List<Group> groupList = groupRepository.findAllByUsersContains(loginService.getAuthenticatedUser());

        for (Group i : groupList) {
            // Logging
            LOGGER.info("Currently in group:" + i.getGroupName());
            VBox invitationBox = new VBox();


            // Get all invitations of the group
            for (Invitation invitation : i.getInvitations()) {
                HBox hbox = new HBox(20);
                Button accept = new Button("Accept");
                accept.setOnAction(event -> {
                    invitation.getStatusMap().put(loginService.getAuthenticatedUser(), InvitationStatus.ACCEPTED);
                    LOGGER.debug(invitation.toString());
                    System.out.println(invitation.toString());
                });
                Button decline = new Button("Decline");
                decline.setOnAction(event -> {
                    invitation.getStatusMap().put(loginService.getAuthenticatedUser(), InvitationStatus.DECLINED);
                    LOGGER.debug(invitation.toString());
                });

                hbox.getChildren().add(new Label(invitation.getDate().toString()));

                hbox.getChildren().addAll(accept, decline);
                invitationBox.getChildren().add(hbox);


                //Logging
                LOGGER.info(i.getGroupName() + " Invitations: " + invitation.getDate());

                // Add the invitation to the invitationListView
                stringArrayList.add(invitation.getDate().toString());
            }

            groupAccordion.getPanes().add(new TitledPane(i.getGroupName(), invitationBox));
        }

        // Fill the observableList with all invitations and sort it
        Collections.sort(stringArrayList);
        invitationListView.getItems().addAll(stringArrayList);
    }


    public void logout() {
        //log out
        System.out.println("attempting to log out");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("You just pressed the logout button");

        alert.showAndWait();
    }
}
