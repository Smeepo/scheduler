package de.patricklass.scheduler;

import de.patricklass.scheduler.control.SceneManager;
import de.patricklass.scheduler.model.Group;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class AdminMainCreateGroup {


    @FXML
    private TextField adminMainCreateGroupNameTextfield = new TextField();

    @FXML
    private TextArea adminMainCreateGroupDescriptionTextfield = new TextArea();

    @FXML
    private Button adminMainCreateGroupConfirmButton = new Button();

    private SceneManager sceneManager;

    public AdminMainCreateGroup(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    private void initialize(){
        adminMainCreateGroupConfirmButton.setOnAction((event -> {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);

            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Gruppe erfolgreich angelegt"));
            Button okButton = new Button("OK");
            okButton.setOnAction((event1 -> {
                dialog.close();
                sceneManager.showLastScene();

                //TODO add Group to database
            }));
            dialogVbox.getChildren().add(okButton);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }));

    }
}
