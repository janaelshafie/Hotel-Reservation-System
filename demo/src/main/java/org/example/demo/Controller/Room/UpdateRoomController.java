package org.example.demo.Controller.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demo.Model.RoomModel;

import java.sql.SQLException;

public class UpdateRoomController {
    @FXML private ComboBox<String> status_ComboBox;
    @FXML private TextField roomNumber_Text;
    @FXML private TextField type_Text;
    @FXML private Button backBttn;

    @FXML
    public void initialize() {
        status_ComboBox.getItems().addAll("Available", "Occupied", "Maintenance", "Reserved");
    }

    @FXML
    public void onUpdate(ActionEvent event) throws SQLException {
        String roomNumber = roomNumber_Text.getText();
        String roomTypeID = type_Text.getText();
        String roomStatus = status_ComboBox.getValue();

        RoomModel.updateRoom(roomNumber,roomStatus,roomTypeID);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Room updated successfully");
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
        status_ComboBox.setValue(null);
        roomNumber_Text.clear();
        type_Text.clear();

    }
}
