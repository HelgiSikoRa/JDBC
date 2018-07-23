package com.epam.lab;

import java.util.List;

public interface CrudInterface<T> {

    boolean create(T instanceDto);

    boolean delete(long id);

    boolean update(String column, String attribute, String id);

    boolean update(String column, String attribute, long id);

    T findByPrimaryKey(long id);

    List<T> findAll();
}