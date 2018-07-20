package com.epam.lab;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class DatabaseConnection {
    private final static Logger LOG = LogManager.getLogger(DatabaseConnection.class);
    private final String URL = "jdbc:mysql://localhost:3306/sample?serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "root";
    private Connection connection = null;
    private static DatabaseConnection instance = null;

    public Connection getConnection() {
        return connection;
    }

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            LOG.info("Successfully connected");
        } catch (ClassNotFoundException | SQLException e) {
            LOG.error("Connection exception" + e);
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null)
            instance = new DatabaseConnection();
        return instance;
    }
}