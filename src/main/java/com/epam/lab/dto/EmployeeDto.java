package com.epam.lab.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDto {
    private final String notAvailable = "N/A";
    private long emp_no;
    private String emp_fname = notAvailable;
    private String emp_lname = notAvailable;
    private String emp_adress = notAvailable;
    private String dept_no = notAvailable;

    public EmployeeDto() {

    }

    public EmployeeDto(ResultSet resultSet) {
        try {
            this.emp_no = resultSet.getLong("emp_no");
            this.emp_fname = resultSet.getString("emp_fname");
            this.emp_lname = resultSet.getString("emp_lname");
            this.emp_adress = resultSet.getString("emp_adress");
            this.dept_no = resultSet.getString("dept_no");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long getEmp_no() {
        return emp_no;
    }

    public String getEmp_fname() {
        return emp_fname;
    }

    public String getEmp_lname() {
        return emp_lname;
    }

    public String getDept_no() {
        return dept_no;
    }

    public String getEmp_adress() {
        return emp_adress;
    }

    public EmployeeDto setEmp_no(long emp_no) {
        this.emp_no = emp_no;
        return this;
    }

    public EmployeeDto setEmp_fname(String emp_fname) {
        this.emp_fname = emp_fname;
        return this;
    }

    public EmployeeDto setEmp_lname(String emp_lname) {
        this.emp_lname = emp_lname;
        return this;
    }

    public EmployeeDto setEmp_adress(String emp_adress) {
        this.emp_adress = emp_adress;
        return this;
    }

    public EmployeeDto setDept_no(String dept_no) {
        this.dept_no = dept_no;
        return this;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "emp_no=" + emp_no +
                ", emp_fname='" + emp_fname + '\'' +
                ", emp_lname='" + emp_lname + '\'' +
                ", emp_adress='" + emp_adress + '\'' +
                ", dept_no='" + dept_no + '\'' +
                '}' + "\n";
    }
}