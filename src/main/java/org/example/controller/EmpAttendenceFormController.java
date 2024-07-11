package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.EmpAttendenceBo;
import org.example.bo.custom.EmployeeBO;
import org.example.dto.EmpAttendenceDto;
import org.example.dto.EmployeeDto;
import org.example.dto.tm.EmpAttendenceTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmpAttendenceFormController {
    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField txtIsAttendence;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colInTime;

    @FXML
    private TableColumn<?, ?> colOutTime;

    @FXML
    private Label lblName;

    @FXML
    private Label lblRole;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<EmpAttendenceTm> tblEmpAttendence;

    @FXML
    private TextField txtInTime;

    @FXML
    private TextField txtOutTime;

    @FXML
    private TextField txtSearchId;

    @FXML
    private Label lblAName;

    @FXML
    private Label lblArole;


    @FXML
    private Label lblOutTimeA;


    @FXML
    private Label lblInTimeA;

    //EmployeeModel employeeModel = new EmployeeModel();
    //EmployeeDAO employeeDAO=new EmployeeDAOImpl();
    //EmployeeBO employeeBO=new EmployeeBOImpl();
    EmployeeBO employeeBO=(EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);


    //EmpAttendenceModel empAttendenceModel = new EmpAttendenceModel();
    //EmpAttendenceDAO empAttendenceDAO=new EmpAttendenceDAOImpl();
    //EmpAttendenceBo empAttendenceBo=new EmpAttendenceBOImpl();
    EmpAttendenceBo empAttendenceBo=(EmpAttendenceBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ATTENDANCE);

    //EmployeeModel employeeModels = new EmployeeModel();
    //EmployeeDAO employeeDAOs=new EmployeeDAOImpl();
    // EmployeeBO employeeBOs=new EmployeeBOImpl();
    EmployeeBO employeeBOs=(EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    @FXML
    void btnAddAction(ActionEvent event) throws SQLException {
        String id = txtSearchId.getText();
        String intime = txtInTime.getText();
        String outTime = txtOutTime.getText();
        String date = String.valueOf(LocalDate.now());

        try {
            boolean isUpdate = empAttendenceBo.isUpdated(id,date);
            if (isUpdate){
                boolean isSaved = empAttendenceBo.manage(new EmpAttendenceDto(id, date, intime, outTime));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, lblName.getText() + "is Attendence Mark Successfully").show();
                    initialize();
                }
            }else if (!isUpdate) {
                new Alert(Alert.AlertType.ERROR,lblName.getText() +" Is Attendence Is Noted Today").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void btnSearchEmpIdAction(ActionEvent event) throws SQLException {
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
    private void empFeild(EmployeeDto dto) {
        lblName.setText(dto.getName());
        lblRole.setText(dto.getRole());
    }


    void loadAttendence() {

        tblEmpAttendence.getItems().clear();
        try {

            ArrayList<EmpAttendenceDto> allAttendences=empAttendenceBo.getAllAttendeceDetail();
            System.out.println(allAttendences);;
            for (EmpAttendenceDto empAttendenceDto:allAttendences){
                tblEmpAttendence.getItems().add(
                        new EmpAttendenceTm(empAttendenceDto.getEmp_Id(),
                                empAttendenceDto.getDate(),
                                empAttendenceDto.getIn_time(),
                                empAttendenceDto.getOut_time())
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        /*ObservableList<EmpAttendenceTm> obList = FXCollections.observableArrayList();

        try {
            List<EmpAttendenceDto> dtoList = empAttendenceDAO.getAllAttendeceDetail();

            for(EmpAttendenceDto dto : dtoList) {
                obList.add(
                        new EmpAttendenceTm(
                                dto.getEmp_Id(),
                                dto.getDate(),
                                dto.getIn_time(),
                                dto.getOut_time()

                        )
                );
            }
            tblEmpAttendence.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
    }
    void setCellValueFactory() {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("emp_Id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colInTime.setCellValueFactory(new PropertyValueFactory<>("in_time"));
        colOutTime.setCellValueFactory(new PropertyValueFactory<>("out_time"));

    }
    public  void  initialize(){
        datePicker.setValue(LocalDate.now());
        loadAttendence();
        setCellValueFactory();
    }

    @FXML
    void btnSearchAttendenceAction(ActionEvent event) throws SQLException {
        String id = txtIsAttendence.getText();
        String date = String.valueOf(datePicker.getValue());

        try {
            EmpAttendenceDto dto = empAttendenceBo.isMarked(id, date);

            if (dto != null) {
                attField(dto);
                new Alert(Alert.AlertType.INFORMATION, lblName.getText() + " Employee is Prsent").show();
            } else {
                new Alert(Alert.AlertType.INFORMATION, lblName.getText() + " Employee is Absent").show();
            }
        }catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void attField(EmpAttendenceDto dto) throws SQLException, ClassNotFoundException {
        String id = dto.getEmp_Id();
        EmployeeDto wDto = employeeBOs.searchEmployee(id);
        lblAName.setText(wDto.getName());
        lblArole.setText(wDto.getRole());
        lblInTimeA.setText(dto.getIn_time());
        lblOutTimeA.setText(dto.getOut_time());

    }
}

