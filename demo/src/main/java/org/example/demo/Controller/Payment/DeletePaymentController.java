package org.example.demo.Controller.Payment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demo.Model.PaymentModel;
import org.example.demo.Model.ReservationModel;

import java.sql.SQLException;

public class DeletePaymentController {
    @FXML private TextField paymentID_Text;
    @FXML private Button backBttn;

    @FXML
    public void onClear(ActionEvent event) {
        paymentID_Text.clear();
    }

    @FXML
    public void onDelete(ActionEvent event) {
        String paymentID = paymentID_Text.getText();
        try {
            PaymentModel.deletePayment(paymentID);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Payment deleted successfully!");
            onClear(event);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Invalid Payment ID format.");
            onClear(event);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
            onClear(event);
        }
    }

    @FXML
    void onBack(ActionEvent event) {
        loadFXML(backBttn, "/org/example/demo/Payment/PaymentManagement.fxml", "Payment Management");
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
