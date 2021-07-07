package com.jb.dao;

import com.jb.beans.CustomerVsCoupon;

import java.sql.SQLException;
import java.util.List;

public interface CustomersCouponDAO {

    public void InsertCustomersCoupons(int customerId, int couponId) throws SQLException;

    public void DeleteRowCustomersCoupons(int customerId, int couponId) throws SQLException;

    public boolean isExistCustomersCoupons(int customerId, int couponId) throws SQLException;

    public void deleteByCouponId(int couponId) throws SQLException;

    public void DeleteCustomer(int couponId) throws SQLException;

    public List<CustomerVsCoupon> getAllByCouponId(int couponId) throws SQLException, InterruptedException;



}
