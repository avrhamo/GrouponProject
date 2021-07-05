package com.jb.clients;

import com.jb.exception.CustomCouponSystemException;
import com.jb.exception.ExceptionsMap;
import com.jb.facade.AdminFacade;
import com.jb.facade.ClientFacade;
import com.jb.facade.CompanyFacade;
import com.jb.facade.CustomerFacade;

import java.sql.SQLException;

public class LoginManager {

    private static LoginManager loginManager = null;

    private LoginManager() {
    }

    public static LoginManager getInstance() {
        if (loginManager == null) {
            loginManager = new LoginManager();
        }
        return loginManager;
    }

    public ClientFacade login(String email, String password, ClientType clientType) throws CustomCouponSystemException, SQLException {
        try {
            switch (clientType) {
                case ADMIN:
                    AdminFacade adminFacade = new AdminFacade();
                    adminFacade.login(email, password);
                    return adminFacade;

                case COMPANY:
                    CompanyFacade companyFacade = new CompanyFacade();
                    companyFacade.login(email, password);
                    return companyFacade;

                case CUSTOMER:
                    CustomerFacade customerFacade = new CustomerFacade();
                    customerFacade.login(email, password);
                    return customerFacade;

                default:
                    throw new CustomCouponSystemException(ExceptionsMap.ERROR_LOGIN);
            }
        }
        catch(CustomCouponSystemException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }
}
