package org.example.dto;

import org.example.dto.tm.EmployeeBoatTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeDto {
    private String emp_Id;
    private String name;
    private String address;
    private String tel;
    private String nic;
    private String role;

    private List<EmployeeBoatTm> employeeBoatTms;

    public EmployeeDto(String emp_Id, String name, String address, String tel, String nic, String role) {
        this.emp_Id = emp_Id;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.nic = nic;
        this.role = role;
    }
}

