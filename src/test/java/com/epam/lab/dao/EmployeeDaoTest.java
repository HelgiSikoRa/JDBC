package com.epam.lab.dao;

import com.epam.lab.CrudInterface;
import com.epam.lab.DatabaseConnection;
import com.epam.lab.dto.EmployeeDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;
import java.util.List;

public class EmployeeDaoTest {
    private final static Logger LOG = LogManager.getLogger(EmployeeDto.class);

    private final String fname = "Test";
    private final String lname = "Ранодвы";
    private final String dep_no = "d2";
    private final String adress = "NY";
    private final int databaseSize = 7;
    private final long getId = 2581;
    private final long deleteId = 1532285887; //replace randomId to this when run single test "shouldDeleteFromEmployee()"
    private static final int divisor = 1000;
    private static long randomId;
    private static final String fieldName = "emp_fname";
    private static final String srcName = "Elke";
    private final String newName = "New Name Updated";
    private final String currentDepartmentNumber = "d3";
    private final String newDepartmentNumber = "d2";

    @BeforeClass
    public static void generateId() {
        randomId = System.currentTimeMillis() / divisor;
    }

    @Test
    public void shouldCreateEmployee() {
        LOG.info("\nshould Create Employee()");
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmp_no(randomId)
                .setEmp_fname(fname)
                .setEmp_lname(lname)
                .setDept_no(dep_no)
                .setEmp_adress(adress);
        CrudInterface<EmployeeDto> crudInterface = new EmployeeDao();
        crudInterface.create(employeeDto);
        EmployeeDto actualResult = crudInterface.findEmployeeById(randomId);
        Assert.assertEquals(actualResult.getEmp_fname(), employeeDto.getEmp_fname());
    }

    @Test
    public void shouldGetEmployeeById() throws SQLException {
        LOG.info("\nshould Get Employee By Id()");
        EmployeeDto actualEmployee = new EmployeeDto();
        CrudInterface<EmployeeDto> crudInterface = new EmployeeDao();
        EmployeeDto expectedEmployee = crudInterface.findEmployeeById(getId);
        String query = String.format("SELECT * FROM employee WHERE emp_no = %d;", getId);
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            actualEmployee = new EmployeeDto(resultSet);
        }
        Assert.assertEquals(expectedEmployee.toString(), actualEmployee.toString());
    }

    @Test
    public void shouldUpdateData() {
        LOG.info("\nshould Update Data()");
        CrudInterface<EmployeeDto> crudInterface = new EmployeeDao();
        boolean result = crudInterface.update(fieldName, newName, 2581);
        Assert.assertTrue(result);
    }

    @Test
    public void shouldDeleteFromEmployee() {
        LOG.info("\nshould Delete From Employee()");
        CrudInterface<EmployeeDto> crudInterface = new EmployeeDao();
        boolean result = crudInterface.delete(randomId);
        Assert.assertTrue(result);
    }

    @Test
    public void shouldGetAllEmployees() {
        LOG.info("\nshould Get All Employees()");
        CrudInterface<EmployeeDto> crudInterface = new EmployeeDao();
        List<EmployeeDto> all = crudInterface.findAll();
        Assert.assertEquals("Actual DB's size equals expected size.", all.size(), databaseSize);
    }

    @Test
    public void shouldTransferAllEmployeeFromCurrentDepartmentToAnotherOne() {
        LOG.info("\nshould Transfer All Employee From Current Department To Another One ()");
        CrudInterface<EmployeeDto> crudInterface = new EmployeeDao();
        boolean result = ((EmployeeDao) crudInterface).
                transferAllEmployeeFromCurrentDepartmentToAnotherOne(currentDepartmentNumber, newDepartmentNumber);
        Assert.assertTrue(result);
    }

    @AfterClass
    public static void cleanUp() {
        LOG.info("\ncleanUp()all changes after test");
        CrudInterface<EmployeeDto> crudInterface = new EmployeeDao();
        crudInterface.update(fieldName, srcName, 2581);
    }
}