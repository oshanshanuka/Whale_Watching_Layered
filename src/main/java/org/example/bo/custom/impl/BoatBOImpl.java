package org.example.bo.custom.impl;

import org.example.bo.custom.BoatBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.BoatDAO;
import org.example.dto.BoatDto;
import org.example.entity.Boat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BoatBOImpl implements BoatBO{
    //BoatDAO boatDAO=new BoatDAOImpl();
    BoatDAO boatDAO= (BoatDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOAT);

    @Override
    public boolean deleteBoat(String bId) throws SQLException, ClassNotFoundException {
        return boatDAO.delete(bId);
    }

    @Override
    public boolean saveBoat(BoatDto dto) throws SQLException, ClassNotFoundException {
        return boatDAO.save(new Boat(dto.getB_Id(), dto.getB_Name(),dto.getB_Type(),dto.getSheet_Count(),dto.getDescription()));
    }

    @Override
    public boolean updateBoat(BoatDto dto) throws SQLException, ClassNotFoundException {
        return boatDAO.update(new Boat(dto.getB_Id(), dto.getB_Name(),dto.getB_Type(),dto.getSheet_Count(),dto.getDescription()));
    }

    @Override
    public BoatDto searchBoatName(String bname) throws SQLException, ClassNotFoundException {
        return boatDAO.searchBoatName(bname);
    }

    @Override
    public ArrayList<BoatDto> getAllBoats() throws SQLException, ClassNotFoundException {
        ArrayList<Boat> all=boatDAO.getAll();
        ArrayList<BoatDto> boatDto=new ArrayList<>();

        for (Boat boat:all){
            boatDto.add(new BoatDto(boat.getB_Id(),boat.getB_Name(),boat.getB_Type(),boat.getSheet_Count(),boat.getDescription()));
        }
        return boatDto;
    }

    @Override
    public BoatDto searchBoat(String boatId) throws SQLException, ClassNotFoundException {
        Boat boat=boatDAO.search(boatId);

        System.out.println("saman kumar"+boat);

        BoatDto boatDto=new BoatDto(boat.getB_Id(),boat.getB_Name(),boat.getB_Type(),boat.getSheet_Count(),boat.getDescription());
        return boatDto;
    }

    @Override
    public List<String> findAllIds() throws SQLException, ClassNotFoundException {
        return boatDAO.findAllIds();
    }
}
