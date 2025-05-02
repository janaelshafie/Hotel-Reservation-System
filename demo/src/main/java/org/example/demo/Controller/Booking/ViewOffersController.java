package org.example.demo.Controller.Booking;

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
import org.example.demo.Model.BookingModel;

import java.sql.SQLException;

public class ViewOffersController {
    @FXML private Button backBttn;
    @FXML private TableView<ObservableList<String>> viewOffers_Table;
    @FXML private TableColumn<ObservableList<String>, String> BookingID;
    @FXML private TableColumn<ObservableList<String>, String> ServiceID;
    @FXML private TableColumn<ObservableList<String>, String> GuestID;

    public void onBack(ActionEvent event) {
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
    public void initialize() {
        try {

            BookingID.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(0)));
            ServiceID.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(1)));
            GuestID.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(2)));


            ObservableList<ObservableList<String>> offers = BookingModel.getAllOffers();
            viewOffers_Table.setItems(offers);
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
