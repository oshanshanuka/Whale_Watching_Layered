package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Salary {
    private String sal_Id;
    private String emp_Id;
    private String date;
    private double amount;

}
