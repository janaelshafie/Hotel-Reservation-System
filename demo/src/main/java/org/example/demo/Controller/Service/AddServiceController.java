package org.example.demo.Controller.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import org.example.demo.Model.GuestModel;
import org.example.demo.Model.ServiceModel;

import java.sql.SQLException;

public class AddServiceController {

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

    }

    @FXML
    void onAdd(ActionEvent event) {
        String name = name_Text.getText();
        String category = cateogry_Text.getText();
        String price = price_Text.getText();
        String description = description_Text.getText();
        String availability = availability_ComboBox.getValue();

        ServiceModel.addService(name, category, description, price, availability);
        showAlert(Alert.AlertType.INFORMATION, "Success", "Service added successfully");
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
