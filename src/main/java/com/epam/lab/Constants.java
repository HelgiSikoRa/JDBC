package com.epam.lab;

public class Constants {
    static final String URL = "jdbc:mysql://localhost:3306/sample?useUnicode=true&useSSL=false&useJDBCCompliantTimezoneShift=true + &useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String USER_NAME = "root";
    static final String PASSWORD = "root";

    static final String USER_DIR = "user.dir";
    static final String PATH = "\\src\\main\\resources\\dbdump.sql";

    public static final String SQL_FIND_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE emp_no = ?;";
    public static final String SQL_INSERT_EMPLOYEE = "INSERT INTO employee (emp_no, emp_fname, emp_lname, dept_no, emp_adress) VALUES (?,?,?,?,?);";
    public static final String SQL_DELETE_EMPLOYEE = "DELETE FROM employee WHERE emp_no = ?;";
    public static final String SQL_FIND_ALL_EMPLOYEE = "SELECT * FROM employee";
    public static final String SQL_FIND_EMPLOYEE_BY_DEPARTMANT = "SELECT * FROM employee WHERE dept_no = ?;";
    public static final String SQL_DISABLE_FOREIGN_KEY = "SET FOREIGN_KEY_CHECKS = 0;";
    public static final String SQL_ENABLE_FOREIGN_KEY = "SET FOREIGN_KEY_CHECKS = 1;";

    public static final String SQL_INSERT_DEPARTMENT = "INSERT INTO department (dept_no, dept_name, location) VALUES (?,?,?);";
    public static final String SQL_FIND_DEPARTMENT_BY_ID = "SELECT * FROM department WHERE dept_no = ?;";
    public static final String SQL_DELETE_DEPARTMENT = "DELETE FROM department WHERE dept_no = ?;";
    public static final String SQL_FIND_ALL_DEPARTMENT = "SELECT * FROM department";

    private Constants() {
    }
}