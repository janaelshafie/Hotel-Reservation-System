package org.example.demo.Controller.Payment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AddPaymentController {

    @FXML private MenuButton status_Menu;
    @FXML private MenuButton method_Menu;
    @FXML private DatePicker date_Date;
    @FXML private TextField amount_Text;
    @FXML private TextField reservationID_Text;
    @FXML private TextField guestID_Text;
    @FXML private Button backBttn;

    @FXML
    private void onAdd() {
        String status = status_Menu.getText();
        String method = method_Menu.getText();
        LocalDate date = date_Date.getValue();
        String amount = amount_Text.getText();
        String reservationId = reservationID_Text.getText();
        String guestId = guestID_Text.getText();
    }

    @FXML
    private void onClear() {
        status_Menu.setText("Status");
        method_Menu.setText("Method");
        date_Date.setValue(null);
        amount_Text.clear();
        reservationID_Text.clear();
        guestID_Text.clear();
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
}
