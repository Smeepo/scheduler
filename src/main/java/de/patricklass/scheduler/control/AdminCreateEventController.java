package de.patricklass.scheduler.control;

import de.patricklass.scheduler.model.Group;
import de.patricklass.scheduler.model.Invitation;
import de.patricklass.scheduler.model.User;
import de.patricklass.scheduler.repository.GroupRepository;
import de.patricklass.scheduler.repository.InvitationRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * Called on AdminGroupOverview
 * Admin creates/updates events here
 * @author Minh
 */
@Controller
public class AdminCreateEventController {

    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private ChoiceBox<Group> choiceBoxGroup;

    @FXML
    private Button btnCancel;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField timeField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField nameField;

    @FXML
    private Button btnCreate;

    private GroupRepository groupRepository;
    private InvitationRepository invitationRepository;
    private SceneManager sceneManager;
    private AdminGroupOverviewController adminGroupOverviewController;

    public AdminCreateEventController(GroupRepository groupRepository, InvitationRepository invitationRepository, SceneManager sceneManager) {
        this.groupRepository = groupRepository;
        this.invitationRepository = invitationRepository;
        this.sceneManager = sceneManager;
    }

    /**
     * groupField is populated by previous scene(AdminGroupOverView)
     */
    @FXML
    public void initialize(){
        choiceBoxGroup.setConverter(new StringConverter<Group>() {
            @Override
            public String toString(Group object) {
                return object.getGroupName();
            }

            @Override
            public Group fromString(String string) {
                return groupRepository.findOneByGroupName(string);
            }
        });
    }

    public void loadForUser(User user, AdminGroupOverviewController adminGroupOverviewController){
        this.adminGroupOverviewController = adminGroupOverviewController;
        choiceBoxGroup.getItems().clear();
        choiceBoxGroup.getItems().addAll(groupRepository.findAllByUsersContains(user));
        choiceBoxGroup.setValue(adminGroupOverviewController.loadedGroup);
    }

    public void createEvent(){
        Group group = choiceBoxGroup.getValue();
        Invitation invitation = new Invitation(
                dateField.getValue(),
                group,
                nameField.getText(),
                descriptionArea.getText()
        );

        group.getInvitations().add(invitation);
        invitationRepository.save(invitation);
        groupRepository.save(group);
        adminGroupOverviewController.loadTables();
        LOGGER.info("event created");
        sceneManager.showLastScene();
    }

    public void cancel(){
        //close scene
        LOGGER.info("closed");
        sceneManager.showLastScene();
    }
}
