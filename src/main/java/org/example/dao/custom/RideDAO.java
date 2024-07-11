package org.example.dao.custom;
import org.example.dao.CrudDAO;
import org.example.dao.custom.Impl.RideBoatDetailDAOImpl;
import org.example.db.Dbconnection;
import org.example.dto.RideBoatDto;
import org.example.dto.RideDto;
import org.example.dto.tm.RideBoatTm;
import org.example.entity.Ride;
import org.example.dao.CrudDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface RideDAO extends CrudDAO<Ride> {
// public RideDto searchRide(String rideId) throws SQLException, ClassNotFoundException;

    // public boolean deleteRide(String rId) throws SQLException, ClassNotFoundException;
    //public boolean updateRide(RideDto dto) throws SQLException, ClassNotFoundException;

    //public boolean saveRide(RideDto dto) throws SQLException, ClassNotFoundException;

    //public ArrayList<RideDto> getAllRides() throws SQLException, ClassNotFoundException;

    public List<String> findAllIds() throws SQLException, ClassNotFoundException;
}