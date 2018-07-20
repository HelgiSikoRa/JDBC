package com.epam.lab.dao;

import com.epam.lab.CrudInterface;
import com.epam.lab.DatabaseConnection;
import com.epam.lab.dto.EmployeeDto;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EmployeeDaoTest {
    private final String fname = "Test";
    private final String lname = "TestLast";
    private final String dep_no = "d2";
    private final String adress = "NY";
    private final int id = 2581;


    @Test
    public void shouldFindEmployee() {
        CrudInterface<EmployeeDto> crudInterface = new EmployeeDao();
        List<EmployeeDto> all = crudInterface.findAll();
        Assert.assertTrue(all.size() > 5);
    }

    @Test
    public void shouldCreateEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        long id = System.currentTimeMillis();
        employeeDto.setEmp_no(id)
                .setEmp_fname(fname)
                .setEmp_lname(lname)
                .setDept_no(dep_no)
                .setEmp_adress(adress);
        CrudInterface<EmployeeDto> crudInterface = new EmployeeDao();
        crudInterface.create(employeeDto);
        EmployeeDto actualeResult = crudInterface.read(id);
        Assert.assertEquals(actualeResult.getEmp_fname(), employeeDto.getEmp_lname());
    }

    @Test
    public void shouldUpdateData() throws SQLException {
        EmployeeDto employeeDto = new EmployeeDto();
        String quary = "UPDATE employee SET emp_adress = " + adress + " WHERE emp_no" + id + ";";
        Connection connection = DatabaseConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(quary);
        statement.executeUpdate();
        CrudInterface<EmployeeDto> crudInterface = new EmployeeDao();
        EmployeeDto actualeResult = crudInterface.read(id);
        Assert.assertEquals(actualeResult.getEmp_fname(), employeeDto.getEmp_lname());
    }

    @Test
    public void shouldDeleteFromEmployee() throws SQLException {
        CrudInterface<EmployeeDto> crudInterface = new EmployeeDao();
        List<EmployeeDto> all = crudInterface.findAll();
        int curentSize = all.size();
        String query = "DELETE FROM employee WHERE emp_no = " + id + ";";
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement statement = connection.prepareStatement(query);
        statement.execute(query);
        Assert.assertTrue(all.size() < curentSize);
    }

    @AfterClass
    public void cleanUp() throws SQLException {
        String query = "DELETE FROM employee WHERE emp_fmane = " + fname + ";";
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement statement = connection.prepareStatement(query);
        statement.execute(query);
    }
}