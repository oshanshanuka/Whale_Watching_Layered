package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.dto.EmployeeBoatDto;
import org.example.entity.EmployeeBoatDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface BoatEmployeeDetailsDAO extends CrudDAO<EmployeeBoatDetail> {
    //public boolean save(EmployeeBoatDto boatDto) throws SQLException, ClassNotFoundException;
}
