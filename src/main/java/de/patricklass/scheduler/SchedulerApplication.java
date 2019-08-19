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

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        fxmlLoader.setLocation(getClass().getResource("/fxml/login.fxml"));
        Parent rootNode = fxmlLoader.load();

        primaryStage.setTitle("DND Scheduler ALPHA v0.1");
        //Custom CSS is inserted here
        Scene loginScene = new Scene(rootNode, 800, 600);
        loginScene.getStylesheets().addAll(this.getClass().getResource("/css/style.css").toExternalForm());

        //TEST
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        fxmlLoader.setLocation(getClass().getResource("/fxml/adminMain.fxml"));
        Parent adminMainNode = fxmlLoader.load();
        Scene adminMainScene = new Scene(adminMainNode, 800, 600);
        sceneManager.addScene("adminMain", adminMainScene);

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
