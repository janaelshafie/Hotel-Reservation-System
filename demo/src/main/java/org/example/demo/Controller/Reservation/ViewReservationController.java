package org.example.demo.Controller.Reservation;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.example.demo.Model.ReservationModel;


import java.sql.SQLException;

public class ViewReservationController {

    @FXML
    private TableView<ObservableList<String>> viewReservations_Table;

    @FXML
    private TableColumn<ObservableList<String>, String> ReservationID;

    @FXML
    private TableColumn<ObservableList<String>, String> Status;

    @FXML
    private TableColumn<ObservableList<String>, String> TotalPrice;

    @FXML
    private TableColumn<ObservableList<String>, String> CheckIn;

    @FXML
    private TableColumn<ObservableList<String>, String> CheckOut;

    @FXML
    private TableColumn<ObservableList<String>, String> RoomNumber;

    @FXML
    private TableColumn<ObservableList<String>, String> GuestID;

    @FXML
    private TableColumn<ObservableList<String>, String> PayID;

    @FXML
    private TableColumn<ObservableList<String>, String> EmployeeID;

    @FXML
    private Button backBttn;


    @FXML
    public void initialize() {
        try {

            ReservationID.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(0)));
            Status.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(1)));
            TotalPrice.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(2)));
            CheckIn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(3)));
            CheckOut.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(4)));
            RoomNumber.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(5)));
            GuestID.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(6)));
            PayID.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(7)));
            EmployeeID.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(8)));


            ObservableList<ObservableList<String>> reservations = ReservationModel.getAllReservations();
            viewReservations_Table.setItems(reservations);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    @FXML
    public void onBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/Reservation/ReservationManagement.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backBttn.getScene().getWindow();
            backBttn.getScene().setRoot(root);
            stage.setTitle("Reservation Management");
            stage.show();
        }catch (Exception e){
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