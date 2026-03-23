package com.example.newJDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Centralized DB config (easy to change later / move to config file)
    private static final String URL =
            "jdbc:mysql://localhost:3306/jdbcLearning?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    // 🔹 Get Connection
    public static Connection getConnection() throws SQLException {
        /*
         * No Class.forName() here
         *
         * WHY?
         * - JDBC 4.0+ uses SPI (Service Provider Interface)
         * - Driver auto-loaded from:
         *   META-INF/services/java.sql.Driver
         *
         * DriverManager internally:
         * - Finds matching driver
         * - Delegates connection creation
         */
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}