package org.example.demo.Controller.Booking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BookingManagementController {
    @FXML private Button addServiceBooking;

    @FXML private Button updateServiceBooking;

    @FXML private Button deleteServiceBooking;

    @FXML private Button viewServiceBooking;

    @FXML private Button addOffers;

    @FXML private Button updateOffers;

    @FXML private Button deleteOffers;

    @FXML private Button viewOffers;

    @FXML private Button addBookingDetails;

    @FXML private Button updateBookingDetails;

    @FXML private Button deleteBookingDetails;

    @FXML private Button viewBookingDetails;

    @FXML
    private Button backBttn;

    @FXML
    void onBack(ActionEvent event) {
        FXMLoader(backBttn , "/org/example/demo/Dashboard.fxml", "Dashboard");

    }

    @FXML
    void onAdd1(ActionEvent event) {
        FXMLoader(addServiceBooking , "/org/example/demo/Booking/AddServiceBooking.fxml", "Add Service Booking");

    }

    @FXML
    void onUpdate1(ActionEvent event) {
        FXMLoader(updateServiceBooking , "/org/example/demo/Booking/UpdateServiceBooking.fxml", "Update Service Booking");

    }

    @FXML
    void onDelete1(ActionEvent event) {
        FXMLoader(deleteServiceBooking , "/org/example/demo/Booking/DeleteServiceBooking.fxml", "Delete Service Booking");
    }

    @FXML
    void onView1(ActionEvent event) {
        FXMLoader(viewServiceBooking , "/org/example/demo/Booking/ViewServiceBooking.fxml", "View Service Bookings");

    }

    @FXML
    void onAdd2(ActionEvent event) {
        FXMLoader(addOffers , "/org/example/demo/Booking/AddOffers.fxml", "Add Offers");

    }

    @FXML
    void onUpdate2(ActionEvent event) {
        FXMLoader(updateOffers , "/org/example/demo/Booking/UpdateOffers.fxml", "Update Offers");

    }

    @FXML
    void onDelete2(ActionEvent event) {
        FXMLoader(deleteOffers , "/org/example/demo/Booking/DeleteOffers.fxml", "Delete Offers");

    }

    @FXML
    void onView2(ActionEvent event) {
        FXMLoader(viewOffers , "/org/example/demo/Booking/ViewOffers.fxml", "View Offers");

    }

    @FXML
    void onAdd3(ActionEvent event) {
        FXMLoader(addBookingDetails , "/org/example/demo/Booking/AddBookingDetails.fxml", "Add Booking Details");

    }

    @FXML
    void onUpdate3(ActionEvent event) {
        FXMLoader(updateBookingDetails , "/org/example/demo/Booking/UpdateBookingDetails.fxml", "Update Booking Details");

    }

    @FXML
    void onDelete3(ActionEvent event) {
        FXMLoader(deleteBookingDetails , "/org/example/demo/Booking/DeleteBookingDetails.fxml", "Delete Booking Details");

    }

    @FXML
    void onView3(ActionEvent event) {
        FXMLoader(viewBookingDetails , "/org/example/demo/Booking/ViewBookingDetails.fxml", "View Booking Details");

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
}
