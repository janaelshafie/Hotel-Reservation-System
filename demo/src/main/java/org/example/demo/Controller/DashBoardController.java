package org.example.demo.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashBoardController {

    @FXML
    private Button guestManagBttn;
    @FXML
    private Button employeeManagBttn;
    @FXML
    private Button departmentManagBttn;
    @FXML
    private Button paymentManagBttn;
    @FXML
    private Button bookingManagBttn;
    @FXML
    private Button serviceManagBttn;
    @FXML
    private Button roomManagBttn;
    @FXML
    private Button reservationManagBttn;

    @FXML
    void onGuestManag(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/Guest/GuestManagement.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) guestManagBttn.getScene().getWindow();
            guestManagBttn.getScene().setRoot(root);
            stage.setTitle("Guest Management");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onEmployeeManag(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/Employee/EmployeeManagement.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) employeeManagBttn.getScene().getWindow();
            employeeManagBttn.getScene().setRoot(root);
            stage.setTitle("Employee Management");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onDepartmentManag(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/Department/DepartmentManagement.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) departmentManagBttn.getScene().getWindow();
            departmentManagBttn.getScene().setRoot(root);
            stage.setTitle("Department Management");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onPaymentManag(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/Payment/PaymentManagement.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) paymentManagBttn.getScene().getWindow();
            paymentManagBttn.getScene().setRoot(root);
            stage.setTitle("Payment Management");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBookingManag(ActionEvent event) {

    }

    @FXML
    void onServiceManag(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/Service/ServiceManagement.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) serviceManagBttn.getScene().getWindow();
            serviceManagBttn.getScene().setRoot(root);
            stage.setTitle("Service Management");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onRoomManag(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/Room/RoomManagement.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) roomManagBttn.getScene().getWindow();
            roomManagBttn.getScene().setRoot(root);
            stage.setTitle("Room Management");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onReservationManag(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/Reservation/ReservationManagement.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) reservationManagBttn.getScene().getWindow();
            reservationManagBttn.getScene().setRoot(root);
            stage.setTitle("Reservation Management");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
