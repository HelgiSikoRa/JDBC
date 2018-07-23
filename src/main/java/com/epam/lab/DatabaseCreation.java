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
            statement.executeUpdate(s);
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

    public String s = "CREATE DATABASE  IF NOT EXISTS `sample`;\n" +
            "USE `sample`;\n" +
            " SET NAMES utf8 ;\n" +
            "DROP TABLE IF EXISTS `department`;\n" +
            " SET character_set_client = utf8mb4 ;\n" +
            "CREATE TABLE `department` (\n" +
            "  `dept_no` varchar(20) NOT NULL,\n" +
            "  `dept_name` varchar(45) DEFAULT NULL,\n" +
            "  `location` varchar(60) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`dept_no`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n" +
            "\n" +
            "LOCK TABLES `department` WRITE;\n" +
            "INSERT INTO `department` VALUES ('d1','research','Dallas'),('d2','accounting','Seattle'),('d3','marketing','Dallas');\n" +
            "UNLOCK TABLES;\n" +
            "\n" +
            "DROP TABLE IF EXISTS `employee`;\n" +
            " SET character_set_client = utf8mb4 ;\n" +
            "CREATE TABLE `employee` (\n" +
            "  `emp_no` int(11) NOT NULL,\n" +
            "  `emp_fname` varchar(45) NOT NULL,\n" +
            "  `emp_lname` varchar(60) NOT NULL,\n" +
            "  `dept_no` varchar(20) DEFAULT NULL,\n" +
            "  `emp_adress` varchar(60) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`emp_no`),\n" +
            "  KEY `dept_no` (`dept_no`),\n" +
            "  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`dept_no`) REFERENCES `department` (`dept_no`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n" +
            "\n" +
            "LOCK TABLES `employee` WRITE;\n" +
            "INSERT INTO `employee` VALUES (2581,'Elke','Hansel','d2','New York'),(9031,'Elsa','Bertoni','d2','Seattle'),(10102,'Ann','Jones','d3','Dallas'),(18316,'John','Barrimore','d1','Dallas'),(25348,'Matthew','Smith','d3','New York'),(28559,'Sybill','Moser','d1','Seattle'),(29346,'James','James','d2','Dallas');\n" +
            "UNLOCK TABLES;\n" +
            "\n" +
            "DROP TABLE IF EXISTS `project`;\n" +
            " SET character_set_client = utf8mb4 ;\n" +
            "CREATE TABLE `project` (\n" +
            "  `project_no` varchar(20) NOT NULL,\n" +
            "  `project_name` varchar(45) NOT NULL,\n" +
            "  `budget` double DEFAULT NULL,\n" +
            "  PRIMARY KEY (`project_no`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n" +
            "\n" +
            "LOCK TABLES `project` WRITE;\n" +
            "INSERT INTO `project` VALUES ('p1','Apollo',120000),('p2','Gemini',95000),('p3','Mercury',186500);\n" +
            "UNLOCK TABLES;\n" +
            "\n" +
            "DROP TABLE IF EXISTS `works_on`;\n" +
            " SET character_set_client = utf8mb4 ;\n" +
            "CREATE TABLE `works_on` (\n" +
            "  `ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `emp_no` int(11) DEFAULT NULL,\n" +
            "  `project_no` varchar(45) DEFAULT NULL,\n" +
            "  `job` varchar(60) DEFAULT NULL,\n" +
            "  `enter_data` date DEFAULT NULL,\n" +
            "  PRIMARY KEY (`ID`),\n" +
            "  KEY `emp_no` (`emp_no`),\n" +
            "  KEY `project_no` (`project_no`),\n" +
            "  CONSTRAINT `works_on_ibfk_1` FOREIGN KEY (`emp_no`) REFERENCES `employee` (`emp_no`),\n" +
            "  CONSTRAINT `works_on_ibfk_2` FOREIGN KEY (`project_no`) REFERENCES `project` (`project_no`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n" +
            "\n" +
            "LOCK TABLES `works_on` WRITE;\n" +
            "INSERT INTO `works_on` VALUES (1,10102,'p1','analyst','2006-10-01'),(2,10102,'p3','manager','2008-01-01'),(3,25348,'p2','clerk','2007-02-15'),(4,18316,'p2',NULL,'2007-06-01'),(5,29346,'p2',NULL,'2006-12-15'),(6,2581,'p3','analyst','2007-10-15'),(7,9031,'p1','manager','2007-04-15'),(8,28559,'p1',NULL,'2007-08-01'),(9,28559,'p2','clerk','2008-02-01'),(10,9031,'p3','clerk','2006-11-15'),(12,29346,'p1','clerk','2007-01-04');\n" +
            "UNLOCK TABLES;\n";
}