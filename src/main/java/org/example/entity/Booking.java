package org.example.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Booking {
    private String booking_Id;
    private String ride_Id;
    private String cus_Id;
    private String pay_Id;
    private String date;
    private String time;

}

