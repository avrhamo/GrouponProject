package com.jb.facade;

import com.jb.beans.Company;
import com.jb.beans.Customer;
import com.jb.dao.CompaniesDAO;
import com.jb.dao.CouponsDAO;
import com.jb.dao.CustomersDAO;

import java.util.List;

public class AdminFacade extends ClientFacade{

    private final String EMAIL = "admin@admin.com";
    private final String PASSWORD = "admin";

    public AdminFacade(CompaniesDAO companiesDAO) {
        super(companiesDAO);
    }

    public AdminFacade(CustomersDAO customersDAO) {
        super(customersDAO);
    }

    public AdminFacade(CouponsDAO couponsDAO) {
        super(couponsDAO);
    }

    public void AdminFacade() {

    }

    public boolean login(String email, String password) {
        return EMAIL == email && PASSWORD == password;
    }


    public void addCompany(Company company) {

    }

    public void updateCompany(Company company) {

    }

    public void deleteCompany(Company company) {

    }

    public List<Company> getAllCompanies() {

        return null;
    }

    public Company getOneCompany(int companyId) {
        return null;
    }

    public void addCustomer(Customer customer) {

    }

    public void updateCustomer(Customer customer) {

    }

    public void deleteCustomer(Customer customer) {

    }

    public List<Customer> getAllCustomers() {

        return null;
    }

    public Customer getOneCustomer(int customerId) {
        return null;
    }


}
