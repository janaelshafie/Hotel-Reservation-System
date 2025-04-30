package org.example.demo.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DepartmentModel {
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

    public static void addDepartment(String description, String name) throws SQLException {
        String sql = "INSERT INTO Department (Department_Description, Department_Name) VALUES(?,?)";

        try(Connection con = DriverManager.getConnection(URL);
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, description);
            ps.setString(2, name);
            ps.executeUpdate();
        }

    }

    public static void updateDepartment(String description, String name, String departmentId) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE Department SET ");
        List<Object> params = new ArrayList<>();

        if (description != null && !description.isEmpty()) {
            sql.append("Department_Description = ?, ");
            params.add(description);
        }
        if (name != null && !name.isEmpty()) {
            sql.append("Department_Name = ?, ");
            params.add(name);
        }

        // Remove trailing comma and space
        if (params.isEmpty()) {
            throw new IllegalArgumentException("No fields provided to update.");
        }

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE Department_Id = ?");
        int departmentID = Integer.parseInt(departmentId);
        params.add(departmentID);

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No department found with ID: " + departmentID);
            }
        }

    }

    public static void deleteDepartment(String departmentId) throws SQLException {
        String sql = "DELETE FROM Department WHERE Department_Id = ?";
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, departmentId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No department found with ID: " + departmentId);
            }
        }
    }

    public static ObservableList<ObservableList<String>> getAllDepartments() throws SQLException {
        ObservableList<ObservableList<String>> departmentList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Department";
        try (Connection con = DriverManager.getConnection(URL);
             Statement ps = con.createStatement();
             ResultSet rs = ps.executeQuery(sql)) {

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(String.valueOf(rs.getInt("Department_Id")));
                row.add(rs.getString("Department_Name"));
                row.add(rs.getString("Department_Description"));

                departmentList.add(row);
            }
        }
        return departmentList;
    }

}
