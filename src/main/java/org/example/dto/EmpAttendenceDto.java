package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpAttendenceDto {
    private String emp_Id;
    private String date ;
    private String in_time;
    private  String out_time;
}
