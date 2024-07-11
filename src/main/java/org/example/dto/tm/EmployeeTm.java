package org.example.dto.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeTm {
    private String emp_Id;
    private String name;
    private String address;
    private String tel;
    private String nic;
    private String role;

}
