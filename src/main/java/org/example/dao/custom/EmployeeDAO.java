package org.example.dao.custom;
import org.example.dao.CrudDAO;
import org.example.dao.custom.Impl.BoatEmployeeDetailDAOImpl;
import org.example.db.Dbconnection;
import org.example.dto.EmployeeBoatDto;
import org.example.dto.EmployeeDto;
import org.example.dto.tm.EmployeeBoatTm;
import org.example.entity.Employee;

import java.sql.*;
import java.util.ArrayList;

public interface EmployeeDAO  extends CrudDAO<Employee> {
    //public boolean deleteEmployee(String empId) throws SQLException, ClassNotFoundException;

    //public boolean saveEmployee(EmployeeDto dto) throws SQLException ;
    public EmployeeDto searchEmployeeNIC(String empNic) throws SQLException, ClassNotFoundException;
    // public boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;

    // public EmployeeDto searchEmployeeId(String id) throws SQLException, ClassNotFoundException;

    //public ArrayList<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException;

    int allEmployeeCount() throws SQLException,ClassNotFoundException;
}
