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
        fxmlLoader.setLocation(getClass().getResource("/fxml/adminMainCreateGroup.fxml"));
        Parent adminNode = fxmlLoader.load();
        sceneManager.addScene("createGroup", new Scene(adminNode));

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        fxmlLoader.setLocation(getClass().getResource("/fxml/adminGroupOverview.fxml"));
        Parent adminOverviewNode = fxmlLoader.load();
        sceneManager.addScene("groupOverview", new Scene(adminOverviewNode));

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        fxmlLoader.setLocation(getClass().getResource("/fxml/adminMain.fxml"));
        Parent adminMainNode = fxmlLoader.load();
        sceneManager.addScene("adminMain", new Scene(adminMainNode));

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        fxmlLoader.setLocation(getClass().getResource("/fxml/userView.fxml"));
        Parent userViewNode = fxmlLoader.load();
        sceneManager.addScene("userView", new Scene(userViewNode));

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        fxmlLoader.setLocation(getClass().getResource("/fxml/invitationView.fxml"));
        Parent invitationViewNode = fxmlLoader.load();
        sceneManager.addScene("invitationView", new Scene(invitationViewNode));

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        fxmlLoader.setLocation(getClass().getResource("/fxml/popupAdminEvent.fxml"));
        Parent adminCreateEventNode = fxmlLoader.load();
        sceneManager.addScene("adminCreateEvent", new Scene(adminCreateEventNode));

        fxmlLoader = new FXMLLoader();
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
