package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoatDto {
    private String B_Id;
    private String B_Name;
    private String B_Type;
    private  int Sheet_Count;
    private String Description;
    private PaymentDto paymentDto;

    public BoatDto(String b_Id, String b_Name, String b_Type, int sheet_Count, String description) {
        B_Id = b_Id;
        B_Name = b_Name;
        B_Type = b_Type;
        Sheet_Count = sheet_Count;
        Description = description;
    }
}
