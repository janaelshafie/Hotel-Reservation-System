package org.example.demo.Controller.Department;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demo.Model.DepartmentModel;

import java.sql.SQLException;

public class AddDepartmentController {

    @FXML
    private TextField name_Text;

    @FXML
    private TextField description_Text;

    @FXML
    private Button backBttn;

    @FXML
    void onBack(ActionEvent event) {
        FXMLoader(backBttn, "/org/example/demo/Department/DepartmentManagement.fxml", "Department Management");
    }

    @FXML
    void onClear(ActionEvent event) {
        name_Text.clear();
        description_Text.clear();
    }

    @FXML
    void onAdd(ActionEvent event) throws SQLException {
        String name = name_Text.getText();
        String description = description_Text.getText();

        DepartmentModel.addDepartment(name, description);
        showAlert(Alert.AlertType.INFORMATION, "Success", "Department added successfully");
        onClear(event);
    }

    void FXMLoader(Button button, String fxmldesign , String title){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmldesign));
            Parent root = loader.load();
            Stage stage = (Stage) button.getScene().getWindow();
            button.getScene().setRoot(root);
            stage.setTitle(title);
            stage.show();
        }catch (Exception e){
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
