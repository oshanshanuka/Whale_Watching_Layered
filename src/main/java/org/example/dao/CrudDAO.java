package org.example.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    boolean save(T t) throws SQLException, ClassNotFoundException ;
    boolean update(T t) throws SQLException, ClassNotFoundException;
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException ;
    T search(String id) throws SQLException, ClassNotFoundException;


}

