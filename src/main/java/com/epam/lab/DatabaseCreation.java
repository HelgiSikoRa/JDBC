package com.epam.lab;

import com.epam.lab.dao.EmployeeDao;
import com.epam.lab.dto.EmployeeDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static com.epam.lab.Constants.PATH;
import static com.epam.lab.Constants.USER_DIR;

class DatabaseCreation {
    private final static Logger LOG = LogManager.getLogger(DatabaseCreation.class);

    static void createSchemaFromDump() {
        String schema = readSchemaStatement();
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(schema);
            CrudInterface<EmployeeDto> crudInterface = new EmployeeDao();
            List<EmployeeDto> all = crudInterface.findAll();
            LOG.info("Database's been  successfully created\n" + all);
        } catch (SQLException e) {
            LOG.error("SQL Error " + e);
        }
    }

    private static String readSchemaStatement() {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(System.getProperty(USER_DIR) + PATH)));
            bufferedReader.lines().forEach(l -> stringBuilder.append(l).append("\n"));
            LOG.info("Database schema's been read");
        } catch (FileNotFoundException e) {
            LOG.error("File not found" + e);
        }
        return stringBuilder.toString();
    }
}