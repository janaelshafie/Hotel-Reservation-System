package org.example.demo.Controller.Guest;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.demo.Model.GuestModel;
import javafx.scene.control.Alert;

import java.sql.SQLException;

public class ViewGuestsController {

    @FXML
    private Button backBttn;

    @FXML
    private TableView<ObservableList<String>> viewGuests_Table;
    @FXML
    private TableColumn<ObservableList<String>, String> FirstName;
    @FXML
    private TableColumn<ObservableList<String>, String> LastName;
    @FXML
    private TableColumn<ObservableList<String>, String> Email;
    @FXML
    private TableColumn<ObservableList<String>, String> PhoneNumber;
    @FXML
    private TableColumn<ObservableList<String>, String> Address;
    @FXML
    private TableColumn<ObservableList<String>, String> Gender;
    @FXML
    private TableColumn<ObservableList<String>, String> BirthDate;
    @FXML
    private TableColumn<ObservableList<String>, String> Nationality;
    @FXML
    private TableColumn<ObservableList<String>, String> SSN;
    @FXML
    private TableColumn<ObservableList<String>, String> GuestID;

    @FXML
    public void initialize() {
        try {

            // Set cell value factories by index — assuming guest_id is column 0, name is 1, email is 2, etc.
            GuestID.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(0)));
            FirstName.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(1)));
            LastName.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(2)));
            Email.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(3)));
            Gender.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(4)));
            Nationality.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(5)));
            SSN.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(6)));
            BirthDate.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(7)));
            PhoneNumber.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(8)));
            Address.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(9)));

            ObservableList<ObservableList<String>> guests = GuestModel.getAllGuests();
            viewGuests_Table.setItems(guests);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    public void onBack(ActionEvent event) {
        FXMLoader(backBttn, "/org/example/demo/Guest/GuestManagement.fxml", "Guest Management");
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
