package org.example.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import org.example.bo.custom.DashboardBO;
import org.example.bo.custom.impl.DashboardBOImpl;
import org.example.dto.DashboardCommonDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class DashBoardFormController {
    @FXML
    private Label lblBoats;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblEmployees;

    @FXML
    private Label lblLuxuryAvailableSheets;

    @FXML
    private Label lblNormalAvailableSheets1;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTotalBooking;

    @FXML
    private PieChart piechart;

    @FXML
    private Label lblTotalCustomers;

    @FXML
    private Label lblTotalLBooking;

    @FXML
    private Label lblTotalNBooking;
    //DashBoarDAO dashBoarDAO=new DashBoardDAOImpl();
    DashboardBO dashBoardBO= (DashboardBO) new DashboardBOImpl();

    DashboardCommonDto dashboardData =null;


    private void pieChartSetData(){
        double Luxury= Integer.parseInt(lblTotalLBooking.getText());
        double normal= Integer.parseInt(lblTotalNBooking.getText());
        double total= Integer.parseInt(lblTotalBooking.getText());

        Luxury=(Luxury/total)*100;
        normal=(normal/total)*100;

        System.out.println(Luxury);
        System.out.println(normal);
        System.out.println(total);

        ObservableList<PieChart.Data> pieChart= FXCollections.observableArrayList(

                new PieChart.Data("Luxury percentage",Luxury),
                new PieChart.Data("Normal percentage",normal)
        );
      /* pieChart.forEach(
               data ->
                       data.nameProperty().bind(
                               Bindings.concat(
                                       data.getName(),"total",
                                       data.pieValueProperty()
                               )
                       )
       );*/
        //this.piechart.getData().addAll(pieChart);
        this.piechart.setData(pieChart);


    }



    public void initialize() throws SQLException, ClassNotFoundException {

        setDate();
        setTime();
        setDashboardData();
        pieChartSetData();
    }

    private void setDate() {
        String date = String.valueOf(LocalDate.now());
        lblDate.setText(date);
    }
    private void setTime(){
        String time=String.valueOf(LocalTime.now());
        lblTime.setText(time);
    }

    private void setDashboardData() throws SQLException, ClassNotFoundException {
        //DashboardModel dashboardModel = new DashboardModel();
        // DashBoardDAOImpl dashBoardDAO=new DashBoardDAOImpl();

        this.dashboardData = dashBoardBO.getDashboardData();

        lblTotalCustomers.setText(String.valueOf(dashboardData.getAllCustomerCount()));
        lblBoats.setText(String.valueOf(dashboardData.getAllBoatsCount()));
        lblEmployees.setText(String.valueOf(dashboardData.getAllEmployeeCount()));
        lblTotalBooking.setText(String.valueOf(dashboardData.getAllBookingCount()));
        lblTotalNBooking.setText(String.valueOf(dashboardData.getNormalBookingCount()));
        lblTotalLBooking.setText(String.valueOf(dashboardData.getLuxuryBookingCount()));
    }


}

