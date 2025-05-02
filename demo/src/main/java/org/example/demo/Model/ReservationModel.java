package org.example.demo.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationModel {

    private static final String URL = "jdbc:sqlserver://JANA:1433;databaseName=HotelReservation;trustServerCertificate=true;integratedSecurity=true";
    private static Connection con = null;

    public static void connectToDatabase(){
        try{
            con = DriverManager.getConnection(URL);
            if (con != null) {
                System.out.println("Connected to database");
            } else {
                System.out.println("Failed to connect to database");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void addReservation(String status, java.sql.Date checkIn, java.sql.Date checkOut, String roomNo, String employeeId, String guestId, String paymentId, String totalPrice) throws SQLException{

        String sql = "INSERT INTO Reservation (Status, Check_In, Check_Out, Room_Number, Employee_Id, Guest_Id, Payment_Id, Total_Price) VALUES(?,?,?,?,?,?,?,?,?)";

        try(Connection con = DriverManager.getConnection(URL);
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, status);
            ps.setString(2, checkIn.toString());
            ps.setString(3, checkOut.toString());
            ps.setString(4, roomNo);
            ps.setString(5, employeeId);
            ps.setString(6, guestId);
            ps.setString(7, paymentId);
            ps.setString(8, totalPrice);

            ps.executeUpdate();
        }

    }

    public static void updateReservation(String status, java.sql.Date checkIn, java.sql.Date checkOut, String roomNo, String employeeId, String guestId, String paymentId, String reservationId, String totalPrice) throws SQLException{

        StringBuilder sql = new StringBuilder("UPDATE Reservation SET ");
        List<Object> params = new ArrayList<>();

        sql.append("Status=? ");
        params.add(status);

        sql.append(", Check_In=? ");
        params.add(checkIn.toString());

        sql.append(", Check_Out=? ");
        params.add(checkOut.toString());

        sql.append(", Room_Number=? ");
        params.add(roomNo);

        sql.append(", Employee_Id=? ");
        params.add(employeeId);

        sql.append(", Guest_Id=? ");
        params.add(guestId);

        sql.append(", Payment_Id=? ");
        params.add(paymentId);

        sql.append(", Reservation_Id=? ");
        params.add(reservationId);

        sql.append(", Total_Price=? ");
        params.add(totalPrice);

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No Reservation found with ID: " + reservationId);
            }
        }

    }

    public static void deleteReservation(String reservationId) throws SQLException{

        String sql = "DELETE FROM Reservation WHERE Reservation_Id = ?";
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, reservationId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No Reservation found with ID: " + reservationId);
            }
        }

    }

    public static ObservableList<ObservableList<String>> getAllReservations() throws SQLException {
        ObservableList<ObservableList<String>> reservList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Reservation";
        try (Connection con = DriverManager.getConnection(URL);
             Statement ps = con.createStatement();
             ResultSet rs = ps.executeQuery(sql)) {

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("Status"));
                row.add(rs.getString("Check_In"));
                row.add(rs.getString("Check_Out"));
                row.add(rs.getString("Room_Number"));
                row.add(rs.getString("Employee_Id"));
                row.add(rs.getString("Guest_Id"));
                row.add(rs.getString("Payment_Id"));
                row.add(rs.getString("Reservation_Id"));
                reservList.add(row);
            }
        }
        return reservList;
    }

}
