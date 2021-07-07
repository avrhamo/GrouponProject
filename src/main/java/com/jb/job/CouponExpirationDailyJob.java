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

public class CouponExpirationDailyJob implements Runnable{

    private static boolean quit;
    private CouponsDAO couponsDAO;
    private CustomerCouponDBDAO customerCouponDBDAO;


    public CouponExpirationDailyJob() {
        couponsDAO = new CouponsDBDAO();
        customerCouponDBDAO = new CustomerCouponDBDAO();
        this.quit = false;
    }

    @Override
    public void run() {
        List<Coupon> expiredCoupons;
        while (!quit) {
            try {
                expiredCoupons = couponsDAO.getExpiredCoupons();
                if (expiredCoupons.size() > 0) {
                    expiredCoupons.forEach(coupon -> {
                        try {
                            customerCouponDBDAO.deleteByCouponId(coupon.getId());
                            couponsDAO.deleteCoupon(coupon.getId());
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                    });
                    System.out.println("daily job message : " + expiredCoupons.size() + " - coupons has been deleted ");
                    //Thread.sleep(1000);
                    Thread.sleep(24 * 60 * 60 * 1000);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }



    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public static void quit() {
        quit = true;
    }
}
