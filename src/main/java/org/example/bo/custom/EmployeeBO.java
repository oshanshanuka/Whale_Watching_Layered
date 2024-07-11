package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public boolean deleteEmployee(String empId) throws SQLException, ClassNotFoundException;

    public boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;
    public EmployeeDto searchEmployeeNIC(String empNic) throws SQLException, ClassNotFoundException;
    public boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;

    public EmployeeDto searchEmployee(String id) throws SQLException, ClassNotFoundException;

    public ArrayList<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException;
}

