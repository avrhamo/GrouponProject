package com.jb.test;

import com.jb.beans.Company;
import com.jb.beans.Coupon;
import com.jb.beans.Customer;
import com.jb.dao.CompaniesDAO;
import com.jb.dao.CouponsDAO;
import com.jb.dao.CustomersDAO;
import com.jb.db.DatabaseManager;
import com.jb.doa.*;
import com.jb.utils.ArtUtils;

import java.sql.Date;
import java.sql.SQLException;

public class TestCreateDB {

//    public static void main(String[] args) throws SQLException {
//        runTest();
//    }

    public static void runTest() throws SQLException {
         //Create DB
        DatabaseManager.DropAndCreate();
        CategoriesDBDAO.addCategories();
        addCompaniesToDB();
        addCouponsToDB();
        addCustomersToDB();
        addCustomersVsCouponsToDB();
    }

    private static void addCustomersVsCouponsToDB() throws SQLException {

        CustomerCouponDBDAO customerCouponDBDAO = new CustomerCouponDBDAO();
        customerCouponDBDAO.InsertCustomersCoupons(1,10);
        customerCouponDBDAO.InsertCustomersCoupons(2,6);
        customerCouponDBDAO.InsertCustomersCoupons(3,4);
        customerCouponDBDAO.InsertCustomersCoupons(5,7);
        customerCouponDBDAO.InsertCustomersCoupons(9,6);
        customerCouponDBDAO.InsertCustomersCoupons(7,8);
        customerCouponDBDAO.InsertCustomersCoupons(8,3);
        customerCouponDBDAO.InsertCustomersCoupons(3,7);
        customerCouponDBDAO.InsertCustomersCoupons(4,9);
        customerCouponDBDAO.InsertCustomersCoupons(9,10);
        customerCouponDBDAO.InsertCustomersCoupons(7,3);
        customerCouponDBDAO.InsertCustomersCoupons(10,3);

    }


    private static void addCouponsToDB() throws SQLException {
        CouponsDAO couponsDAO = new CouponsDBDAO();

        //Creating coupons
        Coupon coupon1 = new Coupon(1, 1, 1, "Cool coupon - 1", "Cool Discount1", Date.valueOf("2021-01-01"), Date.valueOf("2023-01-01"), 1000, 58.50, "image1");
        Coupon coupon2 = new Coupon(2, 2, 2, "Cool coupon - 2", "Cool Discount2", Date.valueOf("2021-02-01"), Date.valueOf("2024-02-01"), 2000, 5448.50, "image2");
        Coupon coupon3 = new Coupon(3, 3, 3, "Cool coupon - 3", "Cool Discount3", Date.valueOf("2021-03-01"), Date.valueOf("2026-03-01"), 3000, 538.50, "image3");
        Coupon coupon4 = new Coupon(4, 4, 4, "Cool coupon - 4", "Cool Discount4", Date.valueOf("2021-01-01"), Date.valueOf("2025-04-01"), 0, 558.50, "image4");
        Coupon coupon5 = new Coupon(5, 5, 5, "Cool coupon - 5", "Cool Discount5", Date.valueOf("2021-02-01"), Date.valueOf("2023-02-01"), 2000, 158.50, "image5");
        Coupon coupon6 = new Coupon(6, 6, 6, "Cool coupon - 6", "Cool Discount6", Date.valueOf("2021-03-01"), Date.valueOf("2026-03-01"), 3000, 58.50, "image6");
        Coupon coupon7 = new Coupon(7, 3, 7, "Cool coupon - 7", "Cool Discount3", Date.valueOf("2021-03-01"), Date.valueOf("2028-03-01"), 3000, 538.50, "image7");
        Coupon coupon8 = new Coupon(8, 4, 9, "Cool coupon - 8", "Cool Discount4", Date.valueOf("2021-01-01"), Date.valueOf("2024-04-01"), 0, 558.50, "image8");
        Coupon coupon9 = new Coupon(9, 5, 3, "Cool coupon - 9", "Cool Discount5", Date.valueOf("2021-02-01"), Date.valueOf("2021-07-01"), 2000, 158.50, "image9");
        Coupon coupon10= new Coupon(10, 6, 3, "Cool coupon - 10", "Cool Discount6", Date.valueOf("2021-03-01"), Date.valueOf("2026-12-01"), 3000, 58.50, "image10");

        //Insert to DB
        couponsDAO.addCoupon(coupon1);
        couponsDAO.addCoupon(coupon2);
        couponsDAO.addCoupon(coupon3);
        couponsDAO.addCoupon(coupon4);
        couponsDAO.addCoupon(coupon5);
        couponsDAO.addCoupon(coupon6);
        couponsDAO.addCoupon(coupon7);
        couponsDAO.addCoupon(coupon8);
        couponsDAO.addCoupon(coupon9);
        couponsDAO.addCoupon(coupon10);

    }

    private static void addCompaniesToDB() throws SQLException {

        CompaniesDAO companiesDAO = new CompaniesDBDAO();

        Company company1 = new Company("company1", "company1@Email.com", "1111", null);
        Company company2 = new Company("company2", "company2@Email.com", "2222", null);
        Company company3 = new Company("company3", "company3@Email.com", "3333", null);
        Company company4 = new Company("company4", "company4@Email.com", "4444", null);
        Company company5 = new Company("company5", "company5@Email.com", "5555", null);
        Company company6 = new Company("company6", "company6@Email.com", "6666", null);

        companiesDAO.addCompany(company1);
        companiesDAO.addCompany(company2);
        companiesDAO.addCompany(company3);
        companiesDAO.addCompany(company4);
        companiesDAO.addCompany(company5);
        companiesDAO.addCompany(company6);
    }

    private static void addCustomersToDB() throws SQLException {

        CustomersDAO customerDAO = new CustomerDBDAO();

        Customer customer1  = new Customer("Dani", "Din", "Dani@gmail.com", "1111");
        Customer customer2  = new Customer("David", "Cohen", "David@gmail.com", "2222");
        Customer customer3  = new Customer("Beni", "Yahalom", "Beni@gmail.com", "3333");
        Customer customer4  = new Customer("Shlomi", "Levi", "Shlomi@gmail.com", "4444");
        Customer customer5  = new Customer("Haim", "Torgeman", "Haim@gmail.com", "5555");
        Customer customer6  = new Customer("Shlomo", "Even", "Shlomi@gmail.com", "6666");
        Customer customer7  = new Customer("Moshe", "Shalom", "Moshe@gmail.com", "7777");
        Customer customer8  = new Customer("Dina", "Herzel", "Dina@gmail.com", "8888");
        Customer customer9  = new Customer("Anat", "David", "Anat@gmail.com", "9999");
        Customer customer10 = new Customer("Sigal", "Segal", "Sigal@gmail.com", "1010");

        customerDAO.addCustomer(customer1);
        customerDAO.addCustomer(customer2);
        customerDAO.addCustomer(customer3);
        customerDAO.addCustomer(customer4);
        customerDAO.addCustomer(customer5);
        customerDAO.addCustomer(customer6);
        customerDAO.addCustomer(customer7);
        customerDAO.addCustomer(customer8);
        customerDAO.addCustomer(customer9);
        customerDAO.addCustomer(customer10);
    }


}
