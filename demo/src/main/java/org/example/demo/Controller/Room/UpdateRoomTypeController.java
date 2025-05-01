package org.example.demo.Controller.Room;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demo.Model.RoomModel;

import java.sql.SQLException;

public class UpdateRoomTypeController {
    @FXML private TextField roomName_Text;
    @FXML private TextField capacity_Text;
    @FXML private Button backBttn;
    @FXML private TextField nightPrice_Text;
    @FXML private TextField roomTypeID_Text;

    @FXML
    public void onUpdate(ActionEvent event) throws SQLException {
        String roomName = roomName_Text.getText();
        String capacity = capacity_Text.getText();
        String nightPrice = nightPrice_Text.getText();
        String roomTypeID = roomTypeID_Text.getText();

        RoomModel.updateRoomType(roomName, capacity, nightPrice, roomTypeID);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Room Type Updated Successfully");
        alert.showAndWait();
        onClear(event);

    }

    @FXML
    public void onBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/Room/RoomManagement.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backBttn.getScene().getWindow();
            backBttn.getScene().setRoot(root);
            stage.setTitle("Room Management");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    public void onClear(ActionEvent event) {
        roomName_Text.clear();
        capacity_Text.clear();
        nightPrice_Text.clear();
        roomTypeID_Text.clear();

    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
