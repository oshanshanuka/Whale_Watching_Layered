package org.example.entity;
import org.example.dto.PaymentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Boat {
    private String B_Id;
    private String B_Name;
    private String B_Type;
    private  int Sheet_Count;
    private String Description;

}
