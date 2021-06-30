package com.jb.facade;

import com.jb.beans.Category;
import com.jb.beans.Coupon;
import com.jb.beans.Customer;
import com.jb.dao.CompaniesDAO;
import com.jb.dao.CouponsDAO;
import com.jb.dao.CustomersCouponDAO;
import com.jb.dao.CustomersDAO;
import com.jb.doa.CustomerCouponDBDAO;
import com.jb.exception.CustomCouponSystemException;
import com.jb.exception.ExceptionsMap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFacade extends ClientFacade{

    private int customerId;

    public CustomerFacade(int customerId) {
        this.customerId = customerId;
    }

    public CustomerFacade() {
        this.customerId = 0;
    }

//    public CustomerFacade(CustomersDAO customersDAO) {
//        super();
//    }
//
//    public int getCompanyId() {
//        return customerId;
//    }
//
//    public void setCompanyId(int customerId) {
//        this.customerId = customerId;
//    }

    public boolean login(String email, String password) throws SQLException, CustomCouponSystemException {
        if(!customersDAO.isCustomerExists(email, password)) {
            throw new CustomCouponSystemException(ExceptionsMap.ERROR_LOGIN);
        }else {
            this.customerId = customersDAO.getIdCustomerByEmail(email, password);
            return true;
        }
    }

    public void purchaseCoupon(Coupon coupon) throws SQLException {
        if (couponsDAO.isCouponValidToPurchase(coupon.getId()) ){
            couponsDAO.addCouponPurchase(customerId, coupon.getId());
        }
    }

    public List<Coupon> getCustomerCoupons () throws SQLException, InterruptedException {

        return couponsDAO.getAllCustomerCoupons(customerId);
    }

    public List<Coupon> getCustomerCoupons (Category category) throws SQLException, InterruptedException {
        List<Coupon> customerCoupons =  couponsDAO.getAllCustomerCoupons(customerId);
        List<Coupon> customerCouponsAns = new ArrayList<>();
        customerCoupons.forEach(coupon -> {
            if(coupon.getCategoryId() == category.value){
                customerCouponsAns.add(coupon);
            }
        });
        return customerCouponsAns;
    }

    public List<Coupon> getCustomerCoupons (double maxPrice) throws SQLException, InterruptedException {
        List<Coupon> customerCoupons =  couponsDAO.getAllCustomerCoupons(customerId);
        List<Coupon> customerCouponsAns = new ArrayList<>();
        customerCoupons.forEach(coupon -> {
            if(coupon.getPrice() < maxPrice){
                customerCouponsAns.add(coupon);
            }
        });
        return customerCouponsAns;
    }

    public Customer getCustomerDetails() throws SQLException {
        return customersDAO.getOneCustomer(customerId);
    }

}
