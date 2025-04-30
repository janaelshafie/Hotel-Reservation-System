package org.example.demo.Controller.Service;

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
import org.example.demo.Model.GuestModel;
import org.example.demo.Model.ServiceModel;

import java.sql.SQLException;

public class ViewServicesController {
    @FXML
    private Button backBttn;

    @FXML
    private TableView<ObservableList<String>> viewServices_Table;
    @FXML
    private TableColumn<ObservableList<String>, String> ServiceID;
    @FXML
    private TableColumn<ObservableList<String>, String> ServiceName;
    @FXML
    private TableColumn<ObservableList<String>, String> ServiceCategory;
    @FXML
    private TableColumn<ObservableList<String>, String> Availability;
    @FXML
    private TableColumn<ObservableList<String>, String> Price;
    @FXML
    private TableColumn<ObservableList<String>, String> Description;

    public void onBack(ActionEvent event) {
        FXMLoader(backBttn, "/org/example/demo/Service/ServiceManagement.fxml", "Service Management");
    }

    @FXML
    public void initialize() {
        try {

            ServiceID.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(0)));
            ServiceName.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(1)));
            ServiceCategory.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(2)));
            Availability.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(3)));
            Price.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(4)));
            Description.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(5)));

            ObservableList<ObservableList<String>> services = ServiceModel.getAllServices();
            viewServices_Table.setItems(services);
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
