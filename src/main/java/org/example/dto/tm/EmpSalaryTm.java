package org.example.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmpSalaryTm {
    private String sal_Id;
    private String emp_Id;
    private String date;
    //private String empName;
    private double amount;
    //private String emp_Id;

    // private double OneDay_Payment_Price;


}
