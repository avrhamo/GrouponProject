package com.jb.facade;

import com.jb.beans.Company;
import com.jb.beans.Coupon;
import com.jb.beans.Customer;
import com.jb.beans.CustomerVsCoupon;
import com.jb.dao.CustomersCouponDAO;
import com.jb.dbdao.CompaniesDBDAO;
import com.jb.dbdao.CouponsDBDAO;
import com.jb.dbdao.CustomerCouponDBDAO;
import com.jb.dbdao.CustomerDBDAO;
import com.jb.exception.CustomCouponSystemException;

import java.sql.SQLException;
import java.util.List;

import static com.jb.exception.ExceptionsMap.*;

public class AdminFacade extends ClientFacade {

    private final String EMAIL = "admin@admin.com";
    private final String PASSWORD = "admin";
//    private CustomersCouponDAO customerCouponDBDAO = new CustomerCouponDBDAO();

    public AdminFacade() {
        super();
    }

    public boolean login(String email, String password) throws CustomCouponSystemException {
        try {
            if (!(EMAIL == email && PASSWORD == password)) {
                throw new CustomCouponSystemException(ERROR_LOGIN);
            }
            return true;
        } catch (CustomCouponSystemException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public void addCompany(Company company) throws SQLException, CustomCouponSystemException {
        try {
            if (companiesDAO.isCompanyNameEmailExist(company.getName(), company.getEmail())) {
                throw new CustomCouponSystemException(ERROR_SAME_NAME_COMPANY);
            }
            this.companiesDAO.addCompany(company);
        } catch (CustomCouponSystemException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateCompany(Company company) throws SQLException, CustomCouponSystemException {
        try {
            //Company companyFromDB = companiesDAO.getOneCompany(company.getId());
            Company companyFromDB = companiesDAO.getOneCompany(company.getName(), company.getEmail());
            if (companyFromDB == null) {
                throw new CustomCouponSystemException(ERROR_CANNOT_UPDATE_COMPANY_NAME);
            }
            if (companyFromDB.getId() == company.getId() && companyFromDB.getName() != company.getName()) {
                companiesDAO.updateCompany(company);
            } else throw new CustomCouponSystemException(ERROR_CANNOT_UPDATE_COMPANY_NAME);
        } catch (CustomCouponSystemException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ERROR_CANNOT_UPDATE_COMPANY_NAME.getMessage());
        }
    }

    public void deleteCompany(int companyId) throws SQLException, InterruptedException {
        List<Coupon> companyCoupons = couponsDAO.getCompanyAllCoupons(companyId);
        for (Coupon c: companyCoupons) {
            customersCouponDAO.deleteByCouponId(c.getId());
            couponsDAO.deleteCoupon(c.getId());
        }
        companiesDAO.deleteCompany(companyId);
    }

    public List<Company> getAllCompanies() throws SQLException, InterruptedException {
        return companiesDAO.getAllCompanies();
    }

    public Company getOneCompany(int companyId) throws SQLException {
        return companiesDAO.getOneCompany(companyId);
    }

    public void addCustomer(Customer customer) throws SQLException, CustomCouponSystemException {
        try {
            if (!customersDAO.isEmailExist(customer.getEmail())) {
                customersDAO.addCustomer(customer);
            } else throw new CustomCouponSystemException(ERROR_SAME_EMAIL_CUSTOMER);

        } catch (CustomCouponSystemException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateCustomer(Customer customer) throws SQLException, CustomCouponSystemException {
        try {
            if (customersDAO.getOneCustomer(customer.getId()).getId() == customer.getId()) {
                customersDAO.updateCustomer(customer);
            } else throw new CustomCouponSystemException(ERROR_CANNOT_UPDATE_CUSTOMER_CODE);

        } catch (CustomCouponSystemException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteCustomer(Customer customer) throws SQLException {
        customersCouponDAO.DeleteCustomer(customer.getId());
        customersDAO.deleteCustomer(customer.getId());
    }

    public List<Customer> getAllCustomers() throws SQLException, InterruptedException {

        return customersDAO.getAllCustomers();
    }

    public Customer getOneCustomer(int customerId) throws SQLException {
        return customersDAO.getOneCustomer(customerId);
    }

}
