package org.example.controller;


import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.BookingBO;
import org.example.bo.custom.CustomerBO;
import org.example.bo.custom.RideBO;
import org.example.common.IDGenerator;
import org.example.db.Dbconnection;
import org.example.dto.*;
import org.example.dto.tm.BookingTm;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class BookingFormController {
    @FXML
    private JFXComboBox<String> cmbCustomer_Id;

    @FXML
    private JFXComboBox<String> cmbRideId;

    @FXML
    private TableColumn<?, ?> colBooking_Id;

    @FXML
    private TableColumn<?, ?> colCus_Id;

    @FXML
    private TableColumn<?, ?> colCus_name;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPay_Id;

    @FXML
    private TableColumn<?, ?> colRideType;

    @FXML
    private TableColumn<?, ?> colAmount;


    @FXML
    private TableColumn<?, ?> colRide_Id;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblRide_Price;

    @FXML
    private Label lblRide_Type;

    @FXML
    private Label lblRideAvailability;


    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BookingTm> t;

    @FXML
    private Label tblBooking;


    @FXML
    private TextField txtBooking_Id;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtTime;

    @FXML
    private Label lblFreeSpace;

    @FXML
    private Label lblSeatsCount;

    BookingBO bookingBO=(BookingBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOKING);

    //BookingBO bookingBO=new BookingBOImpl();
    //BookingDAO bookingDAO=new BookingDAOImpl();
    //CustomerDAO customerDAO=new CustomerDAOImpl();
    //CustomerBO customerBO=  new CustomerBOImpl();
    CustomerBO customerBO=(CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    //RideDAO rideDAO=new RideDAOImpl();
    //RideBO rideBO=new RideBOImpl();
    RideBO rideBO=(RideBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RIDE);
    //RideBoatDetailDAO rideBoatDetailDAO=new RideBoatDetailDAOImpl();
    //RideBoatDetailBO rideBoatDetailBO=new RideBoatDetailBOImpl();
    //RideBoatDetailBO rideBoatDetailBO=(RideBoatDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RIDE_BOAT_DETAIL);


    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        //BookingModel bookingModel = new BookingModel();
        //BookingDAOImpl bookingDAO=new BookingDAOImpl();

        String payId = getPaymentId();
        double price = Double.parseDouble(lblRide_Price.getText());

        PaymentDto paymentDto = new PaymentDto(payId, txtDate.getValue().toString(), price);

        BookingDto bookingDto = new BookingDto(
                txtBooking_Id.getText(),
                cmbRideId.getSelectionModel().getSelectedItem(),
                cmbCustomer_Id.getSelectionModel().getSelectedItem(),
                payId,
                txtDate.getValue().toString(),
                txtTime.getText(),
                paymentDto
        );

        boolean isAdded = bookingBO.saveBooking(bookingDto);
        if (isAdded) {
            new Alert(Alert.AlertType.CONFIRMATION, "Booking Success").show();
            //Mail();
            loadAllBooking();
            BookingBill();


        } else {
            new Alert(Alert.AlertType.ERROR, "Booking Failed!");
        }
    }

    private void BookingBill() {
        String cus_Id=cmbCustomer_Id.getValue();
        String ride_Id=cmbRideId.getValue();
        try {
            InputStream resourceAsStream = getClass().getResourceAsStream("/report/BookingRideDetails.jrxml");
            JasperDesign load = JRXmlLoader.load(resourceAsStream);

            JRDesignQuery jrDesignQuery = new JRDesignQuery();
            jrDesignQuery.setText(" SELECT bo.booking_Id, c.name, r.ride_Id, r.time, r.typ, p.amount FROM booking bo JOIN customer c ON bo.cus_Id = c.cus_Id JOIN ride r ON bo.ride_Id = r.ride_Id JOIN payment p ON bo.pay_Id = p.pay_Id WHERE bo.cus_Id = '"+cus_Id+"' AND bo.ride_Id = '"+ride_Id+"' AND bo.pay_Id = p.pay_Id");
            load.setQuery(jrDesignQuery);

            JasperReport compiledReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    compiledReport,
                    null,
                    Dbconnection.getInstance().getConnection()
            );
            JasperViewer.viewReport(jasperPrint,false);
        }catch (JRException | SQLException e){
            e.printStackTrace();
        }
    }

    private void loadAllBooking() {



        //var model = new BookingModel();
        // BookingDAOImpl bookingDAO=new BookingDAOImpl();

        ObservableList<BookingTm> obList = FXCollections.observableArrayList();

        try {
            List<BookingCustomDto> dtoList = bookingBO.getAllBooking();

            for(BookingCustomDto dto : dtoList) {
                obList.add(
                        new BookingTm(
                                dto.getDate(),
                                dto.getTime(),
                                dto.getCus_name(),
                                dto.getRide_Type(),
                                dto.getRide_amount()
                        )
                );
            }

            t.setItems(obList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }
    public void initialize() throws Exception {

        setCellValueFactory();
        loadAllBooking();
        loadCustomerIds();
        loadRideIds();
        setBookingId();


    }

    private void setCellValueFactory() {

        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colCus_name.setCellValueFactory(new PropertyValueFactory<>("cus_name"));
        colRideType.setCellValueFactory(new PropertyValueFactory<>("ride_type"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("ride_amount"));
    }


    @FXML
    void cmbCustomerOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {



        //var customerModel = new CustomerModel();
        //CustomerDAO customerDAO=new CustomerDAOImpl();

        String cus_Id = cmbCustomer_Id.getSelectionModel().getSelectedItem();
        CustomerDto customerDto = customerBO.searchCustomer(cus_Id);

        lblCustomerName.setText(customerDto.getName());
    }

    void  loadCustomerIds() throws Exception{
        //var customerModel = new CustomerModel();

        //CustomerDAOImpl customerDAO=new CustomerDAOImpl();

        List<String> allIds = customerBO.findAllIds();
        cmbCustomer_Id.getItems().clear();
        for (String Cid: allIds) {
            cmbCustomer_Id.getItems().add(Cid);
        }


    }

    @FXML
    void cmbRideOnActin(ActionEvent event) throws SQLException, ClassNotFoundException {
        //var rideModel = new RideModel();
        //RideDAOImpl rideDAO=new RideDAOImpl();
        String selectedItem = cmbRideId.getSelectionModel().getSelectedItem();
        RideDto rideDto = rideBO.searchRide(selectedItem);
        lblRide_Type.setText(rideDto.getType());
        lblRide_Price.setText(Double.toString(rideDto.getPrice()));
        lblRideAvailability.setText(rideDto.getStatus());

        availableSeatsDisplay();



    }

    void loadRideIds() throws SQLException, ClassNotFoundException {
        //var rideModel = new RideModel();
        //RideDAOImpl rideDAO=new RideDAOImpl();
        List<String> allIds = rideBO.findAllIds();
        cmbRideId.getItems().clear();
        for (String id: allIds) {
            cmbRideId.getItems().add(id);
        }
    }

    void setBookingId() {
        try {
            String bid;
            bid = IDGenerator.getNewID("booking", "booking_Id", "B");
            txtBooking_Id.setText(bid);
        }catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private String getPaymentId() {
        String pid = null;
        try {
            pid = IDGenerator.getNewID("payment", "pay_Id", "P");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return pid;
    }

    private void availableSeatsDisplay() throws SQLException, ClassNotFoundException {


        //RideBoatDetailModel rideBoatDetailModel = new RideBoatDetailModel();
        //RideBoatDetailDAOImpl rideBoatDetailDAO=new RideBoatDetailDAOImpl();
        String selectedItem = cmbRideId.getSelectionModel().getSelectedItem();
        long boatSeatCount = rideBO.findBoatSeatCount(selectedItem);
        long bookedSheets=bookingBO.bookedSheets(selectedItem);
        System.out.println("---------------------------");
        System.out.println(bookedSheets);
        System.out.println("----------------------------");
        long freeSpace=boatSeatCount-bookedSheets;

        lblSeatsCount.setText(String.valueOf(boatSeatCount));
        lblFreeSpace.setText(String.valueOf(freeSpace));

        //////////////////////////////////////////// System.out.println(boatSeatCount);

    }
    @SneakyThrows
    void Mail(){
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add(this.lblCustomerName.getText());
        list.add(lblRide_Type.getText());
        list.add(String.valueOf(txtDate.getValue()));
        list.add(txtTime.getText());
        list.add(lblRide_Price.getText());
        String mail = bookingBO.getMail(cmbCustomer_Id.getValue());
    }

}

