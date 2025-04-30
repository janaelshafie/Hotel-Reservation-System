package org.example.demo.Controller.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EmployeeManagementController {

    @FXML
    private Button addEmployeeBttn;

    @FXML
    private Button updateEmployeeBttn;

    @FXML
    private Button deleteEmployeeBttn;

    @FXML
    private Button viewEmployeesBttn;

    @FXML
    private Button backBttn;


    @FXML
    void onBack(ActionEvent event) {
        FXMLoader(backBttn , "/org/example/demo/Dashboard.fxml", "Dashboard");
    }

    @FXML
    void onAddEmployee(ActionEvent event) {
        FXMLoader(addEmployeeBttn , "/org/example/demo/Employee/Add-EmployeeManagement.fxml", "Add Employee");
    }

    @FXML
    void onUpdateEmployee(ActionEvent event) {
        FXMLoader(updateEmployeeBttn , "/org/example/demo/Employee/Update-EmployeeManagement.fxml", "Update Employee");
    }

    @FXML
    void onDeleteEmployee(ActionEvent event) {
        FXMLoader(deleteEmployeeBttn , "/org/example/demo/Employee/Delete-EmployeeManagement.fxml", "Delete Employee");
    }

    @FXML
    void onViewEmployees(ActionEvent event) {

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
