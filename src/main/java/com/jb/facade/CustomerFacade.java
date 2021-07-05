package com.jb.facade;

import com.jb.beans.Category;
import com.jb.beans.Coupon;
import com.jb.beans.Customer;
import com.jb.dao.CustomersCouponDAO;
import com.jb.dbdao.CustomerCouponDBDAO;
import com.jb.exception.CustomCouponSystemException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.jb.exception.ExceptionsMap.*;

public class CustomerFacade extends ClientFacade{

    private int customerId;

    public CustomerFacade(int customerId) {
        this.customerId = customerId;
    }

    public CustomerFacade() {
        this.customerId = 0;
    }

    public boolean login(String email, String password) throws SQLException, CustomCouponSystemException {
        if(!customersDAO.isCustomerExists(email, password)) {
            throw new CustomCouponSystemException(ERROR_LOGIN);
        }else {
            this.customerId = customersDAO.getIdCustomerByEmail(email, password);
            return true;
        }
    }

    public void purchaseCoupon(Coupon coupon) throws SQLException, CustomCouponSystemException {
        try{
            CustomersCouponDAO customersCouponsDBDAAO = new CustomerCouponDBDAO();
            boolean isCustomerOwnCoupon = customersCouponsDBDAAO.isExistCustomersCoupons(customerId, coupon.getId());
            boolean isCouponValidToBuy = couponsDAO.isCouponValidToPurchase(coupon.getId());

            if ( isCouponValidToBuy && !isCustomerOwnCoupon){
                couponsDAO.addCouponPurchase(customerId, coupon.getId());
            }else if(!isCouponValidToBuy) {
                throw new CustomCouponSystemException(ERROR_COUPON_SOLD_OUT_OR_EXPIRED);
            }else {
                throw new CustomCouponSystemException(ERROR_CANNOT_BUY_THE_SAME_COUPON);
            }

        }catch (CustomCouponSystemException ex) {
            System.out.println(ex.getMessage());
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
