package com.jb.facade;

import com.jb.beans.Category;
import com.jb.beans.Coupon;
import com.jb.beans.Customer;
import com.jb.dao.CompaniesDAO;
import com.jb.dao.CustomersDAO;

import java.util.List;

public class CustomerFacade extends ClientFacade{

    private int customerId;

    public CustomerFacade(CustomersDAO customersDAO) {
        super(customersDAO);
    }

    public int getCompanyId() {
        return customerId;
    }

    public void setCompanyId(int customerId) {
        this.customerId = customerId;
    }

    public boolean login(String email, String password) {
        return false;
    }

    public void purchaseCoupon(Coupon coupon) {

    }

    public List<Coupon> getCustomerCoupon () {
        return null;
    }

    public List<Coupon> getCustomerCoupon (Category category) {
        return null;
    }

    public List<Coupon> getCustomerCoupon (double maxPrice) {
        return null;
    }

    public Customer getCustomerDetails(Customer customer) {
        return null;
    }

}
