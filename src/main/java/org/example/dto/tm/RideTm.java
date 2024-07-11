package org.example.dto.tm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class RideTm {
    private String ride_Id;

    private double one_Person_price;
    private String typ;
    private String date;
    private String time;
    private String status;

}
