package com.jb.dao;

import com.jb.beans.Company;
import com.jb.beans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CouponsDAO {

    void addCoupon(Coupon coupon) throws SQLException;

    void updateCoupon(Coupon coupon) throws SQLException;

    void deleteCoupon(int couponId) throws SQLException;

    void deleteCompanyCoupon(int couponId) throws SQLException;

    List<Coupon> getAllCoupons() throws SQLException, InterruptedException;

    List<Coupon> getCompanyAllCoupons(int companyId) throws SQLException, InterruptedException;

    Coupon getOneCoupon(int couponId) throws SQLException;

    void addCouponPurchase(int customerId, int couponId) throws SQLException;

    void deleteCouponPurchase(int customerId, int couponId)throws SQLException;

    boolean isCouponNameExistUnderCompany(int companyId, String title ) throws SQLException;

    List<Coupon> getAllCouponsByCategory(int companyId, int categoryId) throws SQLException, InterruptedException;

    List<Coupon> getAllCouponsUnderMaxPrice(int companyId, double maxPrice) throws SQLException, InterruptedException;

    boolean isCouponValidToPurchase(int couponId) throws SQLException;

    List<Coupon> getAllCustomerCoupons(int customerId) throws SQLException, InterruptedException;

    List<Coupon> getExpiredCoupons() throws SQLException, InterruptedException;
}
