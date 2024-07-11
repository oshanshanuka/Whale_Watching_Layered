package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.EmpSalaryBO;
import org.example.bo.custom.EmployeeBO;
import org.example.db.Dbconnection;
import org.example.dto.EmpSalaryDto;
import org.example.dto.EmployeeDto;
import org.example.dto.tm.EmpSalaryTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmpSalaryFormController {
    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDayPayment;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colSalaryId;
    @FXML
    private DatePicker datePicker;

    @FXML
    private Label lblName;

    @FXML
    private Label lblRole;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<EmpSalaryTm> tblEmpSalary;

    @FXML
    private TextField txtPaymentAmount;

    @FXML
    private TextField txtSalaryId;

    @FXML
    private TextField txtSearchId;

    //EmployeeModel employeeModel = new EmployeeModel();
    //EmployeeDAO employeeDAO=new EmployeeDAOImpl();
    //EmployeeBO employeeBO=new EmployeeBOImpl();
    EmployeeBO employeeBO=(EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    //EmpSalaryModel empSalaryModel = new EmpSalaryModel();
    //EmpSalaryDAO empSalaryDAO=new EmpSalaryDAOImpl();
    //EmpSalaryBO empSalaryBO=new EmpSalaryBOImpl();
    EmpSalaryBO empSalaryBO=(EmpSalaryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SALARY);

    //EmployeeModel employeeModels = new EmployeeModel();

    @FXML
    void btnAddAction(ActionEvent event) throws SQLException {
        String SalaryId=txtSalaryId.getText();
        String empId=txtSearchId.getText();
        String date=String.valueOf(LocalDate.now());
        //String empname=lblName.getText();
        double Salary=Double.parseDouble(txtPaymentAmount.getText());
        //String empId=txtSearchId.getText();
        System.out.println(Salary);

        try {
            System.out.println("enawada");
            //boolean isUpdate = empSalaryDAO.isUpdated(empId,date);
            boolean isUpdate=empSalaryBO.isUpdated(empId,date);
            // System.out.println("enawada");
            System.out.println(isUpdate);
            System.out.println("==================================");
            if (isUpdate){
                boolean isSaved = empSalaryBO.manage(new EmpSalaryDto(SalaryId,empId, date,Salary));
                System.out.println(isSaved);
                if (isSaved) {
                    System.out.println("//////////////////////////////////");
                    new Alert(Alert.AlertType.CONFIRMATION, lblName.getText() + "is Salary Mark Successfully").show();
                    initialize();
                }
            }else if (!isUpdate) {
                new Alert(Alert.AlertType.ERROR,lblName.getText() +" Is Salary Is Noted Today").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void btnsalaryDetails(ActionEvent event) {
        try {
            InputStream resourceAsStream = getClass().getResourceAsStream("/report/SalaryDetails.jrxml");
            JasperDesign load = JRXmlLoader.load(resourceAsStream);

            JRDesignQuery jrDesignQuery = new JRDesignQuery();
            jrDesignQuery.setText("select s.date,s.sal_Id,s.emp_Id,e.name,s.amount from salary s join employee e on s.emp_Id=e.emp_Id ");
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

    public void initialize(){
        datePicker.setValue(LocalDate.now());
        loadSalary();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        //colSalaryId.setCellValueFactory(new PropertyValueFactory<>("sal_Id"));
        colSalaryId.setCellValueFactory(new PropertyValueFactory<>("sal_Id"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("emp_Id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDayPayment.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void loadSalary() {
        tblEmpSalary.getItems().clear();

        try {
            ArrayList<EmpSalaryDto> allSalary=empSalaryBO.getAllSalarys();
            System.out.println(allSalary);;
            for (EmpSalaryDto employeeDto:allSalary){
                tblEmpSalary.getItems().add(
                        new EmpSalaryTm(employeeDto.getSal_Id(),
                                employeeDto.getEmp_Id(),
                                employeeDto.getDate(),
                                employeeDto.getAmount())
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    private void empFeild(EmployeeDto dto) {
        lblName.setText(dto.getName());
        lblRole.setText(dto.getRole());
    }

    @FXML
    void btnSearchEmpIdAction(ActionEvent event) {
        String id = txtSearchId.getText();
        try {
            EmployeeDto dto = employeeBO.searchEmployee(id);

            if(dto != null) {
                empFeild(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Employee is not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

}

