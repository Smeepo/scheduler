package de.patricklass.scheduler.control;

import de.patricklass.scheduler.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;


/**
 * Called by AdminGroupOverview
 * Controller for viewing events and sending out invitations for those events.
 * GUI displays date and all users that are subscribed to that event
 * and their invitation status(ACCEPTED,DECLINED, NOT ANSWERED)
 * @author Minh
 */
@Controller
public class InvitationViewController {

    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private User user;

    @FXML
    private TextField dateField;

    @FXML
    private TableView memberTable;


    @FXML
    private TableColumn<User,String> statusColumn = new TableColumn<>();

    @FXML
    private TableColumn<User, String> userColumn = new TableColumn<>();


    @FXML
    public void initialize(){
    }

    // Constructor
    public InvitationViewController(User user){
        this.user = user;
    }

    // Init method that is called before scene is shown
    public void initView(){
        memberTable.getItems().add(user);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));

        // @ToDo get invitation date
        dateField.setPromptText("placeholder");
    }

    // Init method
    public void sendInv(){
        LOGGER.info("Torture squad sent");
    }



    public void cancel(){
        LOGGER.info("closed");
    }



}
