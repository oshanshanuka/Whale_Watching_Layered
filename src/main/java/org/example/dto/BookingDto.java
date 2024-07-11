package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDto {
    private String booking_id;
    private String ride_id;
    private String cus_id;
    private String pay_id;
    private String date;
    private String time;

    PaymentDto paymentDto;


}
