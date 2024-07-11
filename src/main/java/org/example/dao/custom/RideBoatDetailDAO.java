package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.db.Dbconnection;
import org.example.dto.RideBoatDto;
import org.example.entity.RideBoatDetail;
import org.example.dao.CrudDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface RideBoatDetailDAO extends CrudDAO<RideBoatDetail> {
    public long findBoatSeatCount(String rideId) throws SQLException, ClassNotFoundException;

    //public boolean saveRideBoatDetail(RideBoatDto rideBoatDto) throws SQLException, ClassNotFoundException;
}
