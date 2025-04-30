package org.example.demo.Controller.Department;

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
import org.example.demo.Model.DepartmentModel;

import java.sql.SQLException;

public class ViewDepartmentsController {
    @FXML
    private Button backBttn;

    @FXML
    private TableView<ObservableList<String>> viewDepartments_Table;
    @FXML
    private TableColumn<ObservableList<String>, String> DepartmentID;
    @FXML
    private TableColumn<ObservableList<String>, String> DepartmentName;
    @FXML
    private TableColumn<ObservableList<String>, String> DepartmentDescription;

    public void onBack(ActionEvent event) {
        FXMLoader(backBttn, "/org/example/demo/Department/DepartmentManagement.fxml", "Department Management");
    }

    @FXML
    public void initialize() {
        try {

            DepartmentID.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(0)));
            DepartmentName.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(2)));
            DepartmentDescription.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().get(1)));

            ObservableList<ObservableList<String>> departments = DepartmentModel.getAllDepartments();
            viewDepartments_Table.setItems(departments);
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
