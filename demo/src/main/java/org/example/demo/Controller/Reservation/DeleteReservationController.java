package org.example.demo.Controller.Reservation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class DeleteReservationController {

    @FXML private TextField reservationID_Text;
    @FXML private Button backBttn;

    @FXML
    private void onDelete(ActionEvent event) {
        String reservationID = reservationID_Text.getText();
    }

    @FXML
    private void onClear(ActionEvent event) {
        reservationID_Text.clear();
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
