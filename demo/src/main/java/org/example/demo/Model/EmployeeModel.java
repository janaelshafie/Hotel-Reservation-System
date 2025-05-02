package org.example.demo.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {

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

    public static void addEmployee(String firstName, String lastName, String email, String phonenumber, String jobTitle, String gender, String Salary, String DepartmentID) throws SQLException {

        String sql = "INSERT INTO Employee (F_Name, L_name, Email, Phone_Number, Job_Title, Gender, Salary, Department_Id) VALUES(?,?,?,?,?,?,?,?)";

        try(Connection con = DriverManager.getConnection(URL);
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setString(4, phonenumber);
            ps.setString(5, jobTitle);
            ps.setString(6, gender);
            ps.setDouble(7, Double.parseDouble(Salary));
            ps.setInt(8, Integer.parseInt(DepartmentID));

            ps.executeUpdate();
        }

    }

    public static void updateEmployee(String firstName, String lastName, String email, String phonenumber, String jobTitle, String gender, String salary, String departmentID, String employeeId) throws SQLException {

        StringBuilder sql = new StringBuilder("UPDATE Employee SET ");
        List<Object> params = new ArrayList<>();

        if (firstName != null && !firstName.isEmpty()) {
            sql.append("F_Name = ?, ");
            params.add(firstName);
        }
        if (departmentID != null && !departmentID.isEmpty()) {
            int deptID = Integer.parseInt(departmentID);
            sql.append("Department_Id = ?, ");
            params.add(deptID);
        }
        if (lastName != null && !lastName.isEmpty()) {
            sql.append("L_name = ?, ");
            params.add(lastName);
        }
        if (email != null && !email.isEmpty()) {
            sql.append("Email = ?, ");
            params.add(email);
        }
        if (phonenumber != null && !phonenumber.isEmpty()) {
            sql.append("Phone_Number = ?, ");
            params.add(phonenumber);
        }
        if (jobTitle != null && !jobTitle.isEmpty()) {
            sql.append("Job_Title = ?, ");
            params.add(jobTitle);
        }
        if (salary != null && !salary.isEmpty()) {
            double salaryDouble = Double.parseDouble(salary);
            sql.append("Salary = ?, ");
            params.add(salaryDouble);
        }
        if (gender != null && !gender.isEmpty()) {
            sql.append("Gender = ?, ");
            params.add(gender);
        }

        if (params.isEmpty()) {
            throw new IllegalArgumentException("No fields provided to update.");
        }

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE Employee_Id = ?");
        int employee_ID = Integer.parseInt(employeeId);
        params.add(employee_ID);

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No Employee found with ID: " + employeeId);
            }
        }

    }

    public static void deleteEmployee(String employeeId) throws SQLException {

        String sql = "DELETE FROM Employee WHERE Employee_Id = ?";
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, employeeId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No Employee found with ID: " + employeeId);
            }
        }

    }

    public static ObservableList<ObservableList<String>> getAllEmployees() throws SQLException {
        ObservableList<ObservableList<String>> employeesList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Employee";
        try (Connection con = DriverManager.getConnection(URL);
             Statement ps = con.createStatement();
             ResultSet rs = ps.executeQuery(sql)) {

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("Employee_Id"));
                row.add(rs.getString("F_Name"));
                row.add(rs.getString("Department_Id"));
                row.add(rs.getString("L_Name"));
                row.add(rs.getString("Email"));
                row.add(rs.getString("Phone_Number"));
                row.add(rs.getString("Job_Title"));
                row.add(rs.getString("Salary"));
                row.add(rs.getString("Gender"));
                employeesList.add(row);
            }
        }
        return employeesList;
    }

}
