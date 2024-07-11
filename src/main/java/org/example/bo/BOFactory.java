package org.example.bo;

import org.example.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}
    public static BOFactory getBoFactory(){
        return (boFactory==null)?boFactory=new BOFactory():boFactory;
    }
    public  enum BOTypes{
        ATTENDANCE,BOAT,BOOKING,CUSTOMER,EMPLOYEE,EMPLOYEE_BOAT_DETAIL,PAYMENT,RIDE,RIDE_BOAT_DETAIL,SALARY,USER
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case ATTENDANCE:
                return  new EmpAttendenceBOImpl();
            case BOAT:
                return  new BoatBOImpl();
            case BOOKING:
                return  new BookingBOImpl();
            case CUSTOMER:
                return  new CustomerBOImpl();
            case EMPLOYEE:
                return  new EmployeeBOImpl();
            case PAYMENT:
                return  new PaymentBOImpl();
            case RIDE:
                return  new RideBOImpl();

            case SALARY:
                return  new EmpSalaryBOImpl();
            case USER:
                return  new UserBOImpl();
            default:return null;

        }
    }

}


