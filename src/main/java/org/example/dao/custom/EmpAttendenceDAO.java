package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.db.Dbconnection;
import org.example.dto.EmpAttendenceDto;
import org.example.dto.EmpSalaryDto;
import org.example.entity.Attendence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface EmpAttendenceDAO extends CrudDAO<Attendence> {
    public boolean isUpdated(String id, String date) throws SQLException, ClassNotFoundException;

    //public boolean manage(EmpAttendenceDto attendenceDTO) throws SQLException, ClassNotFoundException;

    //public List<EmpAttendenceDto> getAllAttendeceDetail() throws SQLException, ClassNotFoundException;
    public EmpAttendenceDto isMarked(String id, String date) throws SQLException, ClassNotFoundException;
}