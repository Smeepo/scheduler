package de.patricklass.scheduler.control;

import de.patricklass.scheduler.control.SceneManager;
import de.patricklass.scheduler.model.User;
import de.patricklass.scheduler.service.LoginService;
import de.patricklass.scheduler.service.MockDataService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private SceneManager sceneManager;

    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    public LoginController(@Qualifier("loginService-local") LoginService loginService, SceneManager sceneManager, MockDataService mockDataService) {
        this.loginService = loginService;
        this.sceneManager = sceneManager;
        mockDataService.initRepositoryData();
    }

    /**
     *   Gets user input for "username" and "password" and checks whether he's an admin or not and redirects
     *   him accordingly
     */
    public void login(){
        try{
           if( loginService.login(userTextField.getText(), passwordField.getText()).isAdmin()){
               LOGGER.info("YOU'RE IN -- LOGGED IN AS ADMIN");
               sceneManager.showScene("adminMain");
           }else{
               LOGGER.info("ENTERING PLEB MODE");
               sceneManager.showScene("userView");
           };

        } catch (CredentialException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Authentication Error");
            alert.setHeaderText(null);
            alert.setContentText("Login failed");

            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void register(){
        LOGGER.info("attempting to register");
    }
}
