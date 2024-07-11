package org.example.bo.custom.impl;

import org.example.bo.custom.CustomerBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.CustomerDAO;
import org.example.dto.CustomerDto;
import org.example.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO{
    //CustomerDAO customerDAO=new CustomerDAOImpl();
    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {

        ArrayList<Customer> all=customerDAO.getAll();
        ArrayList<CustomerDto> customerDTOS=new ArrayList<>();

        for (Customer customer:all){
            customerDTOS.add(new CustomerDto(customer.getCus_Id(),customer.getName(),customer.getNic(),customer.getAddress(),customer.getE_mail(),customer.getTel()));
        }
        return customerDTOS;
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCus_Id(),dto.getName(),dto.getNic(),dto.getAddress(),dto.getE_mail(),dto.getTel()));
    }

    @Override
    public boolean deleteCustomer(String cusId) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(cusId);
    }

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getCus_Id(),dto.getName(),dto.getNic(),dto.getAddress(),dto.getE_mail(),dto.getTel()));
    }

    @Override
    public CustomerDto searchCustomerNic(String cusNic) throws SQLException, ClassNotFoundException {
        CustomerDto customer=customerDAO.searchCustomerNic(cusNic);
        CustomerDto customerDto=new CustomerDto(customer.getCus_Id(),customer.getName(),customer.getNic(),customer.getAddress(),customer.getE_mail(),customer.getTel());
        return  customerDto;
    }

    @Override
    public CustomerDto searchCustomer(String cusId) throws SQLException, ClassNotFoundException {
        Customer customer=customerDAO.search(cusId);
        CustomerDto customerDto=new CustomerDto(customer.getCus_Id(),customer.getName(),customer.getNic(),customer.getAddress(),customer.getE_mail(),customer.getTel());
        return customerDto;
    }

    @Override
    public List<String> findAllIds() throws SQLException, ClassNotFoundException {
        return customerDAO.findAllIds();
    }
}

