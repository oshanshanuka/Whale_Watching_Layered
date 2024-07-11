package org.example.dto;

import org.example.dto.tm.RideBoatTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RideDto {

    private String ride_Id;
    private double price;
    private String type;
    private String date;
    private String time;
    private String status;

    private List<RideBoatTm> rideBoatTms;

    public RideDto(String ride_Id, double price, String type, String date, String time, String status) {
        this.ride_Id = ride_Id;
        this.price = price;
        this.type = type;
        this.date=date;

        this.time=time;
        this.status=status;
    }
}

