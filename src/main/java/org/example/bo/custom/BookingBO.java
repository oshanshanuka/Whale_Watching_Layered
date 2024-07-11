package org.example.bo.custom;

import lombok.SneakyThrows;

import org.example.bo.SuperBO;
import org.example.dto.BookingCustomDto;
import org.example.dto.BookingDto;

import java.sql.SQLException;
import java.util.List;
public interface BookingBO extends SuperBO{
    public  long bookedSheets(String selectedItem) throws SQLException, ClassNotFoundException;


    @SneakyThrows
    public  String getMail(String value) throws SQLException ;

    public boolean saveBooking(BookingDto bookingDto) throws SQLException, ClassNotFoundException;

    public List<BookingCustomDto> getAllBooking() throws SQLException, ClassNotFoundException;



}
