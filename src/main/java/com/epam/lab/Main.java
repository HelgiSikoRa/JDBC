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

public class Main {
    private final static Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        String schema = readCreateSchemaStatement();
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        LOG.info("DB've created");
        CrudInterface<EmployeeDto> crudInterface = new EmployeeDao();
        List<EmployeeDto> all = crudInterface.findAll();
        LOG.info(all);
    }

    private static String readCreateSchemaStatement() {
        StringBuilder stringBuilder = new StringBuilder();
        String PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\dbdump.sql";
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(PATH)));
            bufferedReader.lines().forEach(l -> stringBuilder.append(l).append("\n"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}