package org.example.bo.custom;

import org.example.bo.SuperBO;

import java.sql.SQLException;

public interface UserBO extends SuperBO {

    boolean getUser(String userName, String password) throws SQLException, ClassNotFoundException;
}
