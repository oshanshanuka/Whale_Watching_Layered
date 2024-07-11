package org.example.dto.tm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BoatTm {
    private String boat_Id;
    private String boat_Name;
    private String boat_Type;
    private  int Sheet_Count;
    private String Description;

}

