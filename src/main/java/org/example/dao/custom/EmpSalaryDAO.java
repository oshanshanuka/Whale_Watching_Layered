package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.db.Dbconnection;
import org.example.dto.EmpSalaryDto;
import org.example.entity.Salary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface EmpSalaryDAO extends CrudDAO<Salary>{
    public boolean isUpdated(String empId, String date) throws SQLException, ClassNotFoundException;
    //public boolean manage(EmpSalaryDto salaryDto) throws SQLException, ClassNotFoundException;

    //public List<EmpSalaryDto> getAllsalaryDetail() throws SQLException, ClassNotFoundException;
}
