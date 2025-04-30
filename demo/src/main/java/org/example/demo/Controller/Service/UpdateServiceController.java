package org.example.demo.Controller.Service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demo.Model.ServiceModel;

public class UpdateServiceController {
    @FXML
    private TextField name_Text;

    @FXML
    private TextField cateogry_Text;

    @FXML
    private TextField price_Text;

    @FXML
    private TextField description_Text;

    @FXML
    private ComboBox<String> availability_ComboBox;

    @FXML
    private TextField serviceID_Text;

    @FXML
    private Button backBttn;

    @FXML
    public void initialize() {
        availability_ComboBox.getItems().addAll("Yes", "No");
    }

    @FXML
    void onBack(ActionEvent event) {
        FXMLoader(backBttn, "/org/example/demo/Service/ServiceManagement.fxml", "Service Management");
    }

    @FXML
    void onClear(ActionEvent event) {
        name_Text.clear();
        cateogry_Text.clear();
        price_Text.clear();
        description_Text.clear();
        availability_ComboBox.setValue(null);
        serviceID_Text.clear();
    }

    @FXML
    void onUpdate(ActionEvent event) {
        String name = name_Text.getText();
        String category = cateogry_Text.getText();
        String price = price_Text.getText();
        String description = description_Text.getText();
        String availability = availability_ComboBox.getValue();
        String serviceID = serviceID_Text.getText();

        ServiceModel.updateService(name, category, description, price, availability, serviceID);
        showAlert(Alert.AlertType.INFORMATION, "Success", "Service updated successfully");
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
