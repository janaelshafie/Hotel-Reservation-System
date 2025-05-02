package org.example.demo.Controller.Booking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demo.Model.BookingModel;

import java.sql.SQLException;

public class AddServiceBookingController {
    @FXML private TextField guestID_Text;
    @FXML private TextField employeeID_Text;
    @FXML private Button backBttn;

    @FXML
    void onBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/Booking/BookingManagement.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backBttn.getScene().getWindow();
            backBttn.getScene().setRoot(root);
            stage.setTitle("Booking Management");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    void onClear(ActionEvent event) {
        guestID_Text.clear();
        employeeID_Text.clear();
    }
    @FXML
    void onAdd(ActionEvent event) throws SQLException {
        String guestID = guestID_Text.getText();
        String employeeID = employeeID_Text.getText();

        BookingModel.addServiceBooking(guestID,employeeID);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Service Booking added successfully");
        alert.showAndWait();
        onClear(event);
    }
}
