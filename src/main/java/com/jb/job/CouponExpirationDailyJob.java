package com.jb.job;

import com.jb.beans.Coupon;
import com.jb.dao.CouponsDAO;
import com.jb.dao.CustomersCouponDAO;
import com.jb.db.ConnectionPool;
import com.jb.dbdao.CouponsDBDAO;
import com.jb.dbdao.CustomerCouponDBDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CouponExpirationDailyJob extends Thread{

    private boolean quit;
    private CouponsDAO couponsDAO;
    private CustomerCouponDBDAO customerCouponDBDAO;

    @Override
    public void run() {
        List<Coupon> expiredCoupons;
        while (!quit) {
            try {
                expiredCoupons = couponsDAO.getExpiredCoupons();
                if (expiredCoupons.size() > 0) {
                    expiredCoupons.forEach(coupon -> {
                        try {
                            customerCouponDBDAO.DeleteByCouponId(coupon.getId());
                            couponsDAO.deleteCoupon(coupon.getId());
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                    });
                    System.out.println(expiredCoupons.size() + " - coupons has been deleted ");
                    Thread.sleep(1000);
                }else {
                    quit = true;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public CouponExpirationDailyJob() {
        couponsDAO = new CouponsDBDAO();
        customerCouponDBDAO = new CustomerCouponDBDAO();
        this.quit = false;
    }

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }
}
