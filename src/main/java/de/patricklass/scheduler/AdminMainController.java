package de.patricklass.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.springframework.stereotype.Controller;

@Controller
public class AdminMainController {


    @FXML
    private BorderPane AdminMainBorderPane = new BorderPane();

    @FXML
    private TableView AdminUserTableView = new TableView();

    @FXML
    private TableView AdminGroupTableView = new TableView();

    @FXML
    private TableColumn AdminUserColumn = new TableColumn();

    @FXML
    private TableColumn AdminGroupColumn = new TableColumn();

    @FXML
    private Button AddUserButton = new Button();

    @FXML
    private Button DelUserButton = new Button();

    @FXML
    private Button DelGroupButton = new Button();

    @FXML
    private Button CreateGroupButton = new Button();


}

