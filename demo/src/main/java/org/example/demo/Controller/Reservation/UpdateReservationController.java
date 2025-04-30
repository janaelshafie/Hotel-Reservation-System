package org.example.demo.Controller.Reservation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class UpdateReservationController {

    @FXML private MenuButton status_Menu;
    @FXML private DatePicker date_Date;
    @FXML private DatePicker date_Date1;
    @FXML private TextField roomNumber_Text;
    @FXML private TextField employeeID_Text;
    @FXML private TextField guestID_Text;
    @FXML private TextField paymentID_Text;
    @FXML private TextArea totalPrice_Field;
    @FXML private Button backBttn;

    @FXML
    private void onUpdate(ActionEvent event) {
        String status = status_Menu.getText();
        String checkIn = date_Date.getValue() != null ? date_Date.getValue().toString() : "";
        String checkOut = date_Date1.getValue() != null ? date_Date1.getValue().toString() : "";
        String roomNumber = roomNumber_Text.getText();
        String employeeID = employeeID_Text.getText();
        String guestID = guestID_Text.getText();
        String paymentID = paymentID_Text.getText();


    }

    @FXML
    private void onClear(ActionEvent event) {
        status_Menu.setText("Status");
        date_Date.setValue(null);
        date_Date1.setValue(null);
        roomNumber_Text.clear();
        employeeID_Text.clear();
        guestID_Text.clear();
        paymentID_Text.clear();
        totalPrice_Field.clear();
    }

    @FXML
    void onBack(ActionEvent event) {
        loadFXML(backBttn, "/org/example/demo/Reservation/ReservationManagement.fxml", "Reservation Management");
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
