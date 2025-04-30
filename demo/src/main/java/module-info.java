module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports org.example.demo;
    exports org.example.demo.Controller;
    exports org.example.demo.Controller.Guest;
    exports org.example.demo.Controller.Employee;
    exports org.example.demo.Controller.Department;
    exports org.example.demo.Controller.Payment;
    exports org.example.demo.Controller.Reservation;
    exports org.example.demo.Controller.Room;
    exports org.example.demo.Controller.Service;
    opens org.example.demo to javafx.fxml;
    opens org.example.demo.Controller to javafx.fxml;
    opens org.example.demo.Controller.Guest to javafx.fxml;
    opens org.example.demo.Controller.Employee to javafx.fxml;
    opens org.example.demo.Controller.Department to javafx.fxml;
    opens org.example.demo.Controller.Payment to javafx.fxml;
    opens org.example.demo.Controller.Reservation to javafx.fxml;
    opens org.example.demo.Controller.Room to javafx.fxml;
    opens org.example.demo.Controller.Service to javafx.fxml;
}