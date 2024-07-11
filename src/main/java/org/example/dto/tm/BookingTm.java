package org.example.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BookingTm {
    private String date;
    private String time;
    private String cus_name;
    private String ride_type;
    private double ride_amount;
}
