package de.patricklass.scheduler.control;

import de.patricklass.scheduler.model.Invitation;
import de.patricklass.scheduler.model.InvitationStatus;
import de.patricklass.scheduler.model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.Map;


/**
 * Called by AdminGroupOverview
 * Controller for viewing events and sending out invitations for those events.
 * GUI displays date and all users that are subscribed to that event
 * and their invitation status(ACCEPTED,DECLINED, NOT ANSWERED)
 *
 * @author Minh
 */
@Controller
public class InvitationViewController {

    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private SceneManager sceneManager;

    @FXML
    private Label dateLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private TableView<Map.Entry<User, InvitationStatus>> memberTable;


    @FXML
    private TableColumn<Map.Entry<User, InvitationStatus>, String> statusColumn = new TableColumn<>();

    @FXML
    private TableColumn<Map.Entry<User, InvitationStatus>, String> userColumn = new TableColumn<>();

    /**
     * Fills the tableview columns with usernames and invitation-statuses of all users in the group that was selected in the previous scene
     */
    @FXML
    public void initialize() {
        userColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getKey().getUserName()));
        statusColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getValue().toString()));
    }

    // Constructor
    public InvitationViewController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    // Init method that is called before scene is shown
    public void initView() {
    }

    /**
     * Called by GroupAdminOverview
     * Loads all users and their statuses into the tableView
     *
     * @param invitation that was chosen in the previous scene
     */
    public void loadForInvitation(Invitation invitation) {
        nameLabel.setText(invitation.getName());
        dateLabel.setText(invitation.getDate().toString());
        memberTable.getItems().clear();
        memberTable.getItems().addAll(invitation.getStatusMap().entrySet());

        memberTable.setPrefHeight(memberTable.getItems().size() * 29.5);
    }

    public void goBack() {
        sceneManager.showLastScene();
    }


}
