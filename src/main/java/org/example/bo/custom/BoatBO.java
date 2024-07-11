package org.example.bo.custom;

import  org.example.bo.SuperBO;
import org.example.dto.BoatDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public interface BoatBO extends SuperBO {
    public boolean deleteBoat(String bId) throws SQLException, ClassNotFoundException;
    public boolean saveBoat(BoatDto dto) throws SQLException,ClassNotFoundException;
    public boolean updateBoat(BoatDto dto) throws SQLException, ClassNotFoundException;
    public BoatDto searchBoatName(String bname) throws SQLException, ClassNotFoundException;
    public ArrayList<BoatDto> getAllBoats() throws SQLException, ClassNotFoundException ;
    public BoatDto searchBoat(String boatId) throws SQLException, ClassNotFoundException;
    public List<String> findAllIds() throws SQLException,ClassNotFoundException;

}

