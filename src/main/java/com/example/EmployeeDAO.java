package com.example;

import java.sql.*;

public class EmployeeDAO {

    private final DatabaseConnector db;

    public EmployeeDAO(DatabaseConnector db) {
        this.db = db;
    }

    // CREATE
    public void addEmployee(String name, int age, String position, double salary) throws SQLException {
        String sql = "INSERT INTO employees(name, age, position, salary) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = db.prepare(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, position);
            pstmt.setDouble(4, salary);
            pstmt.executeUpdate();
            System.out.println("Employee added.");
        }
    }

    // UPDATE
    public void updateEmployee(int id, String name, int age, String position, double salary) throws SQLException {
        String sql = "UPDATE employees SET name=?, age=?, position=?, salary=? WHERE id=?";

        try (PreparedStatement pstmt = db.prepare(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, position);
            pstmt.setDouble(4, salary);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            System.out.println("Employee updated.");
        }
    }

    // DELETE
    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id=?";

        try (PreparedStatement pstmt = db.prepare(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Employee deleted.");
        }
    }

    // READ (all employees)
    public void getEmployees() throws SQLException {
        String sql = "SELECT * FROM employees";

        try (PreparedStatement pstmt = db.prepare(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("\n--- Employees ---");
            while (rs.next()) {
                System.out.printf(
                        "%d. %s | age: %d | position: %s | salary: %.2f%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("position"),
                        rs.getDouble("salary")
                );
            }
        }
    }
}
