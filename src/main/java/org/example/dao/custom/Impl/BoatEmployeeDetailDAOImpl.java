package org.example.dao.custom.Impl;


import org.example.dao.SqlUtil;
import org.example.dao.custom.BoatEmployeeDetailsDAO;
import org.example.entity.EmployeeBoatDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class BoatEmployeeDetailDAOImpl implements BoatEmployeeDetailsDAO {
    @Override
    public boolean save(EmployeeBoatDetail employeeBoatDetail) throws SQLException, ClassNotFoundException {
        boolean r = false;
        r = SqlUtil.execute("INSERT INTO employee_boat_detail (boat_Id,emp_Id) VALUES (?,?)",employeeBoatDetail.getBoat_Id(),employeeBoatDetail.getEmp_Id());
        return r;}

    @Override
    public boolean update(EmployeeBoatDetail employeeBoatDetail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<EmployeeBoatDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public EmployeeBoatDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}

