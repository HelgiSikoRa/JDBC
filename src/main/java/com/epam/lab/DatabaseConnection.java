package com.epam.lab;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

import static com.epam.lab.Constants.PASSWORD;
import static com.epam.lab.Constants.URL;
import static com.epam.lab.Constants.USER_NAME;

public class DatabaseConnection {
    private final static Logger LOG = LogManager.getLogger(DatabaseConnection.class);
    private Connection connection = null;
    private static DatabaseConnection instance = null;

    public Connection getConnection() {
        return connection;
    }

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            LOG.info("Connecting to DataBase... - successfully connected");
        } catch (ClassNotFoundException | SQLException e) {
            LOG.error("Connecting to DataBase... connection error" + e);
        }
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null)
            instance = new DatabaseConnection();
        else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}