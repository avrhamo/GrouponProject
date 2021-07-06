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

    public void deleteCompanyCoupon(int couponId) throws SQLException;

    public List<Coupon> getAllCoupons() throws SQLException, InterruptedException;

    public List<Coupon> getCompanyAllCoupons(int companyId) throws SQLException, InterruptedException;

    public Coupon getOneCoupon(int couponId) throws SQLException;

    public void addCouponPurchase(int customerId, int couponId) throws SQLException;

    public void deleteCouponPurchase(int customerId, int couponId)throws SQLException;

    public boolean isCouponNameExistUnderCompany(int companyId, String title ) throws SQLException;

    public List<Coupon> getAllCouponsByCategory(int companyId, int categoryId) throws SQLException, InterruptedException;

    public List<Coupon> getAllCouponsUnderMaxPrice(int companyId, double maxPrice) throws SQLException, InterruptedException;

    public boolean isCouponValidToPurchase(int couponId) throws SQLException;

    public List<Coupon> getAllCustomerCoupons(int customerId) throws SQLException, InterruptedException;

    public List<Coupon> getExpiredCoupons() throws SQLException, InterruptedException;
}
