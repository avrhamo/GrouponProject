package com.jb.facade;

import com.jb.dao.CompaniesDAO;
import com.jb.dao.CouponsDAO;
import com.jb.dao.CustomersDAO;
import com.jb.doa.CompaniesDBDAO;
import com.jb.doa.CouponsDBDAO;
import com.jb.doa.CustomerDBDAO;
import com.jb.exception.CustomCouponSystemException;

import java.sql.SQLException;

public abstract class ClientFacade {

    CompaniesDAO companiesDAO;
    CustomersDAO customersDAO;
    CouponsDAO couponsDAO;

    public ClientFacade() {
        companiesDAO = new CompaniesDBDAO();
        customersDAO = new CustomerDBDAO();
        couponsDAO = new CouponsDBDAO();
    }

    public ClientFacade(String email, String password, int ClientType) {
        companiesDAO = new CompaniesDBDAO();
        customersDAO = new CustomerDBDAO();
        couponsDAO = new CouponsDBDAO();
    }

    public abstract boolean login(String email, String password) throws SQLException, CustomCouponSystemException;


}
