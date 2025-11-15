package com.example;

import java.sql.*;

public class DatabaseConnector {

    private static final String URL =
            "jdbc:mysql://localhost:3306/company?useSSL=false&serverTimezone=UTC";

    private static final String USER = "employees";
    private static final String PASSWORD = "123123123";

    private Connection connection;

    public void connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }

        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connected to MySQL.");
    }

    public void createTable() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS employees (
                    id INT NOT NULL AUTO_INCREMENT,
                    name VARCHAR(255) NOT NULL,
                    age INT NOT NULL,
                    position VARCHAR(255) NOT NULL,
                    salary DECIMAL(10,2) NOT NULL,
                    PRIMARY KEY (id)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
                """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created (or already exists).");
        }
    }

    public PreparedStatement prepare(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("Connection closed.");
        }
    }
}
