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

public class ViewRoomTypesController {

    @FXML
    private Button backBttn;

    @FXML
    private TableView<ObservableList<String>> viewRoomTypes_Table;
    @FXML
    private TableColumn<ObservableList<String>, String> RoomTypeID;
    @FXML
    private TableColumn<ObservableList<String>, String> RoomName;
    @FXML
    private TableColumn<ObservableList<String>, String> Capacity;
    @FXML
    private TableColumn<ObservableList<String>, String> NightPrice;

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

            RoomTypeID.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(0)));
            RoomName.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(1)));
            Capacity.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(2)));
            NightPrice.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(3)));

            ObservableList<ObservableList<String>> roomTypes = RoomModel.getAllRoomTypes();
            viewRoomTypes_Table.setItems(roomTypes);
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
