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

public class DeleteBookingDetailsController {
    @FXML TextField bookingID_Text;
    @FXML TextField guestID_Text;
    @FXML Button backBttn;

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
        bookingID_Text.clear();
        guestID_Text.clear();
    }

    @FXML
    void onDelete(ActionEvent event) throws SQLException {
        String bookingID = bookingID_Text.getText();
        String guestID = guestID_Text.getText();

        BookingModel.deleteBookingDetails(bookingID , guestID);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Booking Details deleted successfully");
        alert.showAndWait();
        onClear(event);
    }


}
