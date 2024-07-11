package org.example.dao.custom.Impl;


import org.example.dao.SqlUtil;
import org.example.dao.custom.EmpSalaryDAO;
import org.example.db.Dbconnection;
import org.example.entity.Salary;

import java.sql.*;
import java.util.ArrayList;

public class EmpSalaryDAOImpl implements EmpSalaryDAO {
    public boolean isUpdated(String empId, String date) throws SQLException, ClassNotFoundException {
        /*Connection connection = Dbconnection.getInstance().getConnection();


        PreparedStatement pstm = connection.prepareStatement("\n" +
                "SELECT *\n" +
                "FROM salary\n" +
                "WHERE emp_Id = ? AND date = ?");
        pstm.setString(1, empId);
        pstm.setDate(2, Date.valueOf(date));

        ResultSet resultSet = pstm.executeQuery();*/

        ResultSet resultSet=SqlUtil.execute(
                "SELECT * FROM salary WHERE emp_Id =? AND date =?",empId,date);

        int temp = 0;
        boolean isnoted = true;
        while (resultSet.next()){
            temp+=1;
        }
        if (temp>=1){
            isnoted=false;
        }
        return isnoted;
    }

    public boolean save(Salary entity) throws SQLException, ClassNotFoundException {
        Connection connection = Dbconnection.getInstance().getConnection();

        //String sql = "INSERT INTO salary VALUES(?, ?, ?, ?)";
        /*PreparedStatement pstm = connection.prepareStatement("INSERT INTO salary VALUES(?, ?, ?, ?)");
        pstm.setString(1, salaryDto.getSal_Id());
        pstm.setString(2, salaryDto.getEmp_Id());
        pstm.setString(3, salaryDto.getDate());
        pstm.setString(4, String.valueOf(salaryDto.getAmount()));

        return pstm.executeUpdate() > 0;*/
        return SqlUtil.execute( "INSERT INTO salary VALUES(?, ?, ?, ?)",entity.getSal_Id(),entity.getEmp_Id(),entity.getDate(),String.valueOf(entity.getAmount()));
    }

    @Override
    public boolean update(Salary salary) throws SQLException, ClassNotFoundException {
        return false;
    }

    public ArrayList<Salary> getAll() throws SQLException, ClassNotFoundException {

        /*Connection connection = Dbconnection.getInstance().getConnection();

        //String sql = "SELECT * FROM salary";
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM salary");

        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet= SqlUtil.execute("SELECT * FROM salary");

        ArrayList<Salary> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            Salary salary=new Salary(resultSet.getString("sal_Id"),resultSet.getString("emp_Id"),resultSet.getString("date"),resultSet.getDouble("amount"));

            dtoList.add(salary);
        }

        return dtoList;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Salary search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}

