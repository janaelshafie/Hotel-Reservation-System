package org.example.demo.Controller.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ServiceManagementController {

    @FXML
    private Button addServiceBttn;

    @FXML
    private Button updateServiceBttn;

    @FXML
    private Button deleteServiceBttn;

    @FXML
    private Button viewServicesBttn;

    @FXML
    private Button backBttn;

    @FXML
    void onBack(ActionEvent event) {
        FXMLoader(backBttn , "/org/example/demo/Dashboard.fxml", "Dashboard");

    }

    @FXML
    void onAddService(ActionEvent event) {
        FXMLoader(addServiceBttn , "/org/example/demo/Service/Add-ServiceManagement.fxml", "Add Service");

    }

    @FXML
    void onUpdateService(ActionEvent event) {
        FXMLoader(updateServiceBttn , "/org/example/demo/Service/Update-ServiceManagement.fxml", "Update Service");

    }

    @FXML
    void onDeleteService(ActionEvent event) {
        FXMLoader(deleteServiceBttn , "/org/example/demo/Service/Delete-ServiceManagement.fxml", "Delete Service");
    }

    @FXML
    void onViewServices(ActionEvent event) {

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
