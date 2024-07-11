package org.example.dto.tm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmpAttendenceTm {
    private String emp_Id;
    private String date ;
    private String in_time;
    private  String out_time;
}
