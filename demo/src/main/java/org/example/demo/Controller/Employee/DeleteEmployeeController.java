package org.example.demo.Controller.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demo.Model.EmployeeModel;

import java.sql.SQLException;

public class DeleteEmployeeController {

    @FXML
    private TextField employeeID_Text;

    @FXML private Button backBttn;

    @FXML
    void onDelete(ActionEvent event) {
        String employeeID = employeeID_Text.getText();
        try {
            EmployeeModel.deleteEmployee(employeeID);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Employee deleted successfully!");
            onClear(event);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Invalid Employee ID format.");
            onClear(event);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
            onClear(event);
        }
    }

    @FXML
    void onClear(ActionEvent event) {
        employeeID_Text.clear();
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
