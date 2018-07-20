package com.epam.lab.dao;

import com.epam.lab.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

abstract class AbstractDao {
    private DatabaseConnection databaseConnection;
    Connection connection;
    Statement statement = null;

    AbstractDao() {
        this.databaseConnection = DatabaseConnection.getInstance();
        connection = databaseConnection.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}