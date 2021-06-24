package com.jb.playground;

import com.jb.beans.Category;
import com.jb.beans.Company;
import com.jb.beans.Coupon;
import com.jb.dao.CompaniesDAO;
import com.jb.dao.CouponsDAO;
import com.jb.db.DatabaseManager;
import com.jb.doa.CategoriesDBDAO;
import com.jb.doa.CompaniesDBDAO;
import com.jb.doa.CouponsDBDAO;
import com.jb.utils.ArtUtils;
import com.jb.utils.DBUtils;

import java.sql.Date;
import java.sql.SQLException;

public class TestCoupon {

    public static void main(String[] args) throws SQLException {
        System.out.println("START");

        System.out.println(ArtUtils.INSERT);
        //Drop and Create DB
        DatabaseManager.DropAndCreate();
        //Access to company table
        CompaniesDAO CompanyDAO = new CompaniesDBDAO();

        //Add categories
        CategoriesDBDAO.addCategories();

        // Insert companies
        System.out.println(ArtUtils.INSERT);
        Company c1 = new Company("NIKE1","nikeEmail1@nike.com","1nike1234",null);
        Company c2 = new Company("NIKE2","nikeEmail2@nike.com","2nike1234",null);
        Company c3 = new Company("NIKE3","nikeEmail3@nike.com","3nike1234",null);

        CompanyDAO.addCompany(c1);
        CompanyDAO.addCompany(c2);
        CompanyDAO.addCompany(c3);


//        DBUtils.runQuery("INSERT INTO `bhp-g2-coup-sys-p2`.`categories` (`id`, `name`) VALUES ('1', 'FOOD');");
//        DBUtils.runQuery("INSERT INTO `bhp-g2-coup-sys-p2`.`categories` (`id`, `name`) VALUES ('2', 'PC');");
//        DBUtils.runQuery("INSERT INTO `bhp-g2-coup-sys-p2`.`categories` (`id`, `name`) VALUES ('3', 'VACATION');");

        //Access to coupon table
        CouponsDAO couponsDAO = new CouponsDBDAO();

        //Creating coupons
        Coupon coupon1 = new Coupon(1,1,1,"Cool Discount1", "Cool Discount1", Date.valueOf("2021-01-01"), Date.valueOf("2026-01-01"), 1000, 58.50, "somewhere1");
        Coupon coupon2 = new Coupon(2,2,2,"Cool Discount2", "Cool Discount2", Date.valueOf("2021-02-01"), Date.valueOf("2026-02-01"), 2000, 58.50, "somewhere2");
        Coupon coupon3 = new Coupon(3,3,3,"Cool Discount3", "Cool Discount3", Date.valueOf("2021-03-01"), Date.valueOf("2026-03-01"), 3000, 58.50, "somewhere3");

        System.out.println(coupon1.toString());
        //Insert to DB
        couponsDAO.addCoupon(coupon1);
        couponsDAO.addCoupon(coupon2);
        couponsDAO.addCoupon(coupon3);









        System.out.println("END");
    }
}
