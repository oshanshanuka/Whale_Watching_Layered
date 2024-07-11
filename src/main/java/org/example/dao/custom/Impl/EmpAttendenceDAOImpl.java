package org.example.dao.custom.Impl;

import org.example.dao.SqlUtil;
import org.example.dao.custom.EmpAttendenceDAO;
import org.example.dto.EmpAttendenceDto;
import org.example.entity.Attendence;

import java.sql.*;
import java.util.ArrayList;

public class EmpAttendenceDAOImpl implements EmpAttendenceDAO{
    public boolean isUpdated(String id, String date) throws SQLException, ClassNotFoundException {
        /*Connection connection = Dbconnection.getInstance().getConnection();

        //String sql = "\n" +
                //"SELECT *\n" +
                //"FROM attendance\n" +
                //"WHERE emp_Id = ? AND date = ?";
        PreparedStatement pstm = connection.prepareStatement("\n" +
                "SELECT *\n" +
                "FROM attendance\n" +
                "WHERE emp_Id = ? AND date = ?");
        pstm.setString(1, id);
        pstm.setDate(2, Date.valueOf(date));

        ResultSet resultSet = pstm.executeQuery();
        */
        ResultSet resultSet=SqlUtil.execute("SELECT * FROM attendance WHERE emp_Id = ? AND date = ?",id,date);

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

    public boolean save(Attendence entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = Dbconnection.getInstance().getConnection();

        //String sql = "INSERT INTO attendance VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO attendance VALUES(?, ?, ?, ?)");
        pstm.setString(1, attendenceDTO.getEmp_Id());
        pstm.setString(2, attendenceDTO.getDate());
        pstm.setString(3, attendenceDTO.getIn_time());
        pstm.setString(4, attendenceDTO.getOut_time());

        return pstm.executeUpdate() > 0;*/

        return SqlUtil.execute("INSERT INTO attendance VALUES(?, ?, ?, ?)",entity.getEmp_Id(),entity.getDate(),entity.getIn_time(),entity.getOut_time());
    }

    @Override
    public boolean update(Attendence dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public ArrayList<Attendence> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = Dbconnection.getInstance().getConnection();

        String sql = "SELECT * FROM attendance";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();*/
        // ResultSet resultSet= SqlUtil.execute("SELECT * FROM attendance");

        ResultSet rst = SqlUtil.execute("SELECT * FROM attendance");
        ArrayList<Attendence> getAllEmpAttendance=new ArrayList<>();
        while (rst.next()){
            Attendence attendence=new Attendence(rst.getString("emp_Id"),rst.getString("date"),rst.getString("in_time"), rst.getString("out_time"));
            getAllEmpAttendance.add(attendence);
        }
        return getAllEmpAttendance;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Attendence search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public EmpAttendenceDto isMarked(String id, String date) throws SQLException, ClassNotFoundException {
        /*Connection connection = Dbconnection.getInstance().getConnection();

        /*String sql = "\n" +
                "SELECT *\n" +
                "FROM attendance\n" +
                "WHERE emp_Id = ? AND date = ?";
        PreparedStatement pstm = connection.prepareStatement( "\n" +
                "SELECT *\n" +
                "FROM attendance\n" +
                "WHERE emp_Id = ? AND date = ?");
        pstm.setString(1, id);
        pstm.setDate(2, Date.valueOf(date));

        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet=SqlUtil.execute("SELECT * FROM attendance WHERE emp_Id = ? AND date = ?",id,date);
        EmpAttendenceDto dto = null;

        if (resultSet.next()) {
            String emp_Id = resultSet.getString(1);
            String dates = resultSet.getString(2);
            String in_time = resultSet.getString(3);
            String out_time = resultSet.getString(4);


            dto = new EmpAttendenceDto(emp_Id, dates, in_time, out_time);

        }
        return dto;

    }
}

