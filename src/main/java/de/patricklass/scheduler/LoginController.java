package de.patricklass.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
    @FXML
    private TextField userTextField;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button btnRegister;


    public void login(){
        //login
        System.out.println("attempting login");
    }

    public void register(){
        System.out.println("attempting to register");
    }
}
