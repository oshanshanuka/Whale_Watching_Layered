package org.example.dao.custom.Impl;

import org.example.dao.SqlUtil;
import org.example.dao.custom.CustomerDAO;
import org.example.dto.CustomerDto;
import org.example.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {



    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = Dbconnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM customer");
        ArrayList<CustomerDto> getAllCustomers=new ArrayList<>();
        while (rst.next()){
            CustomerDto customerDto=new CustomerDto(rst.getString("cus_Id"),rst.getString("name"),rst.getString("nic"),rst.getString("address"),rst.getString("e_mail"),rst.getString("tel"));
            getAllCustomers.add(customerDto);
        }
        return getAllCustomers;
         */
        ResultSet rst= SqlUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> getAllCustomers=new ArrayList<>();
        while (rst.next()){
            Customer customer=new Customer(rst.getString("cus_Id"),rst.getString("name"),rst.getString("nic"),rst.getString("address"),rst.getString("e_mail"),rst.getString("tel"));
            getAllCustomers.add(customer);
        }
        return getAllCustomers;
    }

    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = Dbconnection.getInstance().getConnection();

        //String sql = "UPDATE customer SET name = ?,nic=?, address = ?,e_mail=?, tel = ? WHERE cus_Id = ?";
        PreparedStatement pstm = connection.prepareStatement("UPDATE customer SET name = ?,nic=?, address = ?,e_mail=?, tel = ? WHERE cus_Id = ?");

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getNic());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getE_mail());
        pstm.setString(5, dto.getTel());
        pstm.setString(6, dto.getCus_Id());

        boolean isUpdate=pstm.executeUpdate() > 0;
        return isUpdate;*/
        return SqlUtil.execute("UPDATE customer SET name = ?,nic=?, address = ?,e_mail=?, tel = ? WHERE cus_Id = ?",entity.getName(),entity.getNic(),entity.getAddress(),entity.getE_mail(),entity.getTel(),entity.getCus_Id());

    }

    public boolean delete(String cusId) throws SQLException, ClassNotFoundException {

        /*Connection connection= Dbconnection.getInstance().getConnection();

        //String sql = "DELETE FROM customer WHERE cus_Id = ?";
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM customer WHERE cus_Id = ?");
        pstm.setString(1,cusId);

        boolean isDeleted= pstm.executeUpdate() > 0;
        return isDeleted;*/
        return SqlUtil.execute("DELETE FROM customer WHERE cus_Id = ?",cusId);
    }


    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = Dbconnection.getInstance().getConnection();

        //String sql = "INSERT INTO customer VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?)");

        pstm.setString(1, dto.getCus_Id());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getNic());
        pstm.setString(4, dto.getAddress());
        pstm.setString(5, dto.getE_mail());
        pstm.setString(6, dto.getTel());
        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;*/
        return SqlUtil.execute( "INSERT INTO customer VALUES(?,?,?,?,?,?)",entity.getCus_Id(),entity.getName(),entity.getNic(),entity.getAddress(),entity.getE_mail(),entity.getTel());

    }

    public CustomerDto searchCustomerNic(String cusNic) throws SQLException, ClassNotFoundException {
        /*Connection connection=Dbconnection.getInstance().getConnection();
        //String sql="SELECT * FROM customer WHERE nic=?";
        PreparedStatement pstm= connection.prepareStatement("SELECT * FROM customer WHERE nic=?");

        pstm.setString(1,cusNic);

        ResultSet resultSet=pstm.executeQuery();

        CustomerDto dto = null;

        if(resultSet.next()) {
            String Id = resultSet.getString(1);
            String cus_name = resultSet.getString(2);
            String nic = resultSet.getString(3);
            String cus_address = resultSet.getString(4);
            String cus_email = resultSet.getString(5);
            String cus_tel = resultSet.getString(6);

            dto = new CustomerDto(Id, cus_name, nic, cus_address, cus_email, cus_tel);

        }
        return dto;*/
        ResultSet rst=SqlUtil.execute("SELECT * FROM customer WHERE nic=?",cusNic);
        if (rst.next()){
            return new CustomerDto(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6));
        }
        return null;
    }

    public Customer search(String cusId) throws SQLException, ClassNotFoundException {
        /*Connection connection=Dbconnection.getInstance().getConnection();
        //String sql="SELECT * FROM customer WHERE cus_Id=?";
        PreparedStatement pstm= connection.prepareStatement("SELECT * FROM customer WHERE cus_Id=?");

        pstm.setString(1,cusId);

        ResultSet resultSet=pstm.executeQuery();

        CustomerDto dto = null;

        if(resultSet.next()){
            String Id=resultSet.getString(1);
            String cus_name=resultSet.getString(2);
            String cus_nic= resultSet.getString(3);
            String cus_address=resultSet.getString(4);
            String cus_email=resultSet.getString(5);
            String cus_tel=resultSet.getString(6);

            dto = new CustomerDto(Id,cus_name,cus_nic,cus_address,cus_email,cus_tel);

        }
        return dto;*/
        ResultSet resultSet=SqlUtil.execute("SELECT * FROM customer WHERE cus_Id=?",cusId);
        if (resultSet.next()){
            return new Customer(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6));
        }
        return null;

    }

    public List<String> findAllIds() throws SQLException, ClassNotFoundException {
        /*Connection connection=Dbconnection.getInstance().getConnection();

        //String sql = "SELECT cus_Id FROM customer";
        ResultSet resultSet = connection.prepareStatement("SELECT cus_Id FROM customer").executeQuery();

        List<String> allIds = new ArrayList<>();

        while (resultSet.next()) {
            allIds.add(resultSet.getString(1));
        }
        return allIds;
        */
        ResultSet resultSet = SqlUtil.execute("SELECT cus_Id FROM customer");
        List<String> allIds = new ArrayList<>();

        while (resultSet.next()) {
            allIds.add(resultSet.getString(1));
        }
        return allIds;
    }

    @Override
    public int getAllCustomerCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.execute("SELECT COUNT(c.cus_Id) FROM customer c");

        int allCustomerCount=0;
        if (resultSet.next()){
            allCustomerCount=resultSet.getInt(1);
        }
        return allCustomerCount;
    }
}

