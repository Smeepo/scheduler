package de.patricklass.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.springframework.stereotype.Controller;

@Controller
public class AdminGroupOverviewController {


    @FXML
    private Button adminGroupOverviewCreateInvitationButton = new Button();

    @FXML
    private TableView<?> adminGroupOverviewUserTableView = new TableView<>();

    @FXML
    private TableView<?> adminGroupOverviewInvitationTableView = new TableView<>();

    @FXML
    private Button adminGroupOverviewRemoveUserButton = new Button();

    @FXML
    private BorderPane adminGroupOverviewBorderPane = new BorderPane();

    @FXML
    private Button adminGroupOverviewDeleteInvitationButton = new Button();

    @FXML
    private TextField adminGroupOverviewNameTextfield = new TextField();

    @FXML
    private TableColumn<?, ?> adminGroupOverviewInvitationColumn = new TableColumn<>();

    @FXML
    private TableColumn<?, ?> adminGroupOverviewUserColumn = new TableColumn<>();


}


