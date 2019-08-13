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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ConfigurableApplicationContext springContext = SpringApplication.run(SchedulerApplication.class);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/adminGroupOverview.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);

        Parent rootNode = fxmlLoader.load();
        primaryStage.setScene(new Scene(rootNode));
        primaryStage.show();
    }


}


