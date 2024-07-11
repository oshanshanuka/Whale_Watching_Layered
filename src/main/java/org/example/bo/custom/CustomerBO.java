package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO{
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    public boolean deleteCustomer(String cusId) throws SQLException, ClassNotFoundException;

    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    public CustomerDto searchCustomerNic(String cusNic) throws SQLException, ClassNotFoundException;

    public CustomerDto searchCustomer(String cusId) throws SQLException, ClassNotFoundException;
    public List<String> findAllIds() throws SQLException, ClassNotFoundException;
}

