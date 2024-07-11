package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.EmpSalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmpSalaryBO extends SuperBO{
    public boolean isUpdated(String empId, String date) throws SQLException, ClassNotFoundException;
    public boolean manage(EmpSalaryDto salaryDto) throws SQLException, ClassNotFoundException;

    ArrayList<EmpSalaryDto> getAllSalarys()throws SQLException, ClassNotFoundException;
}
