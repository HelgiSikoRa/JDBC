package com.epam.lab.dao;

import com.epam.lab.CrudInterface;
import com.epam.lab.DAOException;
import com.epam.lab.dto.EmployeeDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.epam.lab.Constants.*;

public class EmployeeDao extends AbstractDao implements CrudInterface<EmployeeDto> {
    private final static Logger LOG = LogManager.getLogger(EmployeeDto.class);

    @Override
    public boolean create(EmployeeDto employeeDto) throws DAOException {
        LOG.info("Insert into employee");
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_EMPLOYEE);
            statement.setLong(1, employeeDto.getEmp_no());
            statement.setString(2, employeeDto.getEmp_fname());
            statement.setString(3, employeeDto.getEmp_lname());
            statement.setString(4, employeeDto.getDept_no());
            statement.setString(5, employeeDto.getEmp_adress());
            int rowsUpdate = statement.executeUpdate();
            if (rowsUpdate == 0) {
                throw new DAOException("Creating schema failed, no rows affected.");
            }
            return true;
        } catch (SQLException e) {
            LOG.error("SQL Error " + e);
            return false;
        }
    }

    @Override
    public EmployeeDto findByPrimaryKey(long id) {
        EmployeeDto employeeDto = new EmployeeDto();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_EMPLOYEE_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employeeDto = new EmployeeDto(resultSet);
            }
            LOG.info("Get employee by ID(emp_no = " + id + "): " + employeeDto.toString());
        } catch (SQLException e) {
            LOG.error("SQL Error" + e);
        }
        return employeeDto;
    }

    @Override
    public boolean delete(long id) throws DAOException {
        LOG.info(String.format("Delete employee by id = %d:", id));
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_EMPLOYEE);
            statement.setLong(1, id);
            int rowsUpdate = statement.executeUpdate();
            if (rowsUpdate == 0) {
                throw new DAOException("Delete failed, no rows affected.");
            }
            LOG.info("Employee's been successfully deleted");
            return true;
        } catch (SQLException e) {
            LOG.error("SQL Error " + e);
            return false;
        }
    }

    @Override
    public boolean update(String column, String attribute, String id) {
        LOG.info("This functionality has not been implemented");
        throw new UnsupportedOperationException("This functionality has not been implemented yet.");
    }

    @Override
    public boolean update(String column, String attribute, long id) throws DAOException {
        LOG.info(String.format("Updating employee emp_no = %d", id));
        String query = String.format("UPDATE employee SET %s = '%s' WHERE emp_no = %d;",
                column, attribute, id);
        try {
            Statement statement = connection.createStatement();
            int rowsUpdate = statement.executeUpdate(query);
            if (rowsUpdate == 0) {
                throw new DAOException("Update failed, no rows affected.");
            }
            LOG.info("Employee's been successfully updated");
            return true;
        } catch (SQLException e) {
            LOG.error("SQL Error " + e);
            return false;
        }
    }

    @Override
    public List<EmployeeDto> findAll() {
        LOG.info("Select all from employee");
        List<EmployeeDto> result = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_EMPLOYEE);
            while (resultSet.next()) {
                EmployeeDto employeeDto = new EmployeeDto(resultSet);
                result.add(employeeDto);
            }
        } catch (SQLException e) {
            LOG.error("SQL Error" + e);
        }
        return result;
    }

    //Task 3. Perform execution of a few query in one transaction.
    boolean transferAllEmployeeFromCurrentDepartmentToAnotherOne(String current_dept_no, String new_dept_no) {
        EmployeeDto employeeDto;
        long emp_noNew = System.currentTimeMillis() / 1000;
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_EMPLOYEE_BY_DEPARTMANT);
            statement.setString(1, current_dept_no);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employeeDto = new EmployeeDto(resultSet);
                PreparedStatement statement2 = connection.prepareStatement(SQL_INSERT_EMPLOYEE);
                statement2.setLong(1, emp_noNew);
                statement2.setString(2, employeeDto.getEmp_fname());
                statement2.setString(3, employeeDto.getEmp_lname());
                statement2.setString(4, new_dept_no);
                statement2.setString(5, employeeDto.getEmp_adress());
                int rowsUpdate = statement2.executeUpdate();
                LOG.info("Transferring Employee No: " + employeeDto.getEmp_no());
                if (rowsUpdate == 0) {
                    throw new DAOException("Creating schema failed, no rows affected.");
                }
                emp_noNew++;
            }
            statement.executeUpdate(SQL_DISABLE_FOREIGN_KEY);
            statement.executeUpdate(String.format("DELETE FROM employee WHERE dept_no = '%s';", current_dept_no));
            statement.setString(1, current_dept_no);
            LOG.info("Deleting old employees...");
            statement.executeUpdate(SQL_ENABLE_FOREIGN_KEY);
            connection.commit();
            LOG.info("Transfer's been successfully done");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("SQL Error " + e);
            return false;
        }
    }
}