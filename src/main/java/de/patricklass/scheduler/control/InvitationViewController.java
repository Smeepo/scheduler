package de.patricklass.scheduler.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;


/**
 * Called from AdminGroupOverview
 * Controller for viewing events and sending out invitations for those events.
 * GUI displays date and all users that are subscribed to that event
 * and their invitation status(ACCEPTED,DECLINED, NOT ANSWERED)
 * @author Minh
 */
@Controller
public class InvitationViewController {

    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private Button btnSendInv;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField dateField;

    @FXML
    private TableView memberTable;

    @FXML
    public void initialize(){
        System.out.println("Initialized");
        //init TableColumns
    }

    public void sendInv(){
        LOGGER.info("Torture squad sent");
    }

    public void cancel(){
        LOGGER.info("closed");
    }



}
