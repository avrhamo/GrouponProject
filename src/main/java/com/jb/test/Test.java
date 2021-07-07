package com.jb.test;

import com.jb.beans.Category;
import com.jb.beans.Company;
import com.jb.beans.Coupon;
import com.jb.beans.Customer;
import com.jb.clients.ClientType;
import com.jb.clients.LoginManager;
import com.jb.dao.CouponsDAO;
import com.jb.dbdao.CouponsDBDAO;
import com.jb.dbdao.CustomerCouponDBDAO;
import com.jb.exception.CustomCouponSystemException;
import com.jb.facade.AdminFacade;
import com.jb.facade.CompanyFacade;
import com.jb.facade.CustomerFacade;
import com.jb.job.CouponExpirationDailyJob;
import com.jb.utils.ArtUtils;

import java.sql.Date;
import java.sql.SQLException;

import static com.jb.beans.Category.VACATION;

public class Test {

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private final static int testCase = 1;

    public static void main(String[] args) throws SQLException, CustomCouponSystemException, InterruptedException {
        TestCreateDB.runTest();
        Thread t = new Thread(new CouponExpirationDailyJob());
        t.start();
        TestAdminClient();
        TestCustomerClient();
        TestCompanyClient();
        CouponExpirationDailyJob.quit();
    }

    public static void TestAdminClient() throws SQLException, CustomCouponSystemException, InterruptedException {

        System.out.println("\n\n\n");
        printTestTitle(ArtUtils.TESTING_ADMIN_FACADE, 1);

        //login
        AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin", ClientType.ADMIN);

        printTestTitle("Testing admin facade getAllCustomers()", 1);
        adminFacade.getAllCustomers().forEach(System.out::println);
        System.out.println("\n\n");

        printTestTitle("Testing admin facade addCustomer(customer) - Customer name Shimi", 1);
        Customer customer = new Customer("Shimi", "Shalom", "shimi@gmail.com", "1234");
        adminFacade.addCustomer(customer);
        printTestTitle("Testing admin facade getAllCustomers()", 1);
        adminFacade.getAllCustomers().forEach(System.out::println);
        System.out.println("\n\n");

        printTestTitle("Testing exception - admin facade addCustomer(customer) - Customer name Shimi", 2);
        adminFacade.addCustomer(customer);
        System.out.println("\n\n");

        printTestTitle("Testing Admin facade getAllCompanies()", 1);
        adminFacade.getAllCompanies().forEach(System.out::println);
        System.out.println("\n\n");

        printTestTitle("Testing Admin facade addCompany(TestCompany)", 1);
        Company TestCompany = new Company(7, "testCompany", "testCompany@Email.com", "TEST", null);
        adminFacade.addCompany(TestCompany);
        System.out.println("\n\n");

        printTestTitle("testing Admin facade getAllCompanies()", 1);
        adminFacade.getAllCompanies().forEach(System.out::println);
        System.out.println("\n\n");

        printTestTitle("Testing exception - Admin facade addCompany(TestCompany) again", 2);
        adminFacade.addCompany(TestCompany);
        System.out.println("\n\n");

        printTestTitle("Testing exception -  Admin facade updateCompany(TestCompany) - trying to add the same company again with with different mail ", 2);
        TestCompany.setEmail("newTestEmail1@newTestCompanyMail.com");
        adminFacade.updateCompany(TestCompany);
        System.out.println("\n\n");

        printTestTitle("Testing Admin facade getting one company details -- getOneCompany(7)", 1);
        System.out.println(adminFacade.getOneCompany(TestCompany.getId()).toString());
        System.out.println("\n\n");

        printTestTitle("Testing exception - Trying to update company_id from 7 to 8", 2);
        TestCompany.setId(8);
        adminFacade.updateCompany(TestCompany);
        System.out.println("\n\n");

        printTestTitle("Testing Admin facade deleteCompany(6)", 1);
        adminFacade.deleteCompany(6);
        printTestTitle("showing all companies via function getAllCompanies() and company-6 is not exists", 1);
        adminFacade.getAllCompanies().forEach(System.out::println);

    }

