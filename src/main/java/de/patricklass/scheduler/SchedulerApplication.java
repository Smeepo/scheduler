package de.patricklass.scheduler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Starter for JavaFX using Spring
 *
 * @author minh
 */
@SpringBootApplication
public class SchedulerApplication extends Application {
    private ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        springContext = SpringApplication.run(SchedulerApplication.class);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);

        fxmlLoader.setLocation(getClass().getResource("/fxml/invitationView.fxml"));
        Parent rootNode = fxmlLoader.load();

        primaryStage.setTitle("DND Scheduler ALPHA v0.1");
        Scene scene = new Scene(rootNode, 800, 600);
        //Custom CSS is inserted here
        scene.getStylesheets().addAll(this.getClass().getResource("/css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        springContext.stop();
    }
}
