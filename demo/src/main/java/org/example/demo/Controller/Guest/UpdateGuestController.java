package org.example.demo.Controller.Guest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
