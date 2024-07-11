package org.example.dao.custom;

import java.sql.SQLException;
import org.example.dao.CrudDAO;
import org.example.dto.CustomerDto;
import org.example.entity.Customer;

import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    //public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException;

    //public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    // public boolean deleteCustomer(String cusId) throws SQLException, ClassNotFoundException;

    //public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    public CustomerDto searchCustomerNic(String cusNic) throws SQLException, ClassNotFoundException;

    //public CustomerDto searchCustomer(String cusId) throws SQLException, ClassNotFoundException;

    public List<String> findAllIds() throws SQLException, ClassNotFoundException;
    int getAllCustomerCount() throws SQLException,ClassNotFoundException;
}

