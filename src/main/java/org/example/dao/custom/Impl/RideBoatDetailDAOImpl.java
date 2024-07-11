package org.example.dao.custom.Impl;

import org.example.dao.SqlUtil;
import org.example.dao.custom.BoatDAO;
import org.example.dao.custom.RideBoatDetailDAO;
import org.example.db.Dbconnection;
import org.example.dto.BoatDto;
import org.example.entity.Boat;
import org.example.entity.RideBoatDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RideBoatDetailDAOImpl implements RideBoatDetailDAO {
    @Override
    public boolean save(RideBoatDetail rideBoatDetail) throws SQLException, ClassNotFoundException {
        boolean r = false;
        r = SqlUtil.execute("INSERT INTO ride_boat_detail (boat_Id,ride_Id) VALUES (?,?)",rideBoatDetail.getBoat_Id(),rideBoatDetail.getRide_Id());
        return r;
    }

    @Override
    public boolean update(RideBoatDetail rideBoatDetail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<RideBoatDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public RideBoatDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public long findBoatSeatCount(String rideId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet=SqlUtil.execute("SELECT SUM(b.Sheet_Count) AS seats  FROM ride_boat_detail rb, boat b WHERE b.boat_Id = rb.boat_Id AND rb.ride_Id = ?",rideId);
        long seatAmount = 0;
        if (resultSet.next()){
            seatAmount=resultSet.getLong(1);
        }
        System.out.println("=============="+seatAmount);
        return seatAmount;
    }
}

