package de.patricklass.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class PopupAdminEventController {


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

    @FXML
    public void initialize(){
        System.out.println("sdfdsfs");
        nameField.setText("Lorem Ipsum");
        timeField.setText("Time as String?");
        dateField.setValue(LocalDate.now());
        descriptionArea.setText("Sample description");
        groupField.setText("Group name");
    }


    public void createEvent(){
        //creates event
        System.out.println("event created");
    }

    public void cancel(){
        //close scene
        System.out.println("closed");
    }
}
