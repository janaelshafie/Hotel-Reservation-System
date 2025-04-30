package org.example.demo.Controller.Guest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.demo.Model.GuestModel;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.time.LocalDate;

public class UpdateGuestController {

    @FXML
    private TextField firstName_Text;

    @FXML
    private TextField lastName_Text;

    @FXML
    private TextField email_Text;

    @FXML
    private TextField nationalID_Text;

    @FXML
    private TextField nationality_Text;

    @FXML
    private DatePicker birthDate_Date;

    @FXML
    private TextField phoneNumber_Text;

    @FXML
    private TextField address_Text;

    @FXML
    private ComboBox <String> gender_ComboBox;

    @FXML
    private TextField guestID_Text;

    @FXML
    private Button backBttn;

    @FXML
    public void initialize() {
        gender_ComboBox.getItems().addAll("Male", "Female");
    }

    @FXML
    void onBack(ActionEvent event) {
        FXMLoader(backBttn, "/org/example/demo/Guest/GuestManagement.fxml", "Guest Management");
    }

    @FXML
    void onClear(ActionEvent event) {
        firstName_Text.clear();
        lastName_Text.clear();
        email_Text.clear();
        nationalID_Text.clear();
        nationality_Text.clear();
        birthDate_Date.setValue(null);
        phoneNumber_Text.clear();
        address_Text.clear();
        guestID_Text.clear();
        gender_ComboBox.setValue(null);

    }

    @FXML
    void onUpdate(ActionEvent event) {
        String fName = firstName_Text.getText();
        String lName = lastName_Text.getText();
        String email = email_Text.getText();
        String nationalID = nationalID_Text.getText();
        String nationality = nationality_Text.getText();
        LocalDate Date = birthDate_Date.getValue();
        String gender = gender_ComboBox.getValue();
        String phoneNumber = phoneNumber_Text.getText();
        String address = address_Text.getText();
        String guestID = guestID_Text.getText();

        java.sql.Date birthDate = (Date != null) ? java.sql.Date.valueOf(Date) : null;

        try{
            GuestModel.updateGuest(fName ,lName, email, gender, nationality, nationalID, birthDate, phoneNumber, address, guestID);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Guest updated successfully");
            onClear(event);
        }
        catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Invalid Guest ID format.");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
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

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
