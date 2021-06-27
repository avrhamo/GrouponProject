package com.jb.dao;

import java.sql.SQLException;

public interface CustomersCouponDAO {

    public void InsertCustomersCoupons(int customerId, int couponId) throws SQLException;
    public void DeleteRowCustomersCoupons(int customerId, int couponId) throws SQLException;
    public boolean isExistCustomersCoupons(int customerId, int couponId) throws SQLException;

        public void DeleteByCouponId(int couponId) throws SQLException;
}
