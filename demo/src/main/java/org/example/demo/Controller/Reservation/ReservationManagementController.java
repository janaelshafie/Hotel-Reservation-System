package org.example.demo.Controller.Reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ReservationManagementController {

    @FXML
    private Button addReservationBttn;

    @FXML
    private Button updateReservationBttn;

    @FXML
    private Button deleteReservationBttn;

    @FXML
    private Button viewReservationsBttn;

    @FXML
    private Button backBttn;

    @FXML
    void onBack(ActionEvent event) {
        FXMLoader(backBttn , "/org/example/demo/Dashboard.fxml", "Dashboard");

    }

    @FXML
    void onAddReservation(ActionEvent event) {
        FXMLoader(addReservationBttn , "/org/example/demo/Reservation/Add-ReservationManagement.fxml", "Add Reservation");

    }

    @FXML
    void onUpdateReservation(ActionEvent event) {
        FXMLoader(updateReservationBttn , "/org/example/demo/Reservation/Update-ReservationManagement.fxml", "Update Reservation");

    }

    @FXML
    void onDeleteReservation(ActionEvent event) {
        FXMLoader(deleteReservationBttn , "/org/example/demo/Reservation/Delete-ReservationManagement.fxml", "Delete Reservation");
    }

    @FXML
    void onViewReservations(ActionEvent event) {
        FXMLoader(viewReservationsBttn , "/org/example/demo/Reservation/View-ReservationManagement.fxml", "Delete Reservation");

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
