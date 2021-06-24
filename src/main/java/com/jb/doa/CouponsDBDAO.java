package com.jb.doa;

import com.jb.beans.Coupon;
import com.jb.dao.CouponsDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CouponsDBDAO implements CouponsDAO {

    public static final String QUERY_INSERT_COUPON = "INSERT INTO `bhp-g2-coup-sys-p2`.`coupons` " +
            "(`id`, `company_id`, `category_id`, `title`, `description`, `start_date`, `end_date`, `amount`, `price`, `image`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String QUERY_UPDATE_COUPON_BY_ID = "UPDATE `bhp-g2-coup-sys-p2`.`coupons` " +
            "SET `company_id` = ?, `category_id` = ?, `title` = ?, `description` = ?, `start_date` = ?, `end_date` = ?," +
            " `amount` = ?, `price` = ?, `image` = ? WHERE (`id` = ?);";
    public static final String QUERY_DELETE_COUPON = "DELETE FROM `bhp-g2-coup-sys-p2`.`coupons` WHERE `id` = ? ;";
    public static final String QUERY_GET_ALL_COUPONS = "SELECT * FROM `bhp-g2-coup-sys-p2`.`coupons`";
    public static final String QUERY_GET_ONE_COUPON_BY_ID = "SELECT * FROM `bhp-g2-coup-sys-p2`.`coupons` WHERE `id` = ? ;";
    public static final String QUERY_UPDATE_PURCHASE = "";
    public static final String QUERY_DELETE_PURCHASE = "";


    @Override
    public void addCoupon(Coupon coupon) throws SQLException {

    }

    @Override
    public void updateCoupon(Coupon coupon) throws SQLException {

    }

    @Override
    public void deleteCoupon(int couponId) throws SQLException {

    }

    @Override
    public ArrayList<Coupon> getAllCoupons() throws SQLException {
        return null;
    }

    @Override
    public Coupon getOneCoupon(int couponId) throws SQLException {
        return null;
    }

    @Override
    public void addCouponPurchase(int customerId, int couponId) throws SQLException {

    }

    @Override
    public void deleteCouponPurchase(int customerId, int couponId) throws SQLException {

    }
}
