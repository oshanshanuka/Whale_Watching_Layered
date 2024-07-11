package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    private String emp_Id;
    private String name;
    private String address;
    private String tel;
    private String nic;
    private String role;

}
