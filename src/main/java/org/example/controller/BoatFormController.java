package org.example.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.BoatBO;
import org.example.dto.BoatDto;
import org.example.dto.tm.BoatTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class BoatFormController {
    @FXML
    private TableColumn<?, ?> colBaott_Id;

    @FXML
    private TableColumn<?, ?> colBoatSheetCount;

    @FXML
    private TableColumn<?, ?> colBoatName;

    @FXML
    private TableColumn<?, ?> colBoatType;

    @FXML
    private TableColumn<?, ?> colDesc;


    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BoatTm> tblBoat;

    @FXML
    private TextField txtBName;

    @FXML
    private TextField txtB_Type;

    @FXML
    private TextField txtBoat_Id;

    @FXML
    private TextField txtDes;

    @FXML
    private TextField txtSheetCount;
    //BoatDAO boatDAO=new BoatDAOImpl();
    BoatBO boatBO=(BoatBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOAT);



    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearField();

    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String B_Id=txtBoat_Id.getText();


        try{


            boolean isDeleted=boatBO.deleteBoat(B_Id);

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"BOAT IS DELETED!!!").show();
                clearField();
                loadAllBoat();
            }
            else {
                new Alert(Alert.AlertType.ERROR,"BOAT IS NOT DELETED!!!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {

        String B_Id=txtBoat_Id.getText();
        String B_Name=txtBName.getText();
        String B_Type=txtB_Type.getText();
        int Sheet_Count=Integer.parseInt(txtSheetCount.getText());
        String Description=txtDes.getText();

        var dto=new BoatDto(B_Id,B_Name,B_Type,Sheet_Count,Description);

        //var model=new BoatModel();

        try {
            // BoatDAO boatDAO=new BoatDAOImpl();

            boolean isSaved=boatBO.saveBoat(dto);

            //boolean boatIsSaved = model.saveBoat(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "BOAT IS SAVED").show();
                clearField();
                loadAllBoat();
            } else {
                new Alert(Alert.AlertType.ERROR, "BOAT IS NOT SAVED");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void clearField() {
        txtBoat_Id.setText("");
        txtBName.setText("");
        txtB_Type.setText("");
        txtSheetCount.setText("");
        txtDes.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String B_Id=txtBoat_Id.getText();
        String B_Name=txtBName.getText();
        String B_Type=txtB_Type.getText();
        int Sheet_Count=Integer.parseInt(txtSheetCount.getText());
        String desc=txtDes.getText();

        var dto=new BoatDto(B_Id,B_Name,B_Type,Sheet_Count,desc);

        //var model=new BoatModel();
        try{

            //BoatDAO boatDAO=new BoatDAOImpl();

            boolean isUpdated=boatBO.updateBoat(dto);

            //boolean isUpdate=model.updateBoat(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"BOAT IS UPDATE!!").show();
                clearField();
                loadAllBoat();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"BOAT IS NOT UPDATE!!!").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String boat_Id=txtBoat_Id.getText();

        //var model= new BoatModel();

        try {
            // BoatDAO boatDAO=new BoatDAOImpl();

            BoatDto dto=boatBO.searchBoat(boat_Id);

            //BoatDto dto=model.searchBoat(boat_Id);

            if(dto!=null){

                fillFields(dto);
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Boat NOT FOUND!!!").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void fillFields(BoatDto dto) {
        txtBoat_Id.setText(dto.getB_Id());
        txtBName.setText(dto.getB_Name());
        txtB_Type.setText(dto.getB_Type());
        txtSheetCount.setText(String.valueOf(dto.getSheet_Count()));
        txtDes.setText(dto.getDescription());
    }

    public void initialize() {
        setCellValueFactory();
        loadAllBoat();
    }

    private void setCellValueFactory() {

        colBaott_Id.setCellValueFactory(new PropertyValueFactory<>("boat_Id"));
        colBoatName.setCellValueFactory(new PropertyValueFactory<>("boat_Name"));
        colBoatType.setCellValueFactory(new PropertyValueFactory<>("boat_Type"));
        colBoatSheetCount.setCellValueFactory(new PropertyValueFactory<>("Sheet_Count"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    private void loadAllBoat() {
        tblBoat.getItems().clear();
        try {
            /*Get all boats*/
            //BoatDAO boatDAO=new BoatDAOImpl();
            ArrayList<BoatDto> allBoats=boatBO.getAllBoats();
            System.out.println(allBoats);;
            for (BoatDto boatDto:allBoats){
                tblBoat.getItems().add(
                        new BoatTm(boatDto.getB_Id(),
                                boatDto.getB_Name(),
                                boatDto.getB_Type(),
                                boatDto.getSheet_Count(),
                                boatDto.getDescription())
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void searchBoatNameOnAction(ActionEvent event) {
        String bname=txtBName.getText();
        //var model= new BoatModel();

        try {
            //BoatDAO boatDAO=new BoatDAOImpl();

            BoatDto dto=boatBO.searchBoatName(bname);
            //BoatDto dto=model.searchBoatName(bname);

            if(dto!=null){

                fillFields(dto);
            }else {
                new Alert(Alert.AlertType.INFORMATION,"BOAT NOT FOUND!!!").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



}

