package de.patricklass.scheduler.control;

import com.sun.xml.internal.bind.v2.TODO;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
//            SchedulerApplication.getPrimaryStage().setScene(new Scene(rootNode));
//            SchedulerApplication.getPrimaryStage().show();
        });

        delGroupButton.setOnAction((event -> {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            //dialog.initOwner(SchedulerApplication.getPrimaryStage());
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Möchten Sie die Gruppe wirklich löschen?"));
            Button yesButton = new Button("Ja");
            yesButton.setOnAction((event1 -> {
                dialog.close();
                //TODO delete group from database
            }));
            Button noButton = new Button("Nein");
            noButton.setOnAction(event1 -> {
                dialog.close();
            });
            dialogVbox.getChildren().add(yesButton);
            dialogVbox.getChildren().add(noButton);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }));

        delUserButton.setOnAction((event -> {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
//            dialog.initOwner(SchedulerApplication.getPrimaryStage());
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Möchten Sie den Nutzer wirklich löschen?"));
            Button yesButton = new Button("Ja");
            yesButton.setOnAction((event1 -> {
                dialog.close();
                //TODO delete user from database
            }));
            Button noButton = new Button("Nein");
            noButton.setOnAction(event1 -> {
                dialog.close();
            });

            dialogVbox.getChildren().add(yesButton);
            dialogVbox.getChildren().add(noButton);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }));

        addUserButton.setOnAction((event -> {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            //dialog.initOwner(SchedulerApplication.getPrimaryStage());
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Nutzer erfolgreich zu Gruppe hinzugefügt"));
            Button okButton = new Button("OK");
            okButton.setOnAction((event1 -> {
                dialog.close();
                //TODO add selected User to selected group
            }));

            dialogVbox.getChildren().add(okButton);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();


        }));


    }


}

