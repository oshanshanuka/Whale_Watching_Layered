package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.PaymentDto;

import java.sql.SQLException;

public interface PaymentBO extends SuperBO {
    public boolean savePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException;

}
