package com.epam.lab.dao;

import com.epam.lab.CrudInterface;
import com.epam.lab.DAOException;
import com.epam.lab.dto.DepartmentDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.epam.lab.Constants.*;

public class DepartmentDao extends AbstractDao implements CrudInterface<DepartmentDto> {
    private final static Logger LOG = LogManager.getLogger(DepartmentDao.class);

    @Override
    public boolean create(DepartmentDto departmentDto) {
        LOG.info("Insert into department");
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_DEPARTMENT);
            statement.setString(1, departmentDto.getDept_no());
            statement.setString(2, departmentDto.getDept_name());
            statement.setString(3, departmentDto.getLocation());
            int rowsUpdate = statement.executeUpdate();
            if (rowsUpdate == 0) {
                throw new DAOException("Creating new department failed, no rows affected.");
            }
            return true;
        } catch (SQLException e) {
            LOG.error("SQL Error " + e);
            return false;
        }
    }

    @Override
    public DepartmentDto findByPrimaryKey(long id) {
        DepartmentDto departmentDto = new DepartmentDto();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_DEPARTMENT_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                departmentDto = new DepartmentDto(resultSet);
            }
            LOG.info("Get department by ID(dept_no = " + id + "): " + departmentDto.toString());
        } catch (SQLException e) {
            LOG.error("SQL Error" + e);
        }
        return departmentDto;
    }

    @Override
    public boolean delete(long id) {
        LOG.info(String.format("Delete department by id = %d:", id));
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_DEPARTMENT);
            statement.setLong(1, id);
            int rowsUpdate = statement.executeUpdate();
            if (rowsUpdate == 0) {
                throw new DAOException("Delete failed, no rows affected.");
            }
            LOG.info("Department's been successfully deleted");
            return true;
        } catch (SQLException e) {
            LOG.error("SQL Error " + e);
            return false;
        }
    }

    @Override
    public boolean update(String column, String attribute, long id) {
        LOG.info("This functionality has not been implemented");
        throw new UnsupportedOperationException("This functionality has not been implemented yet.");
    }

    @Override
    public boolean update(String column, String attribute, String id) {
        LOG.info(String.format("Updating department dept_no = %s", id));
        String query = String.format("UPDATE department SET %s = '%s' WHERE dept_no = %s;",
                column, attribute, id);
        try {
            Statement statement = connection.createStatement();
            int rowsUpdate = statement.executeUpdate(query);
            if (rowsUpdate == 0) {
                throw new DAOException("Update failed, no rows affected.");
            }
            LOG.info("Department's been successfully updated");
            return true;
        } catch (SQLException e) {
            LOG.error("SQL Error " + e);
            return false;
        }
    }

    @Override
    public List<DepartmentDto> findAll() {
        LOG.info("Select all from department");
        List<DepartmentDto> result = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_DEPARTMENT);
            while (resultSet.next()) {
                DepartmentDto departmentDto = new DepartmentDto(resultSet);
                result.add(departmentDto);
            }
        } catch (SQLException e) {
            LOG.error("SQL Error" + e);
        }
        return result;
    }
}