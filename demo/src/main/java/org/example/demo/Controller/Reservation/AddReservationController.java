package org.example.demo.Controller.Reservation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.example.demo.Model.ReservationModel;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddReservationController {

    @FXML private ComboBox<String> status_ComboBox;
    @FXML private DatePicker date_Date;
    @FXML private DatePicker date_Date1;
    @FXML private TextField roomNumber_Text;
    @FXML private TextField employeeID_Text;
    @FXML private TextField guestID_Text;
    @FXML private TextField paymentID_Text;
    @FXML private TextField totalPrice_Text;
    @FXML private Button backBttn;

    @FXML
    void initialize() {
        status_ComboBox.getItems().addAll("CheckedIn","CheckedOut", "Booked", "Cancelled");
    }

    @FXML
    public void onAdd(ActionEvent event) throws SQLException {
        String status = status_ComboBox.getValue();
        LocalDate checkIn = date_Date.getValue();
        LocalDate checkOut = date_Date1.getValue();
        String roomNumber = roomNumber_Text.getText();
        String employeeID = employeeID_Text.getText();
        String guestID = guestID_Text.getText();
        String paymentID = paymentID_Text.getText();
        String totalPrice = totalPrice_Text.getText();

        java.sql.Date check_in = (checkIn != null) ? java.sql.Date.valueOf(checkIn) : null;
        java.sql.Date check_out = (checkIn != null) ? java.sql.Date.valueOf(checkOut) : null;

        ReservationModel.addReservation(status,check_in,check_out,roomNumber,employeeID,guestID,paymentID,totalPrice);
        showAlert(Alert.AlertType.INFORMATION, "Success", "Reservation added successfully");
        onClear(event);


    }

    @FXML
    public void onClear(ActionEvent event) {
        status_ComboBox.setValue(null);
        date_Date.setValue(null);
        date_Date1.setValue(null);
        roomNumber_Text.clear();
        employeeID_Text.clear();
        guestID_Text.clear();
        paymentID_Text.clear();
        totalPrice_Text.clear();
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

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
