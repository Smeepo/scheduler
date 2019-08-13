package de.patricklass.scheduler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SchedulerApplication extends Application {

    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SchedulerApplication.primaryStage = primaryStage;

        ConfigurableApplicationContext springContext = SpringApplication.run(SchedulerApplication.class);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/adminMain.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);

        Parent rootNode = fxmlLoader.load();
        primaryStage.setScene(new Scene(rootNode));
        primaryStage.show();
    }




}


