package org.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.demo.Model.DepartmentModel;
import org.example.demo.Model.GuestModel;
import org.example.demo.Model.RoomModel;
import org.example.demo.Model.ServiceModel;

import java.sql.SQLException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();

            // Set the scene and stage
            Scene scene = new Scene(root);
            primaryStage.setTitle("Dashboard");  // Set the window title
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        GuestModel.connectToDatabase();
        ServiceModel.connectToDatabase();
        DepartmentModel.connectToDatabase();
        RoomModel.connectToDatabase();
        launch(args);
    }
}

