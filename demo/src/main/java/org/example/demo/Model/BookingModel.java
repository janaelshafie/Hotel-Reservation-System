package org.example.demo.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingModel {

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

    public static void addBookingDetails(String bookingId,String guestId, String dateTime, String totalPrice) throws SQLException {

        String sql = "INSERT INTO BookingDetails (Booking_Id, Guest_Id, Date_Time, Total_Price) VALUES(?,?,?,?)";

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(bookingId));
            ps.setInt(2, Integer.parseInt(guestId));
            ps.setString(3, dateTime);
            ps.setDouble(4, Double.parseDouble(totalPrice));

            ps.executeUpdate();
        }

    }

    public static void updateBookingDetails(String bookingId, String guestId, String dateTime, String totalPrice) throws SQLException {

        StringBuilder sql = new StringBuilder("UPDATE BookingDetails SET ");
        List<Object> params = new ArrayList<>();


        if (dateTime != null && !dateTime.isEmpty()) {
            sql.append("Date_Time = ?, ");
            params.add(dateTime);
        }

        if (totalPrice != null && !totalPrice.isEmpty()) {
            double totalPriceDouble = Double.parseDouble(totalPrice);
            sql.append("Total_Price = ?, ");
            params.add(totalPriceDouble);
        }

        sql.setLength(sql.length() - 2); // Remove last comma
        sql.append(" WHERE Guest_Id = ? AND Booking_Id = ?");
        int guestID = Integer.parseInt(guestId);
        int bookingID = Integer.parseInt(bookingId);
        params.add(guestID);
        params.add(bookingID);

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No Booking found with ID: " + bookingId);
            }
        }

    }

    public static void deleteBookingDetails(String bookingId, String guestId) throws SQLException {

        String sql = "DELETE FROM BookingDetails WHERE Booking_Id = ? AND Guest_Id = ?";
        List<Object> params = new ArrayList<>();
        params.add(bookingId);
        params.add(guestId);
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No Booking found with ID: " + bookingId);
            }
        }

    }

    public static void addOffers(String bookingId, String guestId, String serviceId) throws SQLException {

        String sql = "INSERT INTO Offers (Booking_Id, Guest_Id, Service_Id) VALUES(?,?,?)";

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(bookingId));
            ps.setInt(2, Integer.parseInt(guestId));
            ps.setInt(3, Integer.parseInt(serviceId));

            ps.executeUpdate();
        }

    }

    public static void deleteOffers(String bookingId, String guestId, String serviceId) throws SQLException {

        String sql = "DELETE FROM Offers WHERE Booking_Id = ? AND Guest_Id = ? AND Service_Id = ?";
        List<Object> params = new ArrayList<>();
        params.add(bookingId);
        params.add(guestId);
        params.add(serviceId);
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No Offer found with ID: " + bookingId);
            }
        }

    }

    public static void addServiceBooking(String guestId, String employeeId) throws SQLException {

        String sql = "INSERT INTO ServiceBooking (Guest_Id, Employee_Id) VALUES(?,?)";

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(guestId));
            ps.setInt(2, Integer.parseInt(employeeId));

            ps.executeUpdate();
        }

    }

    public static void updateServiceBooking(String bookingId, String guestId, String employeeId) throws SQLException {

        StringBuilder sql = new StringBuilder("UPDATE ServiceBooking SET ");
        List<Object> params = new ArrayList<>();


        if (employeeId != null && !employeeId.isEmpty()) {
            int employeeID = Integer.parseInt(employeeId);
            sql.append("Employee_Id = ?, ");
            params.add(employeeID);
        }

        if (params.isEmpty()) {
            throw new IllegalArgumentException("No fields provided to update.");
        }

        sql.setLength(sql.length() - 2); // Remove last comma
        sql.append(" WHERE Booking_Id = ? AND Guest_Id = ?");
        int bookingID = Integer.parseInt(bookingId);
        int guestID = Integer.parseInt(guestId);
        params.add(bookingID);
        params.add(guestID);

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No Service Booking found with ID: " + bookingId);
            }
        }

    }

    public static void deleteServiceBooking(String bookingId, String guestId, String employeeId) throws SQLException {

        String sql = "DELETE FROM ServiceBooking WHERE Booking_Id = ? AND Guest_Id = ? AND Employee_Id = ?";
        List<Object> params = new ArrayList<>();
        params.add(bookingId);
        params.add(guestId);
        params.add(employeeId);
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No Service Booking found with ID: " + bookingId);
            }
        }
    }

    public static ObservableList<ObservableList<String>> getAllBookingDetails() throws SQLException {
        ObservableList<ObservableList<String>> bookingDetailsList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM BookingDetails";
        try (Connection con = DriverManager.getConnection(URL);
             Statement ps = con.createStatement();
             ResultSet rs = ps.executeQuery(sql)) {

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("Booking_Id"));
                row.add(rs.getString("Guest_Id"));
                row.add(rs.getString("Date_Time"));
                row.add(rs.getString("Total_Price"));
                bookingDetailsList.add(row);
            }
        }
        return bookingDetailsList;
    }

    public static ObservableList<ObservableList<String>> getAllOffers() throws SQLException {
        ObservableList<ObservableList<String>> offersList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Offers";
        try (Connection con = DriverManager.getConnection(URL);
             Statement ps = con.createStatement();
             ResultSet rs = ps.executeQuery(sql)) {

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("Booking_Id"));
                row.add(rs.getString("Service_Id"));
                row.add(rs.getString("Guest_Id"));
                offersList.add(row);
            }
        }
        return offersList;
    }

    public static ObservableList<ObservableList<String>> getAllServiceBookings() throws SQLException {
        ObservableList<ObservableList<String>> serviceBookingList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM ServiceBooking";
        try (Connection con = DriverManager.getConnection(URL);
             Statement ps = con.createStatement();
             ResultSet rs = ps.executeQuery(sql)) {

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("Booking_Id"));
                row.add(rs.getString("Guest_Id"));
                row.add(rs.getString("Employee_Id"));
                serviceBookingList.add(row);
            }
        }
        return serviceBookingList;
    }


}
