package org.example.demo.Controller.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddEmployeeController {

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
    private MenuButton gender_Menu;

    @FXML
    private MenuButton department_Menu;

    @FXML
    private Button addBttn;

    @FXML
    private Button clearBttn;

    @FXML
    private Button backBttn;

    @FXML
    void onAdd(ActionEvent event) {
        String firstName = firstName_Text.getText();
        String lastName = lastName_Text.getText();
        String email = email_Text.getText();
        String phone = phoneNum_Text.getText();
        String jobTitle = jobTitle_Text.getText();
        String salary = salary_Text.getText();
        String gender = gender_Menu.getText();
        String department = department_Menu.getText();
    }

    @FXML
    void onClear(ActionEvent event) {
        firstName_Text.clear();
        lastName_Text.clear();
        email_Text.clear();
        phoneNum_Text.clear();
        jobTitle_Text.clear();
        salary_Text.clear();
        gender_Menu.setText("Gender");
        department_Menu.setText("Department");
    }

    @FXML
    void onBack(ActionEvent event) {
        loadFXML(backBttn, "/org/example/demo/Employee/EmployeeManagement.fxml", "Employee Management");
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
