package org.example.demo.Controller.Payment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.demo.Model.PaymentModel;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;


public class UpdatePaymentController {

    @FXML private ComboBox<String> status_ComboBox;
    @FXML private ComboBox<String> method_ComboBox;
    @FXML private DatePicker date_Date;
    @FXML private TextField amount_Text;
    @FXML private TextField reservationID_Text;
    @FXML private TextField guestID_Text;
    @FXML private Button backBttn;
    @FXML private TextField paymentID_Text;

    @FXML
    void initialize() {
        status_ComboBox.getItems().addAll("Pending","Completed", "Failed", "Refunded");
        method_ComboBox.getItems().addAll("Cash","Credit Card","Debit Card","Online");
    }

    @FXML
    public void onUpdate(ActionEvent event) throws SQLException {
        String status = status_ComboBox.getValue();
        String method = method_ComboBox.getValue();
        LocalDate payDate = date_Date.getValue();
        String amount = amount_Text.getText();
        String reservationId = reservationID_Text.getText();
        String guestId = guestID_Text.getText();

        java.sql.Date paymentDate = (payDate != null) ? java.sql.Date.valueOf(payDate) : null;
        String paymentId = paymentID_Text.getText();

        PaymentModel.updatePayment(status,paymentDate,method,amount,reservationId,guestId,paymentId);
        showAlert(Alert.AlertType.INFORMATION, "Success", "Payment updated successfully");
        onClear(event);


    }

    @FXML
    public void onClear(ActionEvent event) {
        status_ComboBox.setValue("Status");
        method_ComboBox.setValue("Method");
        date_Date.setValue(null);
        amount_Text.clear();
        reservationID_Text.clear();
        guestID_Text.clear();
        paymentID_Text.clear();
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
