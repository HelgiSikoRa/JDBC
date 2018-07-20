package com.epam.lab.dao;

import com.epam.lab.CrudInterface;
import com.epam.lab.dto.EmployeeDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao extends AbstractDao implements CrudInterface<EmployeeDto> {
    private final static Logger LOG = LogManager.getLogger(EmployeeDto.class);

    @Override
    public boolean create(EmployeeDto employeeDto) {
        String query = "INSERT INTO employee (emp_no, emp_fname, emp_lname, dept_no, emp_adress) VALUES (?,?,?,?,?);";
        LOG.info("Insert into employee");
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, employeeDto.getEmp_no());
            statement.setString(2, employeeDto.getEmp_fname());
            statement.setString(3, employeeDto.getEmp_lname());
            statement.setString(4, employeeDto.getDept_no());
            statement.setString(5, employeeDto.getEmp_adress());
            int rowsUpdate = statement.executeUpdate();
            if (rowsUpdate >= 1) return true;
        } catch (SQLException e) {
            LOG.error("SQL Error" + e);
            return false;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        String query = "DELETE FROM employee WHERE emp_no = ?";
        LOG.info("Delete from employee");
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            int rowsUpdate = statement.executeUpdate();
            if (rowsUpdate >= 1) return true;
        } catch (SQLException e) {
            LOG.error("Problem with delete project ", e);
            return false;
        }
        return false;
    }

    @Override
    public boolean update(EmployeeDto employeeDto) {
        int id = 2581;
        String adress = "NY";
        String quary = "UPDATE employee SET emp_adress = " + adress + " WHERE emp_no" + id + ";";
        LOG.info("Update employee");
        try {
            PreparedStatement statement = connection.prepareStatement(quary);
            int rowsUpdate = statement.executeUpdate();
            if (rowsUpdate >= 1) return true;
        } catch (SQLException e) {
            LOG.error("SQL Error" + e);
            return false;
        }
        return false;
    }

    @Override
    public EmployeeDto read(long id) {
        String query = "SELECT * FROM employee WHERE emp_no =" + id + ";";
        EmployeeDto employeeDto = new EmployeeDto();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                employeeDto = new EmployeeDto(resultSet);
            }
        } catch (SQLException e) {
            LOG.error("SQL Error" + e);
        }
        return employeeDto;
    }

    @Override
    public List<EmployeeDto> findAll() {
        String query = "SELECT * FROM employee";
        LOG.info("Select all from empliyee");
        List<EmployeeDto> result = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                EmployeeDto employeeDto = new EmployeeDto(resultSet);
                result.add(employeeDto);
            }
        } catch (SQLException e) {
            LOG.error("SQL Error" + e);
        }
        return result;
    }
}