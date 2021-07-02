package com.jb.facade;

import com.jb.beans.Company;
import com.jb.beans.Coupon;
import com.jb.beans.Customer;
import com.jb.dao.CompaniesDAO;
import com.jb.dao.CouponsDAO;
import com.jb.dao.CustomersCouponDAO;
import com.jb.dao.CustomersDAO;
import com.jb.doa.CompaniesDBDAO;
import com.jb.doa.CouponsDBDAO;
import com.jb.doa.CustomerCouponDBDAO;
import com.jb.doa.CustomerDBDAO;
import com.jb.exception.CustomCouponSystemException;
import com.jb.exception.ExceptionsMap;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.sql.SQLException;
import java.util.List;

import static com.jb.exception.ExceptionsMap.*;

public class AdminFacade extends ClientFacade{

    private final String EMAIL = "admin@admin.com";
    private final String PASSWORD = "admin";
    private CustomersCouponDAO customerCouponDBDAO= new CustomerCouponDBDAO();

    public AdminFacade() {
        super();
        companiesDAO = new CompaniesDBDAO();
        couponsDAO = new CouponsDBDAO();
        customersDAO = new CustomerDBDAO();
    }

    public boolean login(String email, String password) throws CustomCouponSystemException {
        if(! (EMAIL == email && PASSWORD == password)) {
            throw new CustomCouponSystemException(ERROR_LOGIN);
        }
        return true;
    }

    public void addCompany(Company company) throws SQLException, CustomCouponSystemException {

        if (companiesDAO.isCompanyNameEmailExist(company.getName(), company.getEmail()) ) {
            throw new CustomCouponSystemException(ERROR_SAME_NAME_COMPANY);
        }
        this.companiesDAO.addCompany(company);
    }

    public void updateCompany(Company company) throws SQLException , CustomCouponSystemException {
        if (companiesDAO.isCompanyExists(company.getEmail(),company.getPassword())) {
            Company companyFromDB  = companiesDAO.getOneCompany(company.getId());
            if (companyFromDB.getId() == company.getId() || companyFromDB.getName() != company.getName()) {
                companiesDAO.updateCompany(company);
            }
        }else throw new CustomCouponSystemException(ERROR_CANNOT_UPDATE_COMPANY_NAME);
    }

    public void deleteCompany(int companyId) throws SQLException, InterruptedException {
        List<Coupon> companyCoupons = couponsDAO.getCompanyAllCoupons(companyId);
        companyCoupons.forEach(coupon -> {
            try {
                customerCouponDBDAO.DeleteByCouponId(coupon.getId());
                couponsDAO.deleteCoupon(coupon.getId());
                companiesDAO.deleteCompany(companyId);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public List<Company> getAllCompanies() throws SQLException, InterruptedException {
        return companiesDAO.getAllCompanies();
    }

    public Company getOneCompany(int companyId) throws SQLException {
        return companiesDAO.getOneCompany(companyId);
    }

    public void addCustomer(Customer customer) throws SQLException, CustomCouponSystemException {
        if (!customersDAO.isEmailExist(customer.getEmail())) {
            customersDAO.addCustomer(customer);
        }else throw new CustomCouponSystemException(ERROR_SAME_EMAIL_CUSTOMER);
    }

    public void updateCustomer(Customer customer) throws SQLException, CustomCouponSystemException {
        if(customersDAO.getOneCustomer(customer.getId()).getId() == customer.getId() ) {
            customersDAO.updateCustomer(customer);
        }else throw new CustomCouponSystemException(ERROR_CANNOT_UPDATE_CUSTOMER_CODE);
    }

    public void deleteCustomer(Customer customer) throws SQLException {
        customerCouponDBDAO.DeleteCustomer(customer.getId());
        customersDAO.deleteCustomer(customer.getId());
    }

    public List<Customer> getAllCustomers() throws SQLException, InterruptedException {

        return customersDAO.getAllCustomers();
    }

    public Customer getOneCustomer(int customerId) throws SQLException {
        return customersDAO.getOneCustomer(customerId);
    }

}
