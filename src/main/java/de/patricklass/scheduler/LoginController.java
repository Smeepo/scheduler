package de.patricklass.scheduler;

import de.patricklass.scheduler.service.LoginService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.security.auth.login.CredentialException;

/**
 * Handles authentications and registrations
 * @author Minh
 */
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

    private final LoginService loginService;

    public LoginController(@Qualifier("loginService-offline") LoginService loginService) {
        this.loginService = loginService;
    }

    public void login(){
        try{
            loginService.login(userTextField.getText(), passwordField.getText());
        } catch (CredentialException e) {
            e.printStackTrace();
        }
        System.out.println("attempting login");
    }

    public void register(){
        System.out.println("attempting to register");
    }
}
