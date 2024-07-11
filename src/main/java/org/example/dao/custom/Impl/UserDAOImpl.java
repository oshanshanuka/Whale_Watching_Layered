package org.example.dao.custom.Impl;

import org.example.dao.SqlUtil;
import org.example.dao.custom.UserDAO;
import org.example.db.Dbconnection;
import org.example.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    public  boolean getUser(String userName, String password) throws SQLException, ClassNotFoundException {
        // Connection connection= Dbconnection.getInstance().getConnection();

        //String sql="SELECT * FROM user WHERE userName=? AND password=?";

        // PreparedStatement pstm=connection.prepareStatement("SELECT * FROM user WHERE userName=? AND password=?");
        // pstm.setString(1,userName);
        // pstm.setString(2,password);

        ResultSet resultSet= SqlUtil.execute("SELECT * FROM user WHERE userName=? AND password=?",userName,password);

        if(resultSet.next()){
            return true;
        }
        return false;
    }

    @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}

