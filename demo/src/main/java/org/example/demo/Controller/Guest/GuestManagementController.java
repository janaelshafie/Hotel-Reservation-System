package org.example.demo.Controller.Guest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GuestManagementController {

    @FXML
    private Button addGuestBttn;

    @FXML
    private Button updateGuestBttn;

    @FXML
    private Button deleteGuestBttn;

    @FXML
    private Button viewGuestsBttn;

    @FXML
    private Button backBttn;

    @FXML
    void onBack(ActionEvent event) {
        FXMLoader(backBttn , "/org/example/demo/Dashboard.fxml", "Dashboard");
    }

    @FXML
    void onAddGuest(ActionEvent event) {
        FXMLoader(addGuestBttn , "/org/example/demo/Guest/Add-GuestManagement.fxml", "Add Guest");
    }

    @FXML
    void onUpdateGuest(ActionEvent event) {
        FXMLoader(updateGuestBttn , "/org/example/demo/Guest/Update-GuestManagement.fxml", "Update Guest");

    }

    @FXML
    void onDeleteGuest(ActionEvent event) {
        FXMLoader(deleteGuestBttn , "/org/example/demo/Guest/Delete-GuestManagement.fxml", "Delete Guest");

    }

    @FXML
    void onViewGuests(ActionEvent event) {

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
