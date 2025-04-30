package org.example.demo.Controller.Department;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DepartmentManagementController {

    @FXML
    private Button addDeptBttn;

    @FXML
    private Button updateDeptBttn;

    @FXML
    private Button deleteDeptBttn;

    @FXML
    private Button viewDeptBttn;

    @FXML
    private Button backBttn;

    @FXML
    void onBack(ActionEvent event) {
        FXMLoader(backBttn , "/org/example/demo/Dashboard.fxml", "Dashboard");

    }

    @FXML
    void onAddDept(ActionEvent event) {
        FXMLoader(addDeptBttn , "/org/example/demo/Department/Add-DepartmentManagement.fxml", "Add Department");

    }

    @FXML
    void onUpdateDept(ActionEvent event) {
        FXMLoader(updateDeptBttn , "/org/example/demo/Department/Update-DepartmentManagement.fxml", "Update Department");

    }

    @FXML
    void onDeleteDept(ActionEvent event) {
        FXMLoader(deleteDeptBttn , "/org/example/demo/Department/Delete-DepartmentManagement.fxml", "Delete Department");
    }

    @FXML
    void onViewDept(ActionEvent event) {
        FXMLoader(viewDeptBttn , "/org/example/demo/Department/View-DepartmentManagement.fxml", "View Departments");

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
