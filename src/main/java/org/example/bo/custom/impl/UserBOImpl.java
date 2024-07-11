package org.example.bo.custom.impl;

import org.example.bo.custom.UserBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.UserDAO;

import java.sql.SQLException;

public class UserBOImpl implements UserBO{

    UserDAO userDAO= (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean getUser(String userName, String password) throws SQLException, ClassNotFoundException {
        return userDAO.getUser(userName, password);
    }
}

