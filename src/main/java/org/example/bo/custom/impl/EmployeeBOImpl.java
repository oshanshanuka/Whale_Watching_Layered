package org.example.bo.custom.impl;


import org.example.bo.custom.EmployeeBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.BoatEmployeeDetailsDAO;
import org.example.dao.custom.EmployeeDAO;
import org.example.db.Dbconnection;
import org.example.dto.EmployeeDto;
import org.example.dto.tm.EmployeeBoatTm;
import org.example.entity.Employee;
import org.example.entity.EmployeeBoatDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO{
    //EmployeeDAO employeeDAO=new EmployeeDAOImpl();
    EmployeeDAO employeeDAO= (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    BoatEmployeeDetailsDAO boatEmployeeDetailsDAO=(BoatEmployeeDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE_BOAT_DETAIL);

    @Override
    public boolean deleteEmployee(String empId) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(empId);
    }

    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        Connection connection= Dbconnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            Employee employee = new Employee(dto.getEmp_Id(),dto.getName(),dto.getAddress(),dto.getTel(),dto.getNic(),dto.getRole());
            boolean isSaved = employeeDAO.save(employee);

            if (!isSaved) {
                connection.rollback();
                return false;
            }

            for (EmployeeBoatTm employeeBoatTm : dto.getEmployeeBoatTms()) {

                EmployeeBoatDetail employeeBoatDetail = new EmployeeBoatDetail(employeeBoatTm.getBoat_Id(), dto.getEmp_Id());

                System.out.println("=====================================//////////////////////");
                System.out.println(employeeBoatDetail);
                System.out.println("========================================//////////////////////");
                boolean isCreate = boatEmployeeDetailsDAO.save(employeeBoatDetail);

                if (!isCreate) {
                    connection.rollback();
                    return false;
                }

            }

            connection.commit();
            return true;

        } catch (Exception ex) {
            System.out.println(ex);
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public EmployeeDto searchEmployeeNIC(String empNic) throws SQLException, ClassNotFoundException {
        return employeeDAO.searchEmployeeNIC(empNic);
    }

    @Override
    public boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getEmp_Id(),dto.getName(),dto.getAddress(),dto.getTel(),dto.getNic(),dto.getRole()));
    }

    @Override
    public EmployeeDto searchEmployee(String id) throws SQLException, ClassNotFoundException {
        Employee employee=employeeDAO.search(id);
        EmployeeDto employeeDto=new EmployeeDto(employee.getEmp_Id(),employee.getName(),employee.getAddress(),employee.getTel(),employee.getNic(),employee.getRole());
        return employeeDto;
    }

    @Override
    public ArrayList<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> all=employeeDAO.getAll();
        ArrayList<EmployeeDto> employeeDtos=new ArrayList<>();

        for (Employee employee:all){
            employeeDtos.add(new EmployeeDto(employee.getEmp_Id(),employee.getName(),employee.getAddress(),employee.getTel(),employee.getNic(),employee.getRole()));
        }
        return employeeDtos;
    }


}

