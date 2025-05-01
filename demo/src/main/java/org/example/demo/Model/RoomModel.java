package org.example.demo.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomModel {
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

    public static void addRoom(String roomNumber , String status , String roomTypeID){
        String sql = "INSERT INTO Room (Room_Number, Room_Status, Room_Type_Id) VALUES(?,?,?)";
        try(Connection con = DriverManager.getConnection(URL);
            PreparedStatement ps = con.prepareStatement(sql)){
            if(!roomTypeID.isEmpty()){
                ps.setInt(1, Integer.parseInt(roomNumber));
                ps.setString(2, status);
                ps.setInt(3, Integer.parseInt(roomTypeID));
            }else{
                ps.setInt(1, Integer.parseInt(roomNumber));
                ps.setString(2, status);
                ps.setString(3, null);
            }

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addRoomType(String roomName , String capacity , String nightPrice){
        String sql = "INSERT INTO RoomType (Room_Name, Capacity, Night_Price) VALUES(?,?,?)";
        try(Connection con = DriverManager.getConnection(URL);
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, roomName);
            ps.setString(2, capacity);
            ps.setDouble(3, Double.parseDouble(nightPrice));

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateRoom(String roomNumber , String status , String roomTypeID) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE Room SET ");
        List<Object> params = new ArrayList<>();

        if (status != null && !status.isEmpty()) {
            sql.append("Room_Status = ?, ");
            params.add(status);
        }
        if (roomTypeID != null && !roomTypeID.isEmpty()) {
            sql.append("Room_Type_Id = ?, ");
            params.add(roomTypeID);
        }
        if(roomNumber==null && !params.isEmpty()) {
            throw new SQLException("Room Number cannot be empty.");
        }

        if (params.isEmpty()) {
            throw new IllegalArgumentException("No fields provided to update.");
        }

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE Room_Number = ?");
        int Room_Number = Integer.parseInt(roomNumber);
        params.add(Room_Number);

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No room number found with ID: " + roomNumber);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateRoomType(String roomName , String capacity , String nightPrice , String roomTypeID) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE RoomType SET ");
        List<Object> params = new ArrayList<>();

        if (roomName != null && !roomName.isEmpty()) {
            sql.append("Room_Name = ?, ");
            params.add(roomName);
        }
        if (capacity != null && !capacity.isEmpty()) {
            sql.append("Capacity = ?, ");
            params.add(capacity);
        }
        if (nightPrice != null && !nightPrice.isEmpty()) {
            sql.append("Night_Price = ?, ");
            params.add(nightPrice);
        }

        if(roomTypeID==null && !params.isEmpty()) {
            throw new SQLException("Room Type ID cannot be empty.");
        }

        if (params.isEmpty()) {
            throw new IllegalArgumentException("No fields provided to update.");
        }

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE Room_Type_Id = ?");
        int Room_Type_Id = Integer.parseInt(roomTypeID);
        params.add(Room_Type_Id);

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No room type id found with ID: " + roomTypeID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteRoom(String roomNumber){
        String sql = "DELETE FROM Room WHERE Room_Number = ?";
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, roomNumber);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No room found with ID: " + roomNumber);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteRoomType(String roomTypeID){
        String sql = "DELETE FROM RoomType WHERE Room_Type_Id = ?";
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, roomTypeID);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No room type id found with ID: " + roomTypeID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<ObservableList<String>> getAllRooms() throws SQLException {
        ObservableList<ObservableList<String>> roomList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Room";
        try (Connection con = DriverManager.getConnection(URL);
             Statement ps = con.createStatement();
             ResultSet rs = ps.executeQuery(sql)) {

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(String.valueOf(rs.getInt("Room_Number")));
                row.add(rs.getString("Room_Status"));
                row.add(String.valueOf(rs.getInt("Room_Type_Id")));

                roomList.add(row);
            }
        }
        return roomList;
    }

    public static ObservableList<ObservableList<String>> getAllRoomTypes() throws SQLException {
        ObservableList<ObservableList<String>> roomTypeList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM RoomType";
        try (Connection con = DriverManager.getConnection(URL);
             Statement ps = con.createStatement();
             ResultSet rs = ps.executeQuery(sql)) {

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(String.valueOf(rs.getInt("Room_Type_Id")));
                row.add(rs.getString("Room_Name"));
                row.add(rs.getString("Capacity"));
                row.add(String.valueOf(rs.getDouble("Night_price")));

                roomTypeList.add(row);
            }
        }
        return roomTypeList;
    }

}
