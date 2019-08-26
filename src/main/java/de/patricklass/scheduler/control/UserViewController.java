package de.patricklass.scheduler.control;

import de.patricklass.scheduler.model.Group;
import de.patricklass.scheduler.model.Invitation;
import de.patricklass.scheduler.repository.GroupRepository;
import de.patricklass.scheduler.repository.InvitationRepository;
import de.patricklass.scheduler.service.LoginService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 *  User's view after logging in. Shows user's groups and events.
 * @author minh
 */
@Controller
public class UserViewController {
    private ArrayList<String> stringArrayList = new ArrayList<>();

    private GroupRepository groupRepository;

    private InvitationRepository invitationRepository;

    private LoginService loginService;

    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);


    @FXML
    private VBox groupBox;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Accordion groupAccordion;

    @FXML
    private ListView<String> eventListView;

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
        stringArrayList.add("test");
        stringArrayList.add("wie geht's?");
        stringArrayList.add("This is a placeholder event");
        // Pane should get name of a group
        titledPaneOne.setText("PLACEHOLDER GROUP");
        ObservableList<String> listViewContents =  FXCollections.observableList(stringArrayList);
        eventListView.setItems(listViewContents);

    }

    public UserViewController(GroupRepository groupRepository, InvitationRepository invitationRepository, @Qualifier("loginService-local") LoginService loginService){
        this.groupRepository = groupRepository;
        this.invitationRepository = invitationRepository;
        this.loginService = loginService;
    }

    public void logout(){
        //log out
        System.out.println("attempting to log out");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("You just pressed the logout button");

        alert.showAndWait();

        //loads all groups the user is part of
        // this function needs to be called when scene is loaded
        List<Group> test = groupRepository.findAllByUsersContains(loginService.getAuthenticatedUser());
        for(Group i : test){
            titledPaneOne.setText(i.getGroupName());
           LOGGER.info(i.getGroupName());
        };
    }
}
