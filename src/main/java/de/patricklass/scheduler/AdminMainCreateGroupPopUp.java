package de.patricklass.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

@Controller
public class AdminMainCreateGroupPopUp {


    @FXML
    private TextField adminMainCreateGroupNameTextfield = new TextField();

    @FXML
    private TextArea adminMainCreateGroupDescriptionTextfield = new TextArea();
}
