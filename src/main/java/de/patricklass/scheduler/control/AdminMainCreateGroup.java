package de.patricklass.scheduler.control;

import de.patricklass.scheduler.model.Group;
import de.patricklass.scheduler.repository.GroupRepository;
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

/**
 * Controller for the admin create a group window
 * @author jens
 */

@Controller
public class AdminMainCreateGroup {


    @FXML
    private TextField adminMainCreateGroupNameTextfield = new TextField();

    @FXML
    private TextArea adminMainCreateGroupDescriptionTextfield = new TextArea();

    @FXML
    private Button adminMainCreateGroupConfirmButton = new Button();

    @FXML
    private Button adminMainCreateGroupCancelButton = new Button();

    private SceneManager sceneManager;

    private GroupRepository groupRepository;
    private AdminMainController adminMainController;

    public AdminMainCreateGroup(SceneManager sceneManager, GroupRepository groupRepository, AdminMainController adminMainController) {
        this.sceneManager = sceneManager;
        this.groupRepository = groupRepository;
        this.adminMainController = adminMainController;
    }

    @FXML
    private void initialize(){
        adminMainCreateGroupConfirmButton.setOnAction((event -> {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);

            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Gruppe erfolgreich angelegt"));
            //create a Group
            groupRepository.save(new Group(adminMainCreateGroupNameTextfield.getText()));
            Button okButton = new Button("OK");
            okButton.setOnAction((event1 -> {
                dialog.close();
                adminMainController.loadTables();
                sceneManager.showLastScene();
            }));
            dialogVbox.getChildren().add(okButton);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }));

        adminMainCreateGroupCancelButton.setOnAction(event -> {
            sceneManager.showLastScene();
        });

    }
}
