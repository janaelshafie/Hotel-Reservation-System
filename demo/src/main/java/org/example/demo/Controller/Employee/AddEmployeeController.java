package org.example.demo.Controller.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.demo.Model.EmployeeModel;

import java.sql.SQLException;

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
    private ComboBox<String> gender_ComboBox;

    @FXML
    private TextField departmentID_Text;

    @FXML
    private Button backBttn;

    @FXML
    void initialize() {

        gender_ComboBox.getItems().setAll("Male", "Female");
    }

    @FXML
    void onAdd(ActionEvent event) throws SQLException {
        String firstName = firstName_Text.getText();
        String lastName = lastName_Text.getText();
        String email = email_Text.getText();
        String phone = phoneNum_Text.getText();
        String jobTitle = jobTitle_Text.getText();
        String salary = salary_Text.getText();
        String gender = gender_ComboBox.getValue();
        String departmentID = departmentID_Text.getText();

        EmployeeModel.addEmployee(firstName,lastName,email,phone,jobTitle,gender,salary,departmentID);
        showAlert(Alert.AlertType.INFORMATION, "Success", "Employee added successfully");
        onClear(event);
    }

    @FXML
    void onClear(ActionEvent event) {
        firstName_Text.clear();
        lastName_Text.clear();
        email_Text.clear();
        phoneNum_Text.clear();
        jobTitle_Text.clear();
        salary_Text.clear();
        gender_ComboBox.setValue("Gender");
        departmentID_Text.clear();
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
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
