package de.patricklass.scheduler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

/**
 *
 * @author minh
 */
@Controller
public class userViewController {
    private ArrayList<String> stringArrayList = new ArrayList<>();

    @FXML
    private ListView<String> eventListView;
    @FXML
    private Accordion groupAccordion;

    @FXML
    private Button btnLogout;

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
    }
}
