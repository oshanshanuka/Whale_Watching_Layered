package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {
    public  boolean getUser(String userName, String password) throws SQLException, ClassNotFoundException;
}
