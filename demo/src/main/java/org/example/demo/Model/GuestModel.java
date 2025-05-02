package org.example.demo.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GuestModel {

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

    public static void addGuest(String firstName, String lastName, String email, String gender, String nationality, String SSN , java.sql.Date birthdate, String phonenumber , String address) throws SQLException {
        String sql = "INSERT INTO Guest (F_Name, L_name, Email, Gender, Nationality, SSN, Birth_Date, Phone_Num, G_Address) VALUES(?,?,?,?,?,?,?,?,?)";

        try(Connection con = DriverManager.getConnection(URL);
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setString(4, gender);
            ps.setString(5, nationality);
            ps.setString(6, SSN);
            ps.setDate(7, birthdate);
            ps.setString(8, phonenumber);
            ps.setString(9, address);

            ps.executeUpdate();
        }

    }
    public static void updateGuest(String firstName, String lastName, String email, String gender, String nationality, String SSN , java.sql.Date birthdate, String phonenumber , String address , String guestId) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE Guest SET ");
        List<Object> params = new ArrayList<>();

        if (firstName != null && !firstName.isEmpty()) {
            sql.append("F_Name = ?, ");
            params.add(firstName);
        }
        if (lastName != null && !lastName.isEmpty()) {
            sql.append("L_name = ?, ");
            params.add(lastName);
        }
        if (email != null && !email.isEmpty()) {
            sql.append("Email = ?, ");
            params.add(email);
        }
        if (gender != null && !gender.isEmpty()) {
            sql.append("Gender = ?, ");
            params.add(gender);
        }
        if (nationality != null && !nationality.isEmpty()) {
            sql.append("Nationality = ?, ");
            params.add(nationality);
        }
        if (SSN != null && !SSN.isEmpty()) {
            sql.append("SSN = ?, ");
            params.add(SSN);
        }
        if (birthdate != null) {
            sql.append("Birth_Date = ?, ");
            params.add(birthdate);
        }
        if (phonenumber != null && !phonenumber.isEmpty()) {
            sql.append("Phone_Num = ?, ");
            params.add(phonenumber);
        }
        if (address != null && !address.isEmpty()) {
            sql.append("G_Address = ?, ");
            params.add(address);
        }

        // Remove trailing comma and space
        if (params.isEmpty()) {
            throw new IllegalArgumentException("No fields provided to update.");
        }

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE Guest_Id = ?");
        int guestID = Integer.parseInt(guestId);
        params.add(guestID);

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No guest found with ID: " + guestId);
            }
        }

    }
    public static void deleteGuest(String guestId) throws SQLException {
        String sql = "DELETE FROM Guest WHERE Guest_Id = ?";
        try (Connection con = DriverManager.getConnection(URL);
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, guestId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No guest found with ID: " + guestId);
            }
        }
    }

    public static ObservableList<ObservableList<String>> getAllGuests() throws SQLException {
        ObservableList<ObservableList<String>> guestList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Guest";
        try (Connection con = DriverManager.getConnection(URL);
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(sql)) {

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(String.valueOf(rs.getInt("Guest_Id")));
                row.add(rs.getString("F_Name"));
                row.add(rs.getString("L_name"));
                row.add(rs.getString("Email"));
                row.add(rs.getString("Gender"));
                row.add(rs.getString("Nationality"));
                row.add(String.valueOf(rs.getInt("SSN")));
                row.add(rs.getString("Birth_date"));
                row.add(rs.getString("Phone_Num"));
                row.add(rs.getString("G_Address"));


                guestList.add(row);
            }
        }
        return guestList;
    }

}
