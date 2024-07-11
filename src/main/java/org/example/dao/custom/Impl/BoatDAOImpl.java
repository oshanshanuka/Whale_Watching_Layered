package org.example.dao.custom.Impl;

import org.example.dao.SqlUtil;
import org.example.dao.custom.BoatDAO;
import org.example.db.Dbconnection;
import org.example.dto.BoatDto;
import org.example.entity.Boat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoatDAOImpl implements  BoatDAO{

    @Override
    public boolean delete(String bId) throws SQLException, ClassNotFoundException {
        /*boolean isDeleted=false;
        Connection connection= Dbconnection.getInstance().getConnection();
        //String sql="DELETE FROM boat WHERE boat_Id=?";
        PreparedStatement pstm=connection.prepareStatement("DELETE FROM boat WHERE boat_Id=?");

        pstm.setString(1,bId);
        isDeleted=pstm.executeUpdate()>0;
        return  isDeleted;*/
        return SqlUtil.execute("DELETE FROM boat WHERE boat_Id=?",bId);
    }




    @Override
    public boolean save(Boat entity) throws SQLException, ClassNotFoundException {
        /*boolean isSaved=false;

        Connection connection = Dbconnection.getInstance().getConnection();

        String sql = "INSERT INTO boat VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getB_Id());
        pstm.setString(2, dto.getB_Name());
        pstm.setString(3, dto.getB_Type());
        pstm.setInt(4, dto.getSheet_Count());
        pstm.setString(5, dto.getDescription());

        isSaved = pstm.executeUpdate() > 0;

        return isSaved;*/
        return SqlUtil.execute("INSERT INTO boat VALUES(?,?,?,?,?)",entity.getB_Id(),entity.getB_Name(),entity.getB_Type(),entity.getSheet_Count(),entity.getDescription());
    }

    @Override
    public boolean update(Boat entity) throws SQLException, ClassNotFoundException {
        /*boolean isUpadate=false;

        Connection connection=Dbconnection.getInstance().getConnection();
        //String sql="UPDATE boat SET boat_Name=?,boat_Type=?,Sheet_Count=?,description=? WHERE boat_Id=?";

        PreparedStatement pstm=connection.prepareStatement("UPDATE boat SET boat_Name=?,boat_Type=?,Sheet_Count=?,description=? WHERE boat_Id=?");

        pstm.setString(1, dto.getB_Name());
        pstm.setString(2, dto.getB_Type());
        pstm.setInt(3,dto.getSheet_Count());
        pstm.setString(4, dto.getDescription());
        pstm.setString(5, dto.getB_Id());

        isUpadate=pstm.executeUpdate()>0;
        return isUpadat;
        */
        return SqlUtil.execute("UPDATE boat SET boat_Name=?,boat_Type=?,Sheet_Count=?,description=? WHERE boat_Id=?",entity.getB_Name(),entity.getB_Type(),entity.getSheet_Count(),entity.getDescription(),entity.getB_Id());
    }

    @Override
    public BoatDto searchBoatName(String bname) throws SQLException, ClassNotFoundException {
        /*Connection connection=Dbconnection.getInstance().getConnection();
        //String sql="SELECT * FROM boat WHERE boat_Name=?";
        PreparedStatement pstm=connection.prepareStatement("SELECT * FROM boat WHERE boat_Name=?");

        pstm.setString(1,bname);
        ResultSet resultSet=pstm.executeQuery();
        BoatDto dto=null;

        if (resultSet.next()){
            String bID=resultSet.getString(1);
            String bName=resultSet.getString(2);
            String bType=resultSet.getString(3);
            int SheetCount=resultSet.getInt(4);
            String Des=resultSet.getString(5);

            dto=new BoatDto(bID,bName,bType,SheetCount,Des);

        }
        return dto;*/
        ResultSet resultSet= SqlUtil.execute("SELECT * FROM boat WHERE boat_Name=?",bname);
        if (resultSet.next()){
            return new BoatDto(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5));

        }

        return null;
    }

    @Override
    public ArrayList<Boat> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = Dbconnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM boat");
        ArrayList<BoatDto> getAllBotas=new ArrayList<>();
        while (rst.next()){
            BoatDto boatDto=new BoatDto(rst.getString("boat_Id"),rst.getString("boat_Name"),rst.getString("boat_Type"), rst.getInt("Sheet_Count"), rst.getString("description"));
            getAllBotas.add(boatDto);
        }
        return getAllBotas;*/
        ResultSet rst = SqlUtil.execute("SELECT * FROM boat");
        ArrayList<Boat> getAllBoats=new ArrayList<>();
        while (rst.next()){
            Boat boat=new Boat(rst.getString("boat_Id"),rst.getString("boat_Name"),rst.getString("boat_Type"), rst.getInt("Sheet_Count"), rst.getString("description"));
            getAllBoats.add(boat);
        }
        return getAllBoats;

    }

    @Override
    public Boat search(String boatId) throws SQLException, ClassNotFoundException {
         /*Connection connection=Dbconnection.getInstance().getConnection();
        //String sql="SELECT * FROM boat WHERE boat_Id=?";
        PreparedStatement pstm=connection.prepareStatement("SELECT * FROM boat WHERE boat_Id=?");

        pstm.setString(1,boatId);
        ResultSet resultSet=pstm.executeQuery();
        BoatDto dto=null;

        if (resultSet.next()){
            String bID=resultSet.getString(1);
            String bName=resultSet.getString(2);
            String bType=resultSet.getString(3);
            int SheetCount=resultSet.getInt(4);
            String Des=resultSet.getString(5);

            dto=new BoatDto(bID,bName,bType,SheetCount,Des);

        }
        return dto;*/
        ResultSet resultSet= SqlUtil.execute("SELECT * FROM boat WHERE boat_Id=?",boatId);
        if (resultSet.next()){
            return new Boat(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5));

        }

        return null;
    }



    @Override
    public List<String> findAllIds() throws SQLException {
        Connection connection = Dbconnection.getInstance().getConnection();

        //String sql = "SELECT boat_Id FROM boat";
        ResultSet resultSet = connection.prepareStatement( "SELECT boat_Id FROM boat").executeQuery();

        List<String> boatList = new ArrayList<>();

        while (resultSet.next()) {
            boatList.add(resultSet.getString(1));
        }
        return boatList;
    }

    @Override
    public int allBoatCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=SqlUtil.execute( "SELECT COUNT(b.boat_Id) FROM boat b");
        int allBoatsCount=0;
        if (resultSet.next()){
            allBoatsCount=resultSet.getInt(1);
        }
        return allBoatsCount;
    }
}

