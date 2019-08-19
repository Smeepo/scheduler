package de.patricklass.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;


import java.sql.SQLOutput;

@Controller
public class InvitationViewController {

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
        System.out.println("Torture squad sent");
    }

    public void cancel(){
        System.out.println("closed");
    }



}
