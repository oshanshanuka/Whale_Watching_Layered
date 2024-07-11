package org.example.dao.custom.Impl;
import org.example.dao.SqlUtil;
import org.example.dao.custom.RideBoatDetailDAO;
import org.example.dao.custom.RideDAO;
import org.example.db.Dbconnection;
import org.example.dto.EmpAttendenceDto;
import org.example.dto.RideBoatDto;
import org.example.dto.RideDto;
import org.example.dto.tm.RideBoatTm;
import org.example.entity.Ride;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RideDAOImpl implements RideDAO {
    RideBoatDetailDAO rideBoatDetailDAO=new RideBoatDetailDAOImpl();
    public Ride search(String rideId) throws SQLException, ClassNotFoundException {
        /*Connection connection = Dbconnection.getInstance().getConnection();

        //String sql = "SELECT * FROM ride WHERE ride_id=?";
        PreparedStatement pstm = connection.prepareStatement( "SELECT * FROM ride WHERE ride_id=?");
        pstm.setString(1, rideId);

        ResultSet resultSet = pstm.executeQuery();
        RideDto dto = null;

        if (resultSet.next()) {
            String id=resultSet.getString(1);
            double price=resultSet.getDouble(2);
            String type=resultSet.getString(3);
            String date=resultSet.getString(4);
            String time=resultSet.getString(5);
            String status=resultSet.getString(6);

            dto=new RideDto(id,price,type,date,time,status);

        }
        return dto;*/
        ResultSet resultSet=SqlUtil.execute("SELECT * FROM ride WHERE ride_id=?",rideId);
        if (resultSet.next()){
            return new Ride(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }

        return null;
    }

    public boolean delete(String rId) throws SQLException, ClassNotFoundException {
        /*Connection connection=Dbconnection.getInstance().getConnection();
        //String sql="DELETE FROM ride WHERE ride_Id=?";
        PreparedStatement pstm= connection.prepareStatement("DELETE FROM ride WHERE ride_Id=?");
        pstm.setString(1,rId);

        boolean isDeleted=pstm.executeUpdate()>0;

        return isDeleted;*/
        return SqlUtil.execute("DELETE FROM ride WHERE ride_Id=?",rId);
    }

    public boolean update(Ride ride) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE ride SET one_Person_Price=?,typ=?,date=?,time=?,status=? WHERE ride_Id=?",ride.getOne_Person_price(),ride.getType(),ride.getDate(),ride.getTime(),ride.getStatus(),ride.getRide_Id());
    }

    public boolean save(Ride ride) throws SQLException, ClassNotFoundException {
        boolean r = false;
        r = SqlUtil.execute("INSERT INTO ride (ride_Id,one_Person_price,typ,date,time,status) VALUES (?,?,?,?,?,?)",ride.getRide_Id(),ride.getOne_Person_price(),ride.getType(),ride.getDate(),ride.getTime(),ride.getStatus());
        return r;
    }

    public ArrayList<Ride> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = Dbconnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM ride");

         */
        ResultSet rst=SqlUtil.execute("SELECT * FROM ride");
        ArrayList<Ride> getAllRides=new ArrayList<>();
        while (rst.next()){
            Ride ride=new Ride(rst.getString("ride_Id"), rst.getDouble("one_Person_price"), rst.getString("typ"),rst.getString("date"),rst.getString("time"),rst.getString("status"));
            getAllRides.add(ride);
        }
        return getAllRides;
    }

    public List<String> findAllIds() throws SQLException, ClassNotFoundException {
       /* Connection connection = Dbconnection.getInstance().getConnection();

        //String sql = "SELECT ride_Id FROM ride";
        ResultSet resultSet = connection.prepareStatement("SELECT ride_Id FROM ride").executeQuery();

        */
        ResultSet resultSet= SqlUtil.execute("SELECT ride_Id FROM ride");
        List<String> rideList = new ArrayList<>();

        while (resultSet.next()) {
            rideList.add(resultSet.getString(1));
        }
        return rideList;
    }
}

