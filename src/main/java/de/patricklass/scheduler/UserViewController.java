package de.patricklass.scheduler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

/**
 *
 * @author minh
 */
@Controller
public class UserViewController {
    private ArrayList<String> stringArrayList = new ArrayList<>();

    @FXML
    private ListView<String> eventListView;
    @FXML
    private Accordion groupAccordion;

    @FXML
    private Button btnLogout;


    //should be generated dynamically based on amount of events/dates
    @FXML
    private Button btnAccept;

    //should be generated dynamically based on amount of events/dates
    @FXML
    private Button btnDecline;

    //should be generated dynamically based on amount of groups
    @FXML
    private TitledPane titledPaneOne;

    @FXML
    public void initialize() {
        stringArrayList.add("hallo");
        stringArrayList.add("wie geht's?");
        stringArrayList.add("This is a placeholder event");
        // Pane should get name of a group
        titledPaneOne.setText("PLACEHOLDER GROUP");
        ObservableList<String> listViewContents =  FXCollections.observableList(stringArrayList);
        eventListView.setItems(listViewContents);
    }

    public void logout(){
        //log out
        System.out.println("attempting to log out");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("You just pressed the logout button");

        alert.showAndWait();
    }
}
