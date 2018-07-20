package com.epam.lab.dao;

import com.epam.lab.CrudInterface;
import com.epam.lab.dto.DepartmentDto;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDao extends AbstractDao implements CrudInterface<DepartmentDto> {
    @Override
    public boolean create(DepartmentDto departmentDto) {
        String query = "INSERT INTO employee (dept_no, dept_name, location) VALUES (?,?,?);";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, departmentDto.getDept_no());
            statement.setString(2, departmentDto.getDept_name());
            statement.setString(3, departmentDto.getLocation());
            int rowsUpdate = statement.executeUpdate();
            if (rowsUpdate >= 1) return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean update(DepartmentDto employeeDto) {
        return false;
    }

    @Override
    public DepartmentDto read(long id) {
        return null;
    }

    @Override
    public List<DepartmentDto> findAll() {
        return null;
    }
}