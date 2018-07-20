package com.epam.lab;

import java.util.List;

public interface CrudInterface<T> {

    boolean create(T employeeDto);

    boolean delete(long id);

    boolean update(T employeeDto);

    T read(long id);

    List<T> findAll();
}
