package com.jb.playground;

import com.jb.beans.Coupon;
import com.jb.db.DatabaseManager;
import com.jb.facade.CompanyFacade;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class TestCompanyFacade {

    public static void main(String[] args) throws SQLException, InterruptedException {
        //Drop and Create DB
        DatabaseManager.DropAndCreate();
        //TestCompany.TestCompanyDB();
        TestCoupon.TestCouponsDB();
//        TestCustomer.TestCustomersDB();

        System.out.println("----- S T A R T ---------------");

        CompanyFacade companyFacade = new CompanyFacade(3);

        Coupon couponx = new Coupon(3, 2, "Cool Discount1", "Cool Discount1 updated!!!", Date.valueOf("2021-01-01"), Date.valueOf("2026-01-01"), 1000, 68.50, "somewhere1");
        Coupon coupony = new Coupon(3, 2, "Cool Discount2", "Cool Discount1 updated!!!", Date.valueOf("2021-01-01"), Date.valueOf("2026-01-01"), 1000, 558.50, "somewhere1");
        Coupon couponz = new Coupon(3, 2, "Cool Discount13", "Cool Discount1 updated!!!", Date.valueOf("2021-01-01"), Date.valueOf("2026-01-01"), 1000, 158.50, "somewhere1");

        companyFacade.addCoupon(couponx);
        companyFacade.addCoupon(coupony);
        companyFacade.addCoupon(couponz);

        companyFacade.deleteCoupon(1);

        //List<Coupon> couponList = companyFacade.getCompanyCoupons();
        companyFacade.getCompanyCoupons().forEach(System.out::println);
        //System.out.println(couponList.toString());

        System.out.println("******** ******** ******** ******** ");

        companyFacade.getAllCouponsByCategory(2).forEach(System.out::println);

        System.out.println("******** ******** ******** ******** ");
        companyFacade.getCompanyCoupons(150).forEach(System.out::println);

        System.out.println("******** ******** ******** ******** ");
        System.out.println(companyFacade.getCompanyDetails().toString());

        System.out.println("----- E N D ---------------");

    }
}
