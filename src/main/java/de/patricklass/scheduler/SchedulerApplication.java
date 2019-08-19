package de.patricklass.scheduler;

import de.patricklass.scheduler.control.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Starter for JavaFX using Spring
 * @author minh
 */
@SpringBootApplication
public class SchedulerApplication extends Application {
    private ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        launch(args);
    }

    private static SceneManager sceneManager;

    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneManager = new SceneManager(primaryStage);
        springContext = SpringApplication.run(SchedulerApplication.class);

        Map<String, String> fxmlMap = new HashMap<>();
        fxmlMap.put("groupOverview", "/fxml/adminGroupOverview.fxml");
        fxmlMap.put("createGroup", "/fxml/adminMainCreateGroup.fxml");
        fxmlMap.put("adminMain", "/fxml/adminMain.fxml");
        fxmlMap.put("userView", "/fxml/userView.fxml");
        fxmlMap.put("invitationView", "/fxml/invitationView.fxml");
        fxmlMap.put("adminCreateEvent", "/fxml/popupAdminEvent.fxml");

        fxmlMap.forEach((identifier, path) -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(springContext::getBean);
            fxmlLoader.setLocation(getClass().getResource(path));
            Parent adminOverviewNode = null;
            try {
                adminOverviewNode = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sceneManager.addScene(identifier, new Scene(adminOverviewNode));
        });

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        fxmlLoader.setLocation(getClass().getResource("/fxml/login.fxml"));
        Parent adminPopupNode = fxmlLoader.load();

        primaryStage.setTitle("DND Scheduler ALPHA v0.1");
        //Custom CSS is inserted here
        Scene loginScene = new Scene(adminPopupNode, 800, 600);
        loginScene.getStylesheets().addAll(this.getClass().getResource("/css/style.css").toExternalForm());

        sceneManager.addScene("login", loginScene);
        sceneManager.showScene("login");
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
