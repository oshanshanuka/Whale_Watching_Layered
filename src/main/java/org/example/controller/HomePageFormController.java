package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.example.util.Navigation;

import java.io.IOException;

public class HomePageFormController {
    public void initialize() throws IOException {
        Navigation.switchPaging(txtAnchor,"dashBoardFrom.fxml","Dashboard");
    }

    @FXML
    private AnchorPane txtAnchor;


    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {


        Navigation.switchPaging(txtAnchor,"customerForm.fxml","Customer");


       /* Parent root = FXMLLoader.load(this.getClass().getResource("/view/customerForm.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Customer Manage");
        stage.centerOnScreen();*/

    }
    @FXML
    void btnRideOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(txtAnchor,"RideForm.fxml","Ride");
    }

    @FXML
    void btnReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnBoatOnAction(ActionEvent event) throws IOException {

        Navigation.switchPaging(txtAnchor,"boatForm.fxml","Boat");

    }

    @FXML
    void btnBookingOnAction(ActionEvent event) throws IOException {

        Navigation.switchPaging(txtAnchor,"bookingForm.fxml","Booking");

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {

        Navigation.switchPaging(txtAnchor,"dashBoardFrom.fxml","Dashboard");

    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {

        Navigation.switchPaging(txtAnchor,"employeeForm.fxml","Employee");

    }

    @FXML
    void btnEmployeeSalaryOnAction(ActionEvent event) throws IOException {

        Navigation.switchPaging(txtAnchor,"empSalaryForm.fxml","EmployeeSalary");

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        System.exit(0);

    }

    @FXML
    void btnEmployeeAttendanceOnAction(ActionEvent event) throws IOException {

        Navigation.switchPaging(txtAnchor,"empAttendenceForm.fxml","Employee");

    }

}

