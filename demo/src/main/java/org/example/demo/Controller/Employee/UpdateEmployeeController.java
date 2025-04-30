package org.example.demo.Controller.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateEmployeeController {

    @FXML
    private TextField firstName_Text;

    @FXML
    private TextField lastName_Text;

    @FXML
    private TextField email_Text;

    @FXML
    private TextField phoneNum_Text;

    @FXML
    private TextField jobTitle_Text;

    @FXML
    private TextField salary_Text;

    @FXML
    private TextField employeeID_Text;

    @FXML
    private MenuButton gender_Menu;

    @FXML
    private MenuButton department_Menu;

    @FXML
    private Button backBttn;

    @FXML
    void onAdd(ActionEvent event) {

    }

    @FXML
    void onClear(ActionEvent event) {
        firstName_Text.clear();
        lastName_Text.clear();
        email_Text.clear();
        phoneNum_Text.clear();
        jobTitle_Text.clear();
        salary_Text.clear();
        employeeID_Text.clear();
        gender_Menu.setText("Gender");
        department_Menu.setText("Department");
    }

    @FXML
    void onBack(ActionEvent event) {
        loadFXML(backBttn, "/org/example/demo/Employee/EmployeeManagement.fxml", "Employee Management");
    }

    @FXML
    void initialize() {

        gender_Menu.getItems().setAll(
                new MenuItem("Male"),
                new MenuItem("Female")
        );

        department_Menu.getItems().setAll(
                new MenuItem("HR"),
                new MenuItem("IT"),
                new MenuItem("Finance")
        );
    }

    private void loadFXML(Button button, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) button.getScene().getWindow();
            stage.setTitle(title);
            stage.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
