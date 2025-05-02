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

    public static void addReservation(String status, java.sql.Date checkIn, java.sql.Date checkOut, String roomNo,
                                            String employeeId, String guestId, String paymentId, String totalPrice) throws SQLException {

        // SQL queries
        String insertSql = "INSERT INTO Reservation (Reserve_stat, Check_In, Check_Out, Room_Id, Employee_Id, Guest_Id, Pay_Id, Total_Price) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            // 1. Insert the reservation and get the generated Reservation_Id
            try (PreparedStatement ps = con.prepareStatement(insertSql)) {
                ps.setString(1, status);
                ps.setDate(2, checkIn);
                ps.setDate(3, checkOut);
                ps.setInt(4, Integer.parseInt(roomNo));

                if (employeeId == null || employeeId.trim().isEmpty()) {
                    ps.setNull(5, java.sql.Types.INTEGER);
                } else {
                    ps.setInt(5, Integer.parseInt(employeeId));
                }

                ps.setInt(6, Integer.parseInt(guestId));

                if (paymentId == null || paymentId.trim().isEmpty()) {
                    ps.setNull(7, java.sql.Types.INTEGER);
                } else {
                    ps.setInt(7, Integer.parseInt(paymentId));
                }

                ps.setDouble(8,Double.parseDouble(totalPrice));
                ps.executeUpdate();
            }catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }

    public static void updateReservation(String status, java.sql.Date checkIn, java.sql.Date checkOut, String roomNo,
                                               String employeeId, String guestId, String paymentId, String reservationId, String totalPrice) throws SQLException {

        StringBuilder sql = new StringBuilder("UPDATE Reservation SET ");
        List<Object> params = new ArrayList<>();

        if (status != null && !status.isEmpty()) {
            sql.append("Reserve_stat=?, ");
            params.add(status);
        }

        if (totalPrice != null && !totalPrice.isEmpty()) {
            sql.append("Total_Price=?, ");
            params.add(totalPrice);
        }

        if (checkIn != null) {
            sql.append("Check_IN=?, ");
            params.add(checkIn);
        }

        if (checkOut != null) {
            sql.append("Check_Out=?, ");
            params.add(checkOut);
        }

        if (roomNo != null && !roomNo.isEmpty()) {
            int roomNum = Integer.parseInt(roomNo);
            sql.append("Room_Id=?, ");
            params.add(roomNum);
        }

        if (employeeId != null && !employeeId.isEmpty()) {
            sql.append("Employee_Id=?, ");
            params.add(Integer.parseInt(employeeId));
        }

        if (guestId != null && !guestId.isEmpty()) {
            sql.append("Guest_Id=?, ");
            params.add(Integer.parseInt(guestId));
        }

        if (paymentId != null && !paymentId.isEmpty()) {
            sql.append("Pay_Id=?, ");
            params.add(Integer.parseInt(paymentId));
        }

        if (params.isEmpty()) {
            throw new IllegalArgumentException("No fields provided to update.");
        }

        sql.setLength(sql.length() - 2); // Remove last comma
        sql.append(" WHERE Reserve_Id = ?");
        int reserveID = Integer.parseInt(reservationId);
        params.add(reserveID);

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No reservation found with ID: " + reservationId);
            }

        }
    }


    public static void deleteReservation(String reservationId) throws SQLException{

        String sql = "DELETE FROM Reservation WHERE Reserve_Id = ?";
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
                row.add(rs.getString("Reserve_Id"));
                row.add(rs.getString("Reserve_stat"));
                row.add(rs.getString("Total_Price"));
                row.add(rs.getString("Check_IN"));
                row.add(rs.getString("Check_Out"));
                row.add(rs.getString("Room_Id"));
                row.add(rs.getString("Guest_Id"));
                row.add(rs.getString("Pay_Id"));
                row.add(rs.getString("Employee_Id"));
                reservList.add(row);
            }
        }
        return reservList;
    }

}
