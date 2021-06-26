package com.jb.facade;

import com.jb.dao.CompaniesDAO;
import com.jb.dao.CouponsDAO;
import com.jb.dao.CustomersDAO;

public abstract class ClientFacade {

    protected CompaniesDAO companiesDAO;
    protected CustomersDAO customersDAO;
    protected CouponsDAO couponsDAO;

    public boolean login(String email, String password) {
        return false;
    }

    public ClientFacade(CompaniesDAO companiesDAO) {
        this.companiesDAO = companiesDAO;
    }

    public ClientFacade(CustomersDAO customersDAO) {
        this.customersDAO = customersDAO;
    }

    public ClientFacade(CouponsDAO couponsDAO) {
        this.couponsDAO = couponsDAO;
    }

}
