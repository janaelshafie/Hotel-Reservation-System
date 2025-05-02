package org.example.demo.Controller.Payment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PaymentManagementController {

    @FXML
    private Button addPayBttn;

    @FXML
    private Button updatePayBttn;

    @FXML
    private Button deletePayBttn;

    @FXML
    private Button viewPaysBttn;

    @FXML
    private Button backBttn;

    @FXML
    void onBack(ActionEvent event) {
        FXMLoader(backBttn , "/org/example/demo/Dashboard.fxml", "Dashboard");

    }

    @FXML
    void onAddPay(ActionEvent event) {
        FXMLoader(addPayBttn , "/org/example/demo/Payment/Add-PaymentManagement.fxml", "Add Payment");

    }

    @FXML
    void onUpdatePay(ActionEvent event) {
        FXMLoader(updatePayBttn , "/org/example/demo/Payment/Update-PaymentManagement.fxml", "Update Payment");

    }

    @FXML
    void onDeletePay(ActionEvent event) {
        FXMLoader(deletePayBttn , "/org/example/demo/Payment/Delete-PaymentManagement.fxml", "Delete Payment");
    }

    @FXML
    void onViewPays(ActionEvent event) {
        FXMLoader(viewPaysBttn , "/org/example/demo/Payment/View-PaymentManagement.fxml", "View Payment");

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
