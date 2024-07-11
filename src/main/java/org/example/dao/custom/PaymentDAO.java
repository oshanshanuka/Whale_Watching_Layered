package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.dto.PaymentDto;
import org.example.entity.Payment;

import java.sql.Connection;
import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<Payment>{
    // public boolean savePayment(PaymentDto paymentDto, Connection dbconnection) throws SQLException, ClassNotFoundException;
}