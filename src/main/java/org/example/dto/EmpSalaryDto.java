package org.example.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmpSalaryDto {
    private String sal_Id;
    private String emp_Id;
    private String date;
    private double amount;
}

