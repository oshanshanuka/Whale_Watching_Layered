package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.dto.BoatDto;
import org.example.entity.Boat;

import java.sql.SQLException;
import java.util.List;

public interface BoatDAO extends CrudDAO<Boat> {
    // public boolean delete(String bId) throws SQLException, ClassNotFoundException;
    //  public boolean save(BoatDto dto) throws SQLException,ClassNotFoundException;
    // public boolean update(BoatDto dto) throws SQLException, ClassNotFoundException;
    public BoatDto searchBoatName(String bname) throws SQLException, ClassNotFoundException;
    //public ArrayList<BoatDto> getAll() throws SQLException, ClassNotFoundException ;
    //public BoatDto search(String boatId) throws SQLException, ClassNotFoundException;
    public List<String> findAllIds() throws SQLException,ClassNotFoundException;
    int allBoatCount() throws  SQLException,ClassNotFoundException;
}
