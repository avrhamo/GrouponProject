package com.jb.playground;

import com.jb.beans.Coupon;
import com.jb.dao.CouponsDAO;
import com.jb.db.DatabaseManager;
import com.jb.doa.CouponsDBDAO;
import com.jb.facade.CustomerFacade;

import java.sql.Date;
import java.sql.SQLException;

public class TestCustomerFacade {

    public static void main(String[] args) throws SQLException, InterruptedException {

        //Drop and Create DB
        DatabaseManager.DropAndCreate();
        //TestCompany.TestCompanyDB();
        TestCoupon.TestCouponsDB();
//        TestCustomer.TestCustomersDB();

        System.out.println("----- S T A R T ---------------");
        //Creating coupons
        Coupon coupon1 = new Coupon(1, 1, 1, "Cool Discount1", "Cool Discount1", Date.valueOf("2021-01-01"), Date.valueOf("2026-01-01"), 1000, 58.50, "somewhere1");
        Coupon coupon2 = new Coupon(2, 2, 2, "Cool Discount2", "Cool Discount2", Date.valueOf("2021-02-01"), Date.valueOf("2026-02-01"), 2000, 58.50, "somewhere2");
        Coupon coupon3 = new Coupon(3, 3, 3, "Cool Discount3", "Cool Discount3", Date.valueOf("2021-03-01"), Date.valueOf("2026-03-01"), 3000, 58.50, "somewhere3");
        Coupon coupon4 = new Coupon(4,4, 1, "Cool Discount4", "Cool Discount4", Date.valueOf("2021-01-01"), Date.valueOf("2026-04-01"), 0, 58.50, "somewhere1");
        Coupon coupon5 = new Coupon(5,5, 2, "Cool Discount5", "Cool Discount5", Date.valueOf("2021-02-01"), Date.valueOf("2026-02-01"), 2000, 58.50, "somewhere2");
        Coupon coupon6 = new Coupon(6,6, 3, "Cool Discount6", "Cool Discount6", Date.valueOf("2021-03-01"), Date.valueOf("2026-03-01"), 3000, 58.50, "somewhere3");

        CustomerFacade customerFacade = new CustomerFacade(1);

        customerFacade.purchaseCoupon(coupon4);
        customerFacade.purchaseCoupon(coupon5);

        customerFacade.getCustomerCoupons().forEach(System.out::println);


    }
}
