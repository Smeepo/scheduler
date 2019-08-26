package de.patricklass.scheduler.control;

import de.patricklass.scheduler.model.Group;
import de.patricklass.scheduler.model.User;
import de.patricklass.scheduler.repository.GroupRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

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

    public AdminCreateEventController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
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
                return null;
            }
        });
    }

    public void loadForUser(User user){
        choiceBoxGroup.getItems().addAll(groupRepository.findAllByUsersContains(user));
    }

    public void createEvent(){
        //creates event
        LOGGER.info("event created");
    }

    public void cancel(){
        //close scene
        LOGGER.info("closed");
    }
}
