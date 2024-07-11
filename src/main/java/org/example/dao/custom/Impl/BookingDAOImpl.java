package org.example.dao.custom.Impl;

import org.example.bo.custom.PaymentBO;
import org.example.bo.custom.impl.PaymentBOImpl;
import org.example.dao.SqlUtil;
import org.example.dao.custom.BookingDAO;
import org.example.dto.BookingCustomDto;
import org.example.entity.Booking;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {
    //PaymentDAO paymentDAO=new PaymentDAOImpl();
    PaymentBO paymentBO=new PaymentBOImpl();
    public  long bookedSheets(String selectedItem) throws SQLException, ClassNotFoundException {
        /*Connection connection= Dbconnection.getInstance().getConnection();
        //String sql=" SELECT COUNT(b.booking_Id) FROM booking b, ride r WHERE b.ride_Id=r.ride_Id AND r.ride_Id=?";
        PreparedStatement pstm=connection.prepareStatement(" SELECT COUNT(b.booking_Id) FROM booking b, ride r WHERE b.ride_Id=r.ride_Id AND r.ride_Id=?");

        pstm.setString(1,selectedItem);
        ResultSet resultSet=pstm.executeQuery();*/
        ResultSet resultSet= SqlUtil.execute("SELECT COUNT(b.booking_Id) FROM booking b, ride r WHERE b.ride_Id=r.ride_Id AND r.ride_Id=?",selectedItem);
        long seatAmount = 0;

        if (resultSet.next()){
            seatAmount = resultSet.getLong(1);

        }
        return seatAmount;
    }

    @SneakyThrows
    public  String getMail(String value) throws SQLException {
        /*Connection connection = Dbconnection.getInstance().getConnection();
        //String sql = "SELECT e_mail FROM customer WHERE cus_Id = ?";
        PreparedStatement pstm = connection.prepareStatement("SELECT e_mail FROM customer WHERE cus_Id = ?");
        pstm.setString(1,value);
        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet=SqlUtil.execute("SELECT e_mail FROM customer WHERE cus_Id = ?",value);
        String mail = "";
        if (resultSet.next()){
            mail = resultSet.getString(1);
        }
        return mail;
    }

    @Override
    public int allBookingCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=SqlUtil.execute("SELECT COUNT(bo.booking_Id) FROM booking bo");
        int allBookings=0;
        if (resultSet.next()){
            allBookings=resultSet.getInt(1);
        }
        return allBookings;
    }

    @Override
    public int getNormalBookingCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=SqlUtil.execute("SELECT COUNT(b.booking_Id) FROM booking b, ride r WHERE b.ride_Id=r.ride_Id AND r.typ='Normal'");
        int normalBookings=0;
        if (resultSet.next()){
            normalBookings=resultSet.getInt(1);
        }
        return normalBookings;
    }

    @Override
    public int getLuxuryBookingCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=SqlUtil.execute("SELECT COUNT(b.booking_Id) FROM booking b, ride r WHERE b.ride_Id=r.ride_Id AND r.typ='Luxury'");
        int luxuryBookings=0;
        if (resultSet.next()){
            luxuryBookings=resultSet.getInt(1);
        }
        return luxuryBookings;
    }

    public boolean save(Booking entity) throws SQLException, ClassNotFoundException {

        boolean r=false;
        r=SqlUtil.execute("INSERT INTO booking(booking_Id,ride_Id,cus_Id,pay_Id,date,time) VALUES(?,?,?,?,?,?)",entity.getBooking_Id(),entity.getRide_Id(),entity.getCus_Id(),entity.getPay_Id(),entity.getDate(),entity.getTime());
        return r;
    }

    @Override
    public boolean update(Booking booking) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<BookingCustomDto> getAllBooking() throws SQLException, ClassNotFoundException {
        ArrayList<BookingCustomDto> dtoList=new ArrayList<>();
        ResultSet resultSet=SqlUtil.execute("SELECT  b.date, b.time,c.name,r.typ,p.amount FROM booking b, ride r, customer c, payment p WHERE r.ride_Id=b.ride_Id \n" +
                "AND c.cus_Id=b.cus_Id AND p.pay_Id=b.pay_Id ORDER BY b.booking_Id");

        while (resultSet.next()) {
            String bookingDate=resultSet.getString(1);
            String  bookingTime = resultSet.getString(2);
            //String bookingType = resultSet.getString(4);
            String customerName = resultSet.getString(3);
            String bookingType = resultSet.getString(4);
            // String bookingDate = resultSet.getString(1);
            // String  bookingTime = resultSet.getString(2);
            double amount = resultSet.getDouble(5);

            var dto = new BookingCustomDto(bookingDate, bookingTime, customerName, bookingType, amount);
            dtoList.add(dto);
        }
        return dtoList;

    }


    public ArrayList<Booking> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection=Dbconnection.getInstance().getConnection();

        //String sql = "SELECT  b.date, b.time,c.name,r.typ,p.amount FROM booking b, ride r, customer c, payment p WHERE r.ride_Id=b.ride_Id \n" +
                //"AND c.cus_Id=b.cus_Id AND p.pay_Id=b.pay_Id ORDER BY b.booking_Id";
        PreparedStatement pstm = connection.prepareStatement("SELECT  b.date, b.time,c.name,r.typ,p.amount FROM booking b, ride r, customer c, payment p WHERE r.ride_Id=b.ride_Id \n" +
                "AND c.cus_Id=b.cus_Id AND p.pay_Id=b.pay_Id ORDER BY b.booking_Id");

        List<BookingCustomDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();*/
        /*ArrayList<BookingCustomDto> dtoList=new ArrayList<>();
        ResultSet resultSet=SqlUtil.execute("SELECT  b.date, b.time,c.name,r.typ,p.amount FROM booking b, ride r, customer c, payment p WHERE r.ride_Id=b.ride_Id \n" +
                "AND c.cus_Id=b.cus_Id AND p.pay_Id=b.pay_Id ORDER BY b.booking_Id");

        while (resultSet.next()) {
            String bookingDate=resultSet.getString(1);
            String  bookingTime = resultSet.getString(2);
            //String bookingType = resultSet.getString(4);
            String customerName = resultSet.getString(3);
            String bookingType = resultSet.getString(4);
            // String bookingDate = resultSet.getString(1);
            // String  bookingTime = resultSet.getString(2);
            double amount = resultSet.getDouble(5);

            var dto = new BookingCustomDto(bookingDate, bookingTime, customerName, bookingType, amount);
            dtoList.add(dto);
        }
        return dtoList;*/
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Booking search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
