package org.example.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Ride { private String ride_Id;
    private double one_Person_price;
    private String type;
    private String date;
    private String time;
    private String status;

}
