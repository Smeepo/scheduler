package de.patricklass.scheduler;

import de.patricklass.scheduler.control.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static de.patricklass.scheduler.control.SceneManager.*;

/**
 * Starter for JavaFX using Spring
 * @author Patrick Laß
 * @author minh
 * @author jens
 */
@SpringBootApplication
@ComponentScan(basePackages = {"de.patricklass.scheduler.control","de.patricklass.scheduler.model","de.patricklass.scheduler.repository","de.patricklass.scheduler.service"})
public class SchedulerApplication extends Application {
    private ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        launch(args);
    }

    private static SceneManager sceneManager;

    private final Logger LOGGER = LoggerFactory.getLogger(SchedulerApplication.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneManager = new SceneManager(primaryStage);
        springContext = SpringApplication.run(SchedulerApplication.class);

        Map<String, String> fxmlMap = new HashMap<>();
        fxmlMap.put(ADMIN_GROUP_OVERVIEW, "/fxml/adminGroupOverview.fxml");
        fxmlMap.put(CREATE_GROUP, "/fxml/adminMainCreateGroup.fxml");
        fxmlMap.put(ADMIN_MAIN, "/fxml/adminMain.fxml");
        fxmlMap.put(USER_VIEW, "/fxml/userView.fxml");
        fxmlMap.put(INVITATION_VIEW, "/fxml/invitationView.fxml");
        fxmlMap.put(ADMIN_CREATE_EVENT, "/fxml/adminCreateEvent.fxml");

        fxmlMap.forEach((identifier, path) -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(springContext::getBean);
            fxmlLoader.setLocation(getClass().getResource(path));
            try {
                Parent rootNode = fxmlLoader.load();
                //gives every Scene the same size
                sceneManager.addScene(identifier, new Scene(rootNode,800,600));
            } catch (IOException | IllegalStateException e) {
                LOGGER.error("Location for Scene \""+identifier+"\" may not be correct:");
                LOGGER.error(e.getMessage());
            }
        });

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        fxmlLoader.setLocation(getClass().getResource("/fxml/login.fxml"));
        Parent adminPopupNode = fxmlLoader.load();

        primaryStage.setTitle("DND Scheduler ALPHA v0.1");
        //Custom CSS is inserted here
        Scene loginScene = new Scene(adminPopupNode, 800, 600);
        loginScene.getStylesheets().addAll(this.getClass().getResource("/css/style.css").toExternalForm());

        sceneManager.addScene(LOGIN, loginScene);
        sceneManager.showScene(LOGIN);
        primaryStage.show();
    }

    @Bean
    public SceneManager getSceneManager() {
        return sceneManager;
    }

    @Override
    public void stop() {
        springContext.stop();
    }
}
