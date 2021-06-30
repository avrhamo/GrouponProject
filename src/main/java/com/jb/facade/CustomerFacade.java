package com.jb.facade;

import com.jb.beans.Category;
import com.jb.beans.Coupon;
import com.jb.beans.Customer;
import com.jb.dao.CompaniesDAO;
import com.jb.dao.CouponsDAO;
import com.jb.dao.CustomersCouponDAO;
import com.jb.dao.CustomersDAO;
import com.jb.doa.CustomerCouponDBDAO;

import java.sql.SQLException;
import java.util.List;

public class CustomerFacade extends ClientFacade{

    private int customerId;

    public CustomerFacade(int customerId) {
        this.customerId = customerId;
    }

    public CustomerFacade(CustomersDAO customersDAO) {
        super();
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

    public void purchaseCoupon(Coupon coupon) throws SQLException {
        if (couponsDAO.isCouponValidToPurchase(coupon.getId()) ){
            couponsDAO.addCouponPurchase(customerId, coupon.getId());
    }
    }

    public List<Coupon> getCustomerCoupons () throws SQLException, InterruptedException {

        return couponsDAO.getAllCustomerCoupons(customerId);
    }

    public List<Coupon> getCustomerCoupons (Category category) {
        return null;
    }

    public List<Coupon> getCustomerCoupons (double maxPrice) {
        return null;
    }

    public Customer getCustomerDetails(Customer customer) {
        return null;
    }

}
