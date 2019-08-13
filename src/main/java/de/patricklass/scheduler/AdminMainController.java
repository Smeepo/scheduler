package de.patricklass.scheduler;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

@Controller
public class AdminMainController {


    @FXML
    private BorderPane adminMainBorderPane = new BorderPane();

    @FXML
    private TableView adminUserTableView = new TableView();

    @FXML
    private TableView adminGroupTableView = new TableView();

    @FXML
    private TableColumn adminUserColumn = new TableColumn();

    @FXML
    private TableColumn adminGroupColumn = new TableColumn();

    @FXML
    private Button addUserButton = new Button();

    @FXML
    private Button delUserButton = new Button();

    @FXML
    private Button delGroupButton = new Button();

    @FXML
    private Button createGroupButton = new Button();

    @FXML
    private void initialize() {
        createGroupButton.setOnAction((event) -> {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/adminMainCreateGroupPopUp.fxml"));
            Parent rootNode = null;
            try {
                rootNode = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SchedulerApplication.getPrimaryStage().setScene(new Scene(rootNode));
            SchedulerApplication.getPrimaryStage().show();
        });
    }





}