    private static void TestCustomerClient() throws SQLException, CustomCouponSystemException, InterruptedException {

        printTestTitle(ArtUtils.TESTING_CUSTOMER_FACADE, 1);

        CouponsDAO accessToCouponTable = new CouponsDBDAO();
        CustomerCouponDBDAO customerCouponDBDAO = new CustomerCouponDBDAO();
        CustomerFacade customerFacade;

        printTestTitle("Testing Exception - Customer facade login withe wrong details", 2);
        customerFacade = (CustomerFacade) LoginManager.getInstance().login("1Dani@gmail.com", "11111", ClientType.CUSTOMER);
        System.out.println("\n\n");

        printTestTitle("Testing Customer login with correct details", 1);
        customerFacade = (CustomerFacade) LoginManager.getInstance().login("Dani@gmail.com", "1111", ClientType.CUSTOMER);
        System.out.println("\n\n");

        printTestTitle("Testing Customer facade - getting all customer coupons via function - getCustomerCoupons()", 1);
        customerFacade.getCustomerCoupons().forEach(System.out::println);
        System.out.println("\n\n");

        printTestTitle("Testing Customer facade getting all customer coupons under 60$ via function - getCustomerCoupons(maxPrice = 60)", 1);
        customerFacade.getCustomerCoupons(60).forEach(System.out::println);
        System.out.println("\n\n");

        printTestTitle("Testing Customer facade getting all customer coupons under 600$ via function - getCustomerCoupons(maxPrice = 60)", 1);
        customerFacade.getCustomerCoupons(600).forEach(System.out::println);
        System.out.println("\n\n");

        printTestTitle("Testing Customer facade getting all customers coupons from category vacation -> getCustomerCoupons(Category.VACATION)", 1);
        customerFacade.getCustomerCoupons(VACATION).forEach(System.out::println);
        System.out.println("\n\n");

        printTestTitle("Testing Customer Facade Exception - trying to buy expired coupon", 2);
        Coupon coupon9 = new Coupon(9, 5, 3, "Cool coupon - 9", "Cool Discount5", Date.valueOf("2021-02-01"), Date.valueOf("2021-07-01"), 2000, 158.50, "image9");
        customerFacade.purchaseCoupon(coupon9);
        System.out.println("\n\n");

        Coupon coupon5;
        printTestTitle("Testing Customer Facade - buying coupon 5", 1);
        printTestTitle("Coupon record before buying a coupon: ", 1);
        coupon5 = accessToCouponTable.getOneCoupon(5);
        System.out.println(coupon5.toString());

        customerFacade.purchaseCoupon(coupon5);

        printTestTitle("Coupon record after buying a coupon: ", 1);
        coupon5 = accessToCouponTable.getOneCoupon(5);
        printTestTitle(coupon5.toString(), 1);
        System.out.println("\n\n");

        printTestTitle("Testing customer facade get details ", 1);
        System.out.println((customerFacade.getCustomerDetails().toString()));
        System.out.println("\n\n");

    }

    private static void TestCompanyClient() throws SQLException, CustomCouponSystemException, InterruptedException {

        System.out.println("\n\n\n");
        printTestTitle(ArtUtils.TESTING_COMPANY_FACADE, 1);

        printTestTitle("Testing Exception - Company facade login withe wrong details", 2);
        CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().login("1Dani@gmail.com", "11111", ClientType.COMPANY);
        System.out.println("\n\n");

        printTestTitle("Testing Company login with correct details", 1);
        companyFacade.login("company1@Email.com", "1111");
        System.out.println("\n\n");

        Coupon coupon1 = new Coupon(1, 1, 1, "Cool coupon - 1", "Cool Discount1", Date.valueOf("2021-01-01"), Date.valueOf("2023-01-01"), 1000, 58.50, "image1");

        printTestTitle("Testing Company facade Exception - trying to add a coupon with already exist coupon name under the same company ", 2);
        companyFacade.addCoupon(coupon1);
        System.out.println("\n\n");

        Coupon coupon3 = new Coupon(1, 3, "Cool coupon - 3", "Cool Discount3", Date.valueOf("2021-03-01"), Date.valueOf("2026-03-01"), 3000, 538.50, "image3");

        printTestTitle("Testing Company facade - trying to add valid coupon to companyId=1", 1);
        printTestTitle("company - 1 coupons before adding coupon", 1);
        companyFacade.getCompanyCoupons().forEach(System.out::println);
        companyFacade.addCoupon(coupon3);
        printTestTitle("company - 1 coupons after adding coupon", 1);
        companyFacade.getCompanyCoupons().forEach(System.out::println);
        System.out.println("\n\n");

        printTestTitle("Testing company get all coupons max price of 100", 1);
        companyFacade.getCompanyCoupons(100).forEach(System.out::println);
        System.out.println("\n\n");

        printTestTitle("Test company facade get all coupons by category [3]", 1);
        companyFacade.getCompanyCoupons(VACATION).forEach(System.out::println);
        System.out.println("\n\n");

        printTestTitle("Test company facade get company details", 1);
        System.out.println(companyFacade.getCompanyDetails().toString());

    }

    private static void printTestTitle(String testTitle, int testType) {
        if (testType == testCase) {
            System.out.println(ANSI_BLUE + testTitle + ANSI_RESET);
        } else {
            System.out.println(ANSI_YELLOW + testTitle + ANSI_RESET);
        }
    }

}