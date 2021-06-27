package com.jb.dao;

import com.jb.beans.Company;
import com.jb.beans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CouponsDAO {

    public void addCoupon(Coupon coupon) throws SQLException;

    public void updateCoupon(Coupon coupon) throws SQLException;

    public void deleteCoupon(int couponId) throws SQLException;

    public List<Coupon> getAllCoupons() throws SQLException, InterruptedException;

    public List<Coupon> getCompanyAllCoupons(Company company) throws SQLException, InterruptedException;

    public Coupon getOneCoupon(int couponId) throws SQLException;

    public void addCouponPurchase(int customerId, int couponId) throws SQLException;

    public void deleteCouponPurchase(int customerId, int couponId)throws SQLException;

}
