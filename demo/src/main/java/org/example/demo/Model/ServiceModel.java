package org.example.demo.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceModel {
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

    public static void addService(String name , String category , String description , String price , String availability){
        String sql = "INSERT INTO Service (Service_Name, Service_category, Availability, Price, Description) VALUES(?,?,?,?,?)";
        try(Connection con = DriverManager.getConnection(URL);
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setString(3, availability);
            ps.setDouble(4, Double.parseDouble(price));
            ps.setString(5, description);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateService(String name , String category , String description , String price , String availability , String serviceId){
        StringBuilder sql = new StringBuilder("UPDATE Service SET ");
        List<Object> params = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            sql.append("service_Name = ?, ");
            params.add(name);
        }
        if (category != null && !category.isEmpty()) {
            sql.append("service_category = ?, ");
            params.add(category);
        }
        if (description != null && !description.isEmpty()) {
            sql.append("Description = ?, ");
            params.add(description);
        }
        if (price != null && !price.isEmpty()) {
            double priceDouble = Double.parseDouble(price);
            sql.append("Price = ?, ");
            params.add(priceDouble);
        }
        if (availability != null && !availability.isEmpty()) {
            sql.append("Availability = ?, ");
            params.add(availability);
        }

        // Remove trailing comma and space
        if (params.isEmpty()) {
            throw new IllegalArgumentException("No fields provided to update.");
        }

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE service_Id = ?");
        int ServiceID = Integer.parseInt(serviceId);
        params.add(ServiceID);

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No service found with ID: " + serviceId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteService(String serviceId){
        String sql = "DELETE FROM Service WHERE service_Id = ?";
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, serviceId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No service found with ID: " + serviceId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<ObservableList<String>> getAllServices() throws SQLException {
        ObservableList<ObservableList<String>> serviceList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Service";
        try (Connection con = DriverManager.getConnection(URL);
             Statement ps = con.createStatement();
             ResultSet rs = ps.executeQuery(sql)) {

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(String.valueOf(rs.getInt("service_Id")));
                row.add(rs.getString("service_name"));
                row.add(rs.getString("service_category"));
                row.add(rs.getString("Availability"));
                row.add(String.valueOf(rs.getDouble("Price")));
                row.add(rs.getString("Description"));


                serviceList.add(row);
            }
        }
        return serviceList;
    }
}
