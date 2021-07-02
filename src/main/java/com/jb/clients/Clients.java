package com.jb.clients;

import com.jb.beans.Company;
import com.jb.exception.CustomCouponSystemException;
import com.jb.exception.ExceptionsMap;
import com.jb.facade.AdminFacade;
import com.jb.facade.ClientFacade;
import com.jb.facade.CompanyFacade;
import com.jb.facade.CustomerFacade;

import java.sql.SQLException;

public class Clients {

    private static Clients loginManager = null;

    private Clients() {    }

    public static Clients getInstance() {
        if(loginManager == null){
            loginManager = new Clients();
        }
        return loginManager;
    }

    public ClientFacade login(String email, String password, int clientType) throws CustomCouponSystemException, SQLException {
        switch (clientType){
            case 1:
                AdminFacade adminFacade = new AdminFacade();
                adminFacade.login(email, password);
                return adminFacade;

            case 2:
                CompanyFacade companyFacade= new CompanyFacade();
                companyFacade.login(email, password);
                return companyFacade;

            case 3:
                CustomerFacade customerFacade = new CustomerFacade();
                customerFacade.login(email, password);
                return customerFacade;

            default:
                throw new CustomCouponSystemException(ExceptionsMap.ERROR_LOGIN);
        }
    }
}
