package com.jb.facade;

import com.jb.beans.Company;
import com.jb.beans.Coupon;
import com.jb.beans.Customer;
import com.jb.dao.CompaniesDAO;
import com.jb.dao.CouponsDAO;
import com.jb.dao.CustomersCouponDAO;
import com.jb.dao.CustomersDAO;
import com.jb.doa.CustomerCouponDBDAO;

import java.sql.SQLException;
import java.util.List;

public class AdminFacade extends ClientFacade{

    private final String EMAIL = "admin@admin.com";
    private final String PASSWORD = "admin";
    private CustomersCouponDAO customerCouponDBDAO= new CustomerCouponDBDAO();

    public AdminFacade(CompaniesDAO companiesDAO) {
        super(companiesDAO);
    }

    public AdminFacade(CustomersDAO customersDAO) {
        super(customersDAO);
    }

    public AdminFacade(CouponsDAO couponsDAO) {
        super(couponsDAO);
    }

    public boolean login(String email, String password) {
        return EMAIL == email && PASSWORD == password;
    }

    public void addCompany(Company company) throws SQLException {

        if ( this.companiesDAO.isCompanyNameEmailExist(company.getName(), company.getEmail()) ) {
        //TODO
            //Handle exception
            return;
        }

        this.companiesDAO.addCompany(company);
    }

    public void updateCompany(Company company) throws SQLException {
        //TODO -- handle exception
        if (companiesDAO.isCompanyExists(company.getEmail(),company.getPassword())) {
            Company companyFromDB  = companiesDAO.getOneCompany(company.getId());
            if (companyFromDB.getId() == company.getId() || companyFromDB.getName() != company.getName()) {
                companiesDAO.updateCompany(company);
            }
        }
    }

    public void deleteCompany(Company company) throws SQLException, InterruptedException {
        List<Coupon> companyCoupons = couponsDAO.getCompanyAllCoupons(company);
        companyCoupons.forEach(coupon -> {
            try {
                customerCouponDBDAO.DeleteByCouponId(coupon.getId());
                couponsDAO.deleteCoupon(coupon.getId());
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
