package com.jb.job;

import com.jb.beans.Coupon;
import com.jb.dao.CouponsDAO;
import com.jb.dbdao.CouponsDBDAO;

import java.sql.SQLException;
import java.util.List;

public class CouponExpirationDailyJob implements Runnable{

    private boolean quit;
    private CouponsDAO couponsDAO;

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(5000);
                couponsDAO.getExpiredCoupons().forEach(coupon -> {
                    try {
                        couponsDAO.deleteCoupon(coupon.getId());
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                });
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public CouponExpirationDailyJob() {
        couponsDAO = new CouponsDBDAO();
        this.quit = false;
    }

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }
}
