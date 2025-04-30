package org.example.demo.Controller.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demo.Model.GuestModel;
import org.example.demo.Model.ServiceModel;

import java.sql.SQLException;

public class DeleteServiceController {
    @FXML
    private TextField serviceID_Text;
    @FXML
    private Button backBttn;
    @FXML
    void onBack(ActionEvent event) {
        FXMLoader(backBttn, "/org/example/demo/Service/ServiceManagement.fxml", "Service Management");

    }
    @FXML
    void onClear(ActionEvent event) {
        serviceID_Text.clear();
    }

    @FXML
    void onDelete(ActionEvent event) {
        String serviceID = serviceID_Text.getText();
        try {
            ServiceModel.deleteService(serviceID);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Service deleted successfully!");
            onClear(event);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Invalid Service ID format.");
            onClear(event);
        }

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
