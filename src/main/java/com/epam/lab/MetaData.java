package com.epam.lab;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

import static com.epam.lab.Constants.SQL_FIND_ALL_EMPLOYEE;

class MetaData {
    private final static Logger LOG = LogManager.getLogger(MetaData.class);
    private DatabaseConnection databaseConnection;
    private Connection connection = null;
    private Statement statement = null;

    MetaData() {
        try {
            this.databaseConnection = DatabaseConnection.getInstance();
            connection = databaseConnection.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            LOG.error("Error connection" + e.getMessage());
        }
    }

    void readDatabaseProductNameVersion() {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            String productName = meta.getDatabaseProductName();
            String productVersion = meta.getDatabaseProductVersion();
            LOG.info("> DateBase name: " + productName);
            LOG.info("> DataBase version: " + productVersion);
        } catch (SQLException e) {
            LOG.error("Error while fetching metadata" + e.getMessage());
        }
    }

    void readTablesName() {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            String catalog = "sample", schemaPattern = null, tableNamePattern = "%";
            String[] types = {"TABLE"};
            LOG.info(String.format("\n> There are tables from %s schema", catalog));
            ResultSet rsTables = meta.getTables(catalog, schemaPattern, tableNamePattern, types);
            while (rsTables.next()) {
                String tableName = rsTables.getString(3);
                LOG.info("- Table: " + tableName);
            }
        } catch (SQLException e) {
            LOG.error("Error while fetching metadata" + e.getMessage());
        }
    }

    void readColumnsNumber() {
        try {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_EMPLOYEE);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numberOfColumns = resultSetMetaData.getColumnCount();
            LOG.info("\n> Number of columns: " + numberOfColumns);
        } catch (SQLException e) {
            LOG.error("Error while fetching metadata" + e.getMessage());
        }
    }

    void readMetaDataAboutColumns() {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            String catalog = null, schemaPattern = null, columnNamePattern = null;
            String tableName = "employee";
            ResultSet resultSet = meta.getColumns(catalog, schemaPattern, tableName, columnNamePattern);
            LOG.info(String.format("> Table '%s':", tableName));
            while (resultSet.next()) {
                String columnName = resultSet.getString("COLUMN_NAME");
                String isNullable = resultSet.getString("IS_NULLABLE");
                String is_autoIncrment = resultSet.getString("IS_AUTOINCREMENT");
                LOG.info(String.format("- %s, IS_NULLABLE - %s, IS_AUTOINCREMENT - %s", columnName, isNullable, is_autoIncrment));
            }
        } catch (SQLException e) {
            LOG.error("Error while fetching metadata" + e.getMessage());
        }
    }

    void getPrimaryKey() {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            String catalog = null, schemaPattern = null, columnNamePattern = null;
            String tableName = "employee";
            ResultSet metaPrimaryKeys = meta.getPrimaryKeys(catalog, schemaPattern, tableName);
            while (metaPrimaryKeys.next()) {
                String primaryKeyColumn = metaPrimaryKeys.getString("COLUMN_NAME");
                LOG.info("\n> Primary Key Column: " + primaryKeyColumn);
            }
        } catch (SQLException e) {
            LOG.error("Error while fetching metadata" + e.getMessage());
        }
    }
}