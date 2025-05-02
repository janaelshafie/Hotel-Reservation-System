package org.example.demo.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentModel {

    private static final String URL = "jdbc:sqlserver://JANA:1433;databaseName=HotelReservation;trustServerCertificate=true;integratedSecurity=true";
    private static Connection con = null;

    public static void connectToDatabase() {
        try {
            con = DriverManager.getConnection(URL);
            if (con != null) {
                System.out.println("Connected to database");
            } else {
                System.out.println("Failed to connect to database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addPayment(String status, java.sql.Date paymentDate, String method, String amount, String reserveId, String guestId) throws SQLException {

        String sql = "INSERT INTO Payment (Pay_Stat, Pay_Date, Amount, Pay_Meth, Guest_Id, Reserve_Id) VALUES(?,?,?,?,?,?)";

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);

            // paymentDate (already a Date or null)
            if (paymentDate == null) {
                ps.setNull(2, java.sql.Types.DATE);
            } else {
                ps.setDate(2, paymentDate);
            }

            ps.setDouble(3, Double.parseDouble(amount));
            ps.setString(4, method);
            ps.setInt(5, Integer.parseInt(guestId));

            // reserveId (nullable)
            if (reserveId == null || reserveId.trim().isEmpty()) {
                ps.setNull(6, java.sql.Types.INTEGER);
            } else {
                ps.setInt(6, Integer.parseInt(reserveId));
            }

            ps.executeUpdate();
        }
    }

    public static void updatePayment(String status, java.sql.Date paymentDate, String method, String amount, String reserveId, String guestId, String paymentId) throws SQLException {

        StringBuilder sql = new StringBuilder("UPDATE Payment SET ");
        List<Object> params = new ArrayList<>();

        if (status != null && !status.isEmpty()) {
            sql.append("Pay_Stat=?, ");
            params.add(status);
        }
        if (paymentDate != null) {
            sql.append("Pay_Date=?, ");
            params.add(paymentDate);
        }
        if (amount != null && !amount.isEmpty()) {
            double amt = Double.parseDouble(amount);
            sql.append("Amount=?, ");
            params.add(amt);
        }
        if (method != null && !method.isEmpty()) {
            sql.append("Pay_Meth=?, ");
            params.add(method);
        }
        if (guestId != null && !guestId.isEmpty()) {
            int guest = Integer.parseInt(guestId);
            sql.append("Guest_Id=?, ");
            params.add(guest);
        }
        if (reserveId != null && !reserveId.isEmpty()) {
            int reservationID = Integer.parseInt(reserveId);
            sql.append("Reserve_Id=?, ");
            params.add(reservationID);
        }

        if (params.isEmpty()) {
            throw new IllegalArgumentException("No fields provided to update.");
        }

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE Pay_Id = ?");
        int payID = Integer.parseInt(paymentId);
        params.add(payID);


        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No Payment found with ID: " + paymentId);
            }
        }


    }

    public static void deletePayment(String paymentId) throws SQLException {

        String sql = "DELETE FROM Payment WHERE Pay_Id=?";
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, paymentId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No Payment found with ID: " + paymentId);
            }
        }

    }

    public static ObservableList<ObservableList<String>> getAllPayments() throws SQLException {
        ObservableList<ObservableList<String>> paymentsList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Payment";
        try (Connection con = DriverManager.getConnection(URL);
             Statement ps = con.createStatement();
             ResultSet rs = ps.executeQuery(sql)) {

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("Pay_Id"));
                row.add(rs.getString("Pay_Stat"));
                row.add(rs.getString("Pay_Date"));
                row.add(rs.getString("Amount"));
                row.add(rs.getString("Pay_Meth"));
                row.add(rs.getString("Guest_Id"));
                row.add(rs.getString("Reserve_Id"));
                paymentsList.add(row);
            }
        }
        return paymentsList;
    }

}
