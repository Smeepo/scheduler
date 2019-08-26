package de.patricklass.scheduler.control;

import de.patricklass.scheduler.control.SceneManager;
import de.patricklass.scheduler.service.LoginService;
import de.patricklass.scheduler.service.MockDataService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.security.auth.login.CredentialException;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

/**
 * Handles authentications and registrations
 * @author Minh
 */
@Controller
public class LoginController {

    @FXML
    private BorderPane loginPane;

    @FXML
    private TextField userTextField;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button btnRegister;

    private final LoginService loginService;

    private SceneManager sceneManager;

    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @FXML
    public void initialize(){
        loginPane.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if(keyCode.equals(KeyCode.ENTER)){
                login();
            }
        });
    }

    public LoginController(@Qualifier("loginService-local") LoginService loginService, SceneManager sceneManager, MockDataService mockDataService) {
        this.loginService = loginService;
        this.sceneManager = sceneManager;
        mockDataService.initRepositoryData();
    }


    public void login(){
        try{
            loginService.login(userTextField.getText(), passwordField.getText());

           if( loginService.login(userTextField.getText(), passwordField.getText()).isAdmin()){
               System.out.println("YOU'RE IN");
               sceneManager.showScene("adminMain");
           };

        } catch (CredentialException e) {
            e.printStackTrace();
        }





    }

    public void register(){
        System.out.println("attempting to register");
    }
}
