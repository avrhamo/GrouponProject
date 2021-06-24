package com.jb.dao;

import com.jb.beans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CouponsDAO {

    public void addCoupon(Coupon coupon) throws SQLException;
    public void updateCoupon(Coupon coupon) throws SQLException;
    public void deleteCoupon(int couponId) throws SQLException;
    public ArrayList<Coupon> getAllCoupons() throws SQLException;
    public Coupon getOneCoupon(int couponId) throws SQLException;
    public void addCouponPurchase(int customerId, int couponId) throws SQLException;
    public void deleteCouponPurchase(int customerId, int couponId)throws SQLException;

}
