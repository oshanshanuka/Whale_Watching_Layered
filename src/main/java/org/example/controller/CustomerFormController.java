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
import org.example.bo.custom.CustomerBO;
import org.example.dto.CustomerDto;
import org.example.dto.tm.CustomerTm;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CustomerFormController {
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableColumn<?, ?> colcus_Id;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtcus_Id;
    // CustomerDAO customerDAO=new CustomerDAOImpl();
    //CustomerBO customerBO=new CustomerBOImpl();
    CustomerBO customerBO=(CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void setCellValueFactory() {
        colcus_Id.setCellValueFactory(new PropertyValueFactory<>("cus_Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("e_mail"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void loadAllCustomers() {

        tblCustomer.getItems().clear();
        try {
            /*Get all customer*/

            //CustomerDAO customerDAO=new CustomerDAOImpl();
            ArrayList<CustomerDto> allCustomers=customerBO.getAllCustomers();
            for (CustomerDto customerdto:allCustomers){
                tblCustomer.getItems().add(
                        new CustomerTm(customerdto.getCus_Id(),
                                customerdto.getName(),
                                customerdto.getNic(),
                                customerdto.getAddress(),
                                customerdto.getE_mail(),
                                customerdto.getTel())
                );
            }

            /*Get all customer*/
            /*Connection connection = Dbconnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM customer");
            while (rst.next()) {
                tblCustomer.getItems().add(new CustomerTm(rst.getString("cus_Id"), rst.getString("name"), rst.getString("nic"), rst.getString("address"),rst.getString("e_mail"),rst.getString("tel")));
            }*/
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void btnClearOnAction(ActionEvent event) {

        clearFields();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String cus_Id=txtcus_Id.getText();
        String name=txtName.getText();
        String nic=txtNIC.getText();
        String address=txtAddress.getText();
        String email=txtEmail.getText();
        String tel=txtTel.getText();

        var dto = new CustomerDto(cus_Id, name,nic, address,email, tel);

        //var model = new CustomerModel();
        try {
            //CustomerDAO customerDAO=new CustomerDAOImpl();
            boolean isUpdated=customerBO.updateCustomer(dto);

            /*Connection connection = Dbconnection.getInstance().getConnection();

            //String sql = "UPDATE customer SET name = ?,nic=?, address = ?,e_mail=?, tel = ? WHERE cus_Id = ?";
            PreparedStatement pstm = connection.prepareStatement("UPDATE customer SET name = ?,nic=?, address = ?,e_mail=?, tel = ? WHERE cus_Id = ?");

            pstm.setString(1, dto.getName());
            pstm.setString(2, dto.getNic());
            pstm.setString(3, dto.getAddress());
            pstm.setString(4, dto.getE_mail());
            pstm.setString(5, dto.getTel());
            pstm.setString(6, dto.getCus_Id());

            boolean isUpdate=pstm.executeUpdate() > 0;

            //boolean isUpdated = model.updateCustomer(dto);

            */
            System.out.println(isUpdated);

            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                clearFields();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String cus_Id = txtcus_Id.getText();

        //var customerModel = new CustomerModel();
        try {
            //CustomerDAO customerDAO=new CustomerDAOImpl();
            boolean isDeleted=customerBO.deleteCustomer(cus_Id);


            /*Connection connection= Dbconnection.getInstance().getConnection();

            //String sql = "DELETE FROM customer WHERE cus_Id = ?";
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM customer WHERE cus_Id = ?");
            pstm.setString(1,cus_Id);

            boolean isDeleted= pstm.executeUpdate() > 0;*/

            //boolean isDeleted = customerModel.deleteCustomer(cus_Id);

            if(isDeleted) {
                tblCustomer.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
                clearFields();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isCustomerValidated = ValidateCustomer();

        if (!isCustomerValidated){
            return;
        }

        String cus_Id = txtcus_Id.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String address = txtAddress.getText();
        String e_mail = txtEmail.getText();
        String tel = txtTel.getText();

        var dto = new CustomerDto(cus_Id,name,nic,address,e_mail,tel);

        //var model=new CustomerModel();
        try{
            //CustomerDAO customerDAO=new CustomerDAOImpl();
            boolean isSaved=customerBO.saveCustomer(dto);

            /*Connection connection = Dbconnection.getInstance().getConnection();

            //String sql = "INSERT INTO customer VALUES(?,?,?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?)");

            pstm.setString(1, dto.getCus_Id());
            pstm.setString(2, dto.getName());
            pstm.setString(3, dto.getNic());
            pstm.setString(4, dto.getAddress());
            pstm.setString(5, dto.getE_mail());
            pstm.setString(6, dto.getTel());

            boolean isSaved = pstm.executeUpdate() > 0;*/
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"CUSTOMER IS SAVED!!").show();
                clearFields();
                loadAllCustomers();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"CUSTOMER NOT SAVED").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private boolean ValidateCustomer() {
        String idText = txtcus_Id.getText();
        boolean isCustomerIdValidated = Pattern.matches("[C][0-9]{4}", idText);
        if (!isCustomerIdValidated) {
            txtcus_Id.setStyle("-fx-border-color: red");
            new Alert(Alert.AlertType.ERROR, "invalid Customer Id!").show();
            return false;
        }


        String nameText = txtName.getText();
        boolean isCustomerNameValidated = Pattern.matches("[A-Z][a-z](.*)", nameText);
        if(!isCustomerNameValidated){
            txtName.setStyle("-fx-border-color: red");
            new Alert(Alert.AlertType.ERROR, "invalid Customer Name!").show();
            return false;

        }



        String addressText = txtAddress.getText();
        boolean isAddressValidated = Pattern.matches("[A-Z][a-z](.*)",addressText);
        if(!isAddressValidated){
            txtAddress.setStyle("-fx-border-color: red");
            new Alert(Alert.AlertType.ERROR, "invalid Customer Address!").show();
            return false;

        }



        String EmailAddressText = txtEmail.getText();
        boolean isEmailValidated = Pattern.matches("[A-z](.*)(gmail.com)", EmailAddressText);
        if(!isEmailValidated){
            txtEmail.setStyle("-fx-border-color: red");
            new Alert(Alert.AlertType.ERROR, "invalid Customer E-mail Address!").show();
            return false;

        }


        String ContacText = txtTel.getText();
        boolean isContacValidated = Pattern.matches("[0-9]{10}", ContacText);
        if(!isContacValidated){
            txtTel.setStyle("-fx-border-color: red");
            new Alert(Alert.AlertType.ERROR, "invalid Customer Contac No!").show();
            return false;

        }



        return true;


    }

    private void clearFields() {
        txtcus_Id.setText("");
        txtName.setText("");
        txtNIC.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtTel.setText("");

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

        String cus_Id=txtcus_Id.getText();

        //var model= new CustomerModel();
        //CustomerDAO customerDAO=new CustomerDAOImpl();

        try {
            // System.out.println("enawada");
            CustomerDto dto=customerBO.searchCustomer(cus_Id);


            if(dto!=null){

                fillFields(dto);
            }else {
                new Alert(Alert.AlertType.INFORMATION,"CUSTOMER NOT FOUND!!!").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void customerSearchNic(ActionEvent event) {
        String cus_nic=txtNIC.getText();
        // var model= new CustomerModel();

        try {
            //CustomerDAOImpl customerDAO=new CustomerDAOImpl();
            CustomerDto dto=customerBO.searchCustomerNic(cus_nic);

            // System.out.println("enawada");
            //CustomerDto dto=model.searchCustomerNIC(cus_nic);
            /*Connection connection=Dbconnection.getInstance().getConnection();
            //String sql="SELECT * FROM customer WHERE nic=?";
            PreparedStatement pstm= connection.prepareStatement("SELECT * FROM customer WHERE nic=?");

            pstm.setString(1,cus_nic);

            ResultSet resultSet=pstm.executeQuery();

            CustomerDto dto = null;

            if(resultSet.next()){
                String Id=resultSet.getString(1);
                String cus_name=resultSet.getString(2);
                String nic= resultSet.getString(3);
                String cus_address=resultSet.getString(4);
                String cus_email=resultSet.getString(5);
                String cus_tel=resultSet.getString(6);

                dto = new CustomerDto(Id,cus_name,nic,cus_address,cus_email,cus_tel);

            }*/

            if(dto!=null){

                fillFields(dto);
            }else {
                new Alert(Alert.AlertType.INFORMATION,"CUSTOMER NOT FOUND!!!").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    private void fillFields(CustomerDto dto) {
        txtcus_Id.setText(dto.getCus_Id());
        txtName.setText(dto.getName());
        txtNIC.setText(dto.getNic());
        txtAddress.setText(dto.getAddress());
        txtEmail.setText(dto.getE_mail());
        txtTel.setText(dto.getTel());
    }


    @FXML
    void getSelectedRow(javafx.scene.input.MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) //Checking double click
        {
            txtcus_Id.setText(tblCustomer.getSelectionModel().getSelectedItem().getCus_Id());
            txtName.setText(tblCustomer.getSelectionModel().getSelectedItem().getName());
            txtAddress.setText(tblCustomer.getSelectionModel().getSelectedItem().getAddress());
            txtTel.setText(tblCustomer.getSelectionModel().getSelectedItem().getTel());
            txtEmail.setText(tblCustomer.getSelectionModel().getSelectedItem().getE_mail());
            txtNIC.setText(tblCustomer.getSelectionModel().getSelectedItem().getNic());
        }

    }

}

