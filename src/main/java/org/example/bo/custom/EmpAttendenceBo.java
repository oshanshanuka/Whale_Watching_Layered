package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.EmpAttendenceDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmpAttendenceBo extends SuperBO{
    public boolean isUpdated(String id, String date) throws SQLException, ClassNotFoundException;

    public boolean manage(EmpAttendenceDto attendenceDTO) throws SQLException, ClassNotFoundException;

    public ArrayList<EmpAttendenceDto> getAllAttendeceDetail() throws SQLException, ClassNotFoundException;
    public EmpAttendenceDto isMarked(String id, String date) throws SQLException, ClassNotFoundException;
}
