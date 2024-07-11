package org.example.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookingCustomDto {
    private String date;
    private String time;
    private String cus_name;
    private String ride_Type;
    private double ride_amount;
}
