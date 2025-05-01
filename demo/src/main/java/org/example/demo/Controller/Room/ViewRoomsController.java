package org.example.demo.Controller.Room;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.example.demo.Model.RoomModel;
import org.example.demo.Model.ServiceModel;

import java.sql.SQLException;

public class ViewRoomsController {

    @FXML
    private Button backBttn;

    @FXML
    private TableView<ObservableList<String>> viewRooms_Table;
    @FXML
    private TableColumn<ObservableList<String>, String> RoomNumber;
    @FXML
    private TableColumn<ObservableList<String>, String> Status;
    @FXML
    private TableColumn<ObservableList<String>, String> RoomTypeID;

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
    public void initialize() {
        try {

            RoomNumber.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(0)));
            Status.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(1)));
            RoomTypeID.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(2)));

            ObservableList<ObservableList<String>> rooms = RoomModel.getAllRooms();
            viewRooms_Table.setItems(rooms);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
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
