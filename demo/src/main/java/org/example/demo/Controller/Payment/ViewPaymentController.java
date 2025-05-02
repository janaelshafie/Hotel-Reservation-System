package org.example.demo.Controller.Payment;

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
import org.example.demo.Model.PaymentModel;
import org.example.demo.Model.ReservationModel;


import java.sql.SQLException;

public class ViewPaymentController {

    @FXML
    private TableView<ObservableList<String>> viewPayments_Table;

    @FXML
    private TableColumn<ObservableList<String>, String> PaymentID;

    @FXML
    private TableColumn<ObservableList<String>, String> Status;

    @FXML
    private TableColumn<ObservableList<String>, String> PayDate;

    @FXML
    private TableColumn<ObservableList<String>, String> Amount;

    @FXML
    private TableColumn<ObservableList<String>, String> Method;

    @FXML
    private TableColumn<ObservableList<String>, String> GuestID;

    @FXML
    private TableColumn<ObservableList<String>, String> ReserveID;

    @FXML
    private Button backBttn;


    @FXML
    public void initialize() {
        try {

            PaymentID.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(0)));
            Status.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(1)));
            PayDate.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(2)));
            Amount.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(3)));
            Method.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(4)));
            GuestID.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(5)));
            ReserveID.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(6)));


            ObservableList<ObservableList<String>> payments = PaymentModel.getAllPayments();
            viewPayments_Table.setItems(payments);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    @FXML
    public void onBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/Payment/PaymentManagement.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backBttn.getScene().getWindow();
            backBttn.getScene().setRoot(root);
            stage.setTitle("Payment Management");
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