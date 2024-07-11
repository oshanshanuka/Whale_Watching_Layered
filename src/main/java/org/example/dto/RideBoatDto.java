package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RideBoatDto {
    private String boat_Id;
    private String ride_Id;

    public RideBoatDto(String boat_Id) {
        this.boat_Id = boat_Id;
    }
}


