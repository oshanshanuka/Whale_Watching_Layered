package org.example.dao.custom.Impl;

import org.example.dao.SqlUtil;
import org.example.dao.custom.BoatEmployeeDetailsDAO;
import org.example.dao.custom.EmployeeDAO;
import org.example.db.Dbconnection;
import org.example.dto.EmpAttendenceDto;
import org.example.dto.EmployeeBoatDto;
import org.example.dto.EmployeeDto;
import org.example.dto.tm.EmployeeBoatTm;
import org.example.entity.Employee;


import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO{
    BoatEmployeeDetailsDAO boatEmployeeDetailDAO=new BoatEmployeeDetailDAOImpl();
    public boolean delete(String empId) throws SQLException, ClassNotFoundException {
        /*Connection connection= Dbconnection.getInstance().getConnection();
        //String sql="DELETE FROM employee WHERE emp_Id=?";
        PreparedStatement pstm=connection.prepareStatement("DELETE FROM employee WHERE emp_Id=?");

        pstm.setString(1,empId);
        boolean isDeleted=pstm.executeUpdate()>0;
        return isDeleted;*/

        return SqlUtil.execute("DELETE FROM employee WHERE emp_Id=?",empId);
    }

    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {

        boolean r = false;
        r = SqlUtil.execute("INSERT INTO employee (emp_Id,name,address,tel,nic,role) VALUES (?,?,?,?,?,?)",entity.getEmp_Id(),entity.getName(),entity.getAddress(),entity.getTel(),entity.getNic(),entity.getRole());
        return r;

        /*Connection connection= Dbconnection.getInstance().getConnection();
        //BoatEmployeeDetailModel boatEmployeeDetailModel = new BoatEmployeeDetailModel();

        connection.setAutoCommit(false);
        try {



            boolean isSaved=SqlUtil.execute("INSERT INTO employee VALUES(?,?,?,?,?,?)",dto.getEmp_Id(),dto.getName(),dto.getAddress(),dto.getTel(),dto.getNic(),dto.getRole());

            if (isSaved) {

                for (EmployeeBoatTm boatTm : dto.getEmployeeBoatTms()) {
                    EmployeeBoatDto boatDto = new EmployeeBoatDto(boatTm.getBoat_Id(), dto.getEmp_Id());
                    boolean isAdded = boatEmployeeDetailDAO.save(boatDto);
                    if (!isAdded) {
                        connection.rollback();
                        return false;
                    }
                }

                connection.commit();
                return true;

            } else {
                connection.rollback();
                return false;
            }

        } catch (Exception ex) {
            System.out.println(ex);
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }*/

    }

    public EmployeeDto searchEmployeeNIC(String empNic) throws SQLException, ClassNotFoundException {
        /*Connection connection=Dbconnection.getInstance().getConnection();
        //String sql="SELECT * FROM employee WHERE nic=?";
        PreparedStatement pstm= connection.prepareStatement("SELECT * FROM employee WHERE nic=?");

        pstm.setString(1,empNic);

        ResultSet resultSet= pstm.executeQuery();*/
        ResultSet resultSet=SqlUtil.execute("SELECT * FROM employee WHERE nic=?",empNic);
        EmployeeDto dto=null;
        if (resultSet.next()){
            String emp_Id=resultSet.getString(1);
            String emp_Name=resultSet.getString(2);
            String emp_Address=resultSet.getString(3);
            String tel_Num=resultSet.getString(4);
            String Nic=resultSet.getString(5);
            String emp_Role=resultSet.getString(6);

            dto=new EmployeeDto(emp_Id,emp_Name,emp_Address,tel_Num,Nic,emp_Role);
        }
        return dto;
    }

    @Override
    public int allEmployeeCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.execute("SELECT COUNT(e.emp_Id) FROM employee e");
        int employeeCount=0;
        if (resultSet.next()){
            employeeCount=resultSet.getInt(1);
        }
        return employeeCount;
    }

    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        boolean isUpadate=false;

        /*Connection connection=Dbconnection.getInstance().getConnection();
        //String sql="UPDATE employee SET name=?,address=?,tel=?,nic=?,role=? WHERE emp_Id=?";

        PreparedStatement pstm=connection.prepareStatement("UPDATE employee SET name=?,address=?,tel=?,nic=?,role=? WHERE emp_Id=?");

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getTel());
        pstm.setString(4, dto.getNic());
        pstm.setString(5, dto.getRole());
        pstm.setString(6, dto.getEmp_Id());

        isUpadate=pstm.executeUpdate()>0;
        return isUpadate;*/
        return SqlUtil.execute("UPDATE employee SET name=?,address=?,tel=?,nic=?,role=? WHERE emp_Id=?",entity.getName(),entity.getAddress(),entity.getTel(),entity.getNic(),entity.getRole(),entity.getEmp_Id());
    }

    public Employee search(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection=Dbconnection.getInstance().getConnection();
        //String sql="SELECT * FROM employee WHERE emp_Id=?";
        PreparedStatement pstm= connection.prepareStatement("SELECT * FROM employee WHERE emp_Id=?");

        pstm.setString(1,id);

        ResultSet resultSet= pstm.executeQuery();*/
        ResultSet resultSet=SqlUtil.execute("SELECT * FROM employee WHERE emp_Id=?",id);
        Employee employee=null;
        if (resultSet.next()){
            String emp_Id=resultSet.getString(1);
            String emp_Name=resultSet.getString(2);
            String emp_Address=resultSet.getString(3);
            String tel_Num=resultSet.getString(4);
            String Nic=resultSet.getString(5);
            String emp_Role=resultSet.getString(6);

            employee=new Employee(emp_Id,emp_Name,emp_Address,tel_Num,Nic,emp_Role);
        }
        return employee;
    }

    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = Dbconnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM employee");*/
        ResultSet rst=SqlUtil.execute("SELECT * FROM employee");
        ArrayList<Employee> getAllEmployees=new ArrayList<>();
        while (rst.next()){
            Employee employee=new Employee(rst.getString("emp_Id"),rst.getString("name"),rst.getString("address"),rst.getString("tel"),rst.getString("nic"),rst.getString("role"));
            getAllEmployees.add(employee);
        }
        return getAllEmployees;
    }


}

