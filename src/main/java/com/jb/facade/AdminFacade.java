package com.jb.facade;

import com.jb.beans.Company;
import com.jb.beans.Customer;

import java.util.List;

public class AdminFacade {

    private final String EMAIL = "admin@admin.com";
    private final String PASSWORD = "admin";

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
