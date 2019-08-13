package de.patricklass.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Controller;

@Controller
public class PopupAdminEventController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnCreate;


    public void createEvent(){
        //creates event
        System.out.println("event created");
    }

    public void cancel(){
        //close scene
        System.out.println("closed");
    }
}
