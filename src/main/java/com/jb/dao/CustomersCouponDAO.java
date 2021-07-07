package com.jb.dao;

import com.jb.beans.CustomerVsCoupon;

import java.sql.SQLException;
import java.util.List;

public interface CustomersCouponDAO {

    void InsertCustomersCoupons(int customerId, int couponId) throws SQLException;

    void deleteCouponPurchase(int customerId, int couponId) throws SQLException;

    boolean isExistCustomersCoupons(int customerId, int couponId) throws SQLException;

    void deleteByCouponId(int couponId) throws SQLException;

    void DeleteCustomer(int couponId) throws SQLException;

    List<CustomerVsCoupon> getAllByCouponId(int couponId) throws SQLException, InterruptedException;

}
