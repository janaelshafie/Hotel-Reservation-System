package org.example.demo.Controller.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RoomManagementController {

    @FXML
    private Button addRoomBttn;

    @FXML
    private Button updateRoomBttn;

    @FXML
    private Button deleteRoomBttn;

    @FXML
    private Button viewRoomsBttn;

    @FXML
    private Button addRoomTypeBttn;

    @FXML
    private Button updateRoomTypeBttn;

    @FXML
    private Button deleteRoomTypeBttn;

    @FXML
    private Button viewRoomTypeBttn;


    @FXML
    private Button backBttn;

    @FXML
    void onBack(ActionEvent event) {
        FXMLoader(backBttn , "/org/example/demo/Dashboard.fxml", "Dashboard");

    }

    @FXML
    void onAddRoom(ActionEvent event) {
        FXMLoader(addRoomBttn , "/org/example/demo/Room/AddRoom-RoomManagement.fxml", "Add Room");

    }

    @FXML
    void onUpdateRoom(ActionEvent event) {
        FXMLoader(updateRoomBttn , "/org/example/demo/Room/UpdateRoom-RoomManagement.fxml", "Update Room");

    }

    @FXML
    void onDeleteRoom(ActionEvent event) {
        FXMLoader(deleteRoomBttn , "/org/example/demo/Room/DeleteRoom-RoomManagement.fxml", "Delete Room");
    }

    @FXML
    void onViewRooms(ActionEvent event) {

    }

    @FXML
    void onAddRoomType(ActionEvent event) {
        FXMLoader(addRoomTypeBttn , "/org/example/demo/Room/AddRoomType-RoomManagement.fxml", "Add Room Type");

    }

    @FXML
    void onUpdateRoomType(ActionEvent event) {
        FXMLoader(updateRoomTypeBttn , "/org/example/demo/Room/UpdateRoomType-RoomManagement.fxml", "Update Room Type");

    }

    @FXML
    void onDeleteRoomType(ActionEvent event) {
        FXMLoader(deleteRoomTypeBttn , "/org/example/demo/Room/DeleteRoomType-RoomManagement.fxml", "Delete Room Type");

    }

    @FXML
    void onViewRoomTypes(ActionEvent event) {

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
