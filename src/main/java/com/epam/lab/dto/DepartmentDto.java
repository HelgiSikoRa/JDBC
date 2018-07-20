package com.epam.lab.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDto {
    private final String notAvailable = "N/A";
    private String dept_no = notAvailable;
    private String location = notAvailable;
    private String dept_name = notAvailable;

    public DepartmentDto() {

    }

    public DepartmentDto(ResultSet resultSet) {
        try {
            this.dept_name = resultSet.getString("dept_name");
            this.location = resultSet.getString("location");
            this.dept_no = resultSet.getString("dept_no");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DepartmentDto setDept_no(String dept_no) {
        this.dept_no = dept_no;
        return this;
    }

    public DepartmentDto setLocation(String location) {
        this.location = location;
        return this;
    }

    public DepartmentDto setDept_name(String dept_name) {
        this.dept_name = dept_name;
        return this;
    }

    public String getDept_no() {
        return dept_no;
    }

    public String getLocation() {
        return location;
    }

    public String getDept_name() {
        return dept_name;
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "notAvailable='" + notAvailable + '\'' +
                ", dept_no='" + dept_no + '\'' +
                ", location='" + location + '\'' +
                ", dept_name='" + dept_name + '\'' +
                '}';
    }
}