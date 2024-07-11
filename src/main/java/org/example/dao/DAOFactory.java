package org.example.dao;

import org.example.dao.custom.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;
    }
    public  enum DAOTypes{
        ATTENDANCE,BOAT,BOOKING,CUSTOMER,EMPLOYEE,EMPLOYEE_BOAT_DETAIL,PAYMENT,RIDE,RIDE_BOAT_DETAIL,SALARY,USER
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case ATTENDANCE:
                return new EmpAttendenceDAOImpl();
            case BOAT:
                return new BoatDAOImpl();
            case BOOKING:
                return new BookingDAOImpl();
            case CUSTOMER:
                return  new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case EMPLOYEE_BOAT_DETAIL:
                return new BoatEmployeeDetailDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case RIDE:
                return new RideDAOImpl();
            case RIDE_BOAT_DETAIL:
                return new RideBoatDetailDAOImpl();
            case SALARY:
                return new EmpSalaryDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:return null;

        }
    }
}

