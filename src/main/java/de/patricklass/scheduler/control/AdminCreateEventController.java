package de.patricklass.scheduler.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

/**
 * Called on AdminGroupOverview
 * Admin creates/updates events here
 * @author Minh
 */
@Controller
public class AdminCreateEventController {

    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private TextField groupField;

    @FXML
    private Button btnCancel;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField timeField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField nameField;

    @FXML
    private Button btnCreate;

    /**
     * groupField is populated by previous scene(AdminGroupOverView)
     */
    @FXML
    public void initialize(){
        LOGGER.info("Initialized AdminCreateEventController");
        nameField.setText("Lorem Ipsum");
        timeField.setText("Time as String?");
        dateField.setValue(LocalDate.now());
        descriptionArea.setText("Sample description");
        groupField.setText("Group name");
    }


    public void createEvent(){
        //creates event
        LOGGER.info("event created");
    }

    public void cancel(){
        //close scene
        LOGGER.info("closed");
    }
}
