package com.developer.sportbooking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String url = "jdbc:mysql://localhost:3306/sport_app";
    private static final String username = "root";
    private static final String password = "userroot";

    public static Connection initDB() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }

        try (Connection initialConnection = DriverManager.getConnection(url, username, password)) {
            initialConnection.createStatement().executeUpdate("CREATE DATABASE IF NOT EXISTS project");
            System.out.println("Database is created or already exists.");
        }

        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connected to the database project");
        return connection;
    }
}
