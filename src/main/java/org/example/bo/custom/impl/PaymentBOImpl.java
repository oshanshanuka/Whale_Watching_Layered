package org.example.bo.custom.impl;

import org.example.bo.custom.PaymentBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.PaymentDAO;
import org.example.dto.PaymentDto;
import org.example.entity.Payment;

import java.sql.SQLException;

public class PaymentBOImpl implements PaymentBO {
    //PaymentDAO paymentDAO=new PaymentDAOImpl();
    PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);


    @Override
    public boolean savePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(paymentDto.getPay_id(),paymentDto.getDate(),paymentDto.getAmount()));
    }
}
