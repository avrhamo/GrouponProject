package com.jb.playground;

import com.jb.beans.Company;
import com.jb.beans.Coupon;
import com.jb.beans.Customer;
import com.jb.dao.CompaniesDAO;
import com.jb.dao.CouponsDAO;
import com.jb.dao.CustomersDAO;
import com.jb.db.DatabaseManager;
import com.jb.dbdao.*;
import com.jb.utils.ArtUtils;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class TestCoupon {

    public static void main(String[] args) throws SQLException, InterruptedException {

        TestCouponsDB();

    }

    public static void TestCouponsDB() throws SQLException, InterruptedException {

        System.out.println("START");

        //Drop and Create DB
        DatabaseManager.DropAndCreate();

        //Create companies,customers and categories
        //TestCompany.TestCompanyDB();
        //TestCustomer.TestCustomersDB();
        CategoriesDBDAO.addCategories();

//        System.out.println(ArtUtils.INSERT);

        //Access to company table
        CompaniesDAO CompanyDAO = new CompaniesDBDAO();

        // Insert companies
//        System.out.println(ArtUtils.INSERT);
        Company company1 = new Company("NIKE1", "nikeEmail1@nike.com", "1nike1234", null);
        Company company2 = new Company("NIKE2", "nikeEmail2@nike.com", "2nike1234", null);
        Company company3 = new Company("NIKE3", "nikeEmail3@nike.com", "3nike1234", null);

        CompanyDAO.addCompany(company1);
        CompanyDAO.addCompany(company2);
        CompanyDAO.addCompany(company3);

//        System.out.println(ArtUtils.INSERT);
        Customer customer1 = new Customer("Dani", "Din", "Dani@Din.mail.com", "1234");
        Customer customer2 = new Customer("David", "Fogel", "nikeEmail2@nike.com", "12345678");
        Customer customer3 = new Customer("Beni", "Davidov", "nikeEmail3@nike.com", "3nike1234");

        CustomersDAO customerDAO = new CustomerDBDAO();
        customerDAO.addCustomer(customer1);
        customerDAO.addCustomer(customer2);
        customerDAO.addCustomer(customer3);

        //Access to coupon table
        CouponsDAO couponsDAO = new CouponsDBDAO();

        //Creating coupons
        Coupon coupon1 = new Coupon(1, 1, 1, "Cool Discount1", "Cool Discount1", Date.valueOf("2021-01-01"), Date.valueOf("2026-01-01"), 1000, 58.50, "somewhere1");
        Coupon coupon2 = new Coupon(2, 2, 2, "Cool Discount2", "Cool Discount2", Date.valueOf("2021-02-01"), Date.valueOf("2026-02-01"), 2000, 5448.50, "somewhere2");
        Coupon coupon3 = new Coupon(3, 3, 3, "Cool Discount3", "Cool Discount3", Date.valueOf("2021-03-01"), Date.valueOf("2026-03-01"), 3000, 538.50, "somewhere3");
        Coupon coupon4 = new Coupon(4, 4, 1, "Cool Discount4", "Cool Discount4", Date.valueOf("2021-01-01"), Date.valueOf("2026-04-01"), 0, 558.50, "somewhere1");
        Coupon coupon5 = new Coupon(5, 5, 2, "Cool Discount5", "Cool Discount5", Date.valueOf("2021-02-01"), Date.valueOf("2026-02-01"), 2000, 158.50, "somewhere2");
        Coupon coupon6 = new Coupon(6, 6, 3, "Cool Discount6", "Cool Discount6", Date.valueOf("2021-03-01"), Date.valueOf("2026-03-01"), 3000, 58.50, "somewhere3");

        //Insert to DB
        couponsDAO.addCoupon(coupon1);
        couponsDAO.addCoupon(coupon2);
        couponsDAO.addCoupon(coupon3);
        couponsDAO.addCoupon(coupon4);
        couponsDAO.addCoupon(coupon5);
        couponsDAO.addCoupon(coupon6);

        //update coupon 1
//        System.out.println(ArtUtils.UPDATE);
        coupon1.setTitle("Cool Discount1 updated!!!");
        couponsDAO.updateCoupon(coupon1);

        CustomerCouponDBDAO x = new CustomerCouponDBDAO();
        x.InsertCustomersCoupons(1,1);
        x.InsertCustomersCoupons(2,2);
        x.InsertCustomersCoupons(3,3);

//        //delete coupon
//        System.out.println(ArtUtils.DELETE);
//        CustomerCouponDBDAO.DeleteRowCustomersCoupons(2,2);
//        couponsDAO.deleteCoupon(coupon2.getId());
//        customerDAO.deleteCustomer(2);
//
//        System.out.println("********************************************************");
//        CustomerCouponDBDAO.DeleteRowCustomersCoupons(1,1);
//        couponsDAO.deleteCoupon(coupon1.getId());
//
//        CustomerCouponDBDAO.DeleteRowCustomersCoupons(2,2);
//        customerDAO.deleteCustomer(2);
//        System.out.println("customer 2 was deleted");

        List<Coupon> cops = couponsDAO.getAllCoupons();
        cops.forEach(System.out::println);

        System.out.println(couponsDAO.getOneCoupon(1).toString());

        System.out.println("END");
    }
}
