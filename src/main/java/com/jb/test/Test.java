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

import java.sql.Date;
import java.sql.SQLException;

import static com.jb.beans.Category.VACATION;

public class Test {

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private final static int testCase = 1;

    public static void main(String[] args) throws SQLException, CustomCouponSystemException, InterruptedException {
        //Create DB
        TestCreateDB.runTest();
        //Thread expireCouponRemover = new Thread(new CouponExpirationDailyJob());
        //expireCouponRemover.start();
        TestAdminClient();
        TestCustomerClient();
        TestCompanyClient();
        //System.out.println(expireCouponRemover.getState().toString());
    }

    public static void TestAdminClient () throws SQLException, CustomCouponSystemException, InterruptedException {

        //login
        AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin", ClientType.ADMIN);

        printTestTitle("Testing admin facade getAllCustomers()", 1);
        adminFacade.getAllCustomers().forEach(System.out::println);

        printTestTitle("Testing admin facade addCustomer(customer) - Customer name Shimi", 1);
        Customer customer  = new Customer("Shimi", "Shalom", "shimi@gmail.com", "1234");
        adminFacade.addCustomer(customer);

        printTestTitle("Testing admin facade getAllCustomers()", 1);
        adminFacade.getAllCustomers().forEach(System.out::println);

        printTestTitle("Testing exception - admin facade addCustomer(customer) - Customer name Shimi", 1);
        adminFacade.addCustomer(customer);

        printTestTitle("Testing Admin facade getAllCompanies()", 1);
        adminFacade.getAllCompanies().forEach(System.out::println);

        printTestTitle("Testing Admin facade addCompany(TestCompany)", 1);
        Company TestCompany = new Company(7,"tetsCompany", "testCompany@Email.com", "TEST", null);
        adminFacade.addCompany(TestCompany);

        printTestTitle("testing Admin facade getAllCompanies()", 1);
        adminFacade.getAllCompanies().forEach(System.out::println);

        printTestTitle("Testing exception - Admin facade addCompany(TestCompany)", 1);
        adminFacade.addCompany(TestCompany);

        printTestTitle("Testing Admin facade updateCompany(TestCompany)", 1);
        TestCompany.setEmail("newTestEmail1@newTestCompanyMail.com");
        adminFacade.updateCompany(TestCompany);

        printTestTitle("Testing Admin facade getOneCompany(7)", 1);
        System.out.println(adminFacade.getOneCompany(TestCompany.getId()).toString());

        printTestTitle("Testing Admin facade updateCompany(TestCompany) Trying to update company_id", 1);
        TestCompany.setId(8);
        adminFacade.updateCompany(TestCompany);

        printTestTitle("Testing Admin facade deleteCompany(6)", 1);
        adminFacade.deleteCompany(6);

    }

    private static void TestCustomerClient() throws SQLException, CustomCouponSystemException, InterruptedException {
        CouponsDAO accessToCouponTable = new CouponsDBDAO();
        CustomerCouponDBDAO customerCouponDBDAO = new CustomerCouponDBDAO();
        CustomerFacade customerFacade;

        printTestTitle("Testing Exception - Customer facade login withe wrong details", 2);
        customerFacade = (CustomerFacade) LoginManager.getInstance().login("1Dani@gmail.com", "11111",ClientType.CUSTOMER);

        printTestTitle("Testing Customer login with correct details", 1);
        customerFacade = (CustomerFacade) LoginManager.getInstance().login("Dani@gmail.com", "1111",ClientType.CUSTOMER);

        printTestTitle("Testing Customer facade getCustomerCoupons()", 1);
        customerFacade.getCustomerCoupons().forEach(System.out::println);

        printTestTitle("Testing Customer facade getCustomerCoupons(maxPrice = 60)", 1);
        customerFacade.getCustomerCoupons(60).forEach(System.out::println);

        printTestTitle("Testing Customer facade getCustomerCoupons(Category.VACATION)", 1);
        customerFacade.getCustomerCoupons(VACATION).forEach(System.out::println);

        printTestTitle("Testing Customer Facade Exception - trying to buy expired coupon", 1);
        Coupon coupon9 = new Coupon(9, 5, 3, "Cool coupon - 9", "Cool Discount5", Date.valueOf("2021-02-01"), Date.valueOf("2021-07-01"), 2000, 158.50, "image9");
        customerFacade.purchaseCoupon(coupon9);

        Coupon coupon5;
        printTestTitle("Testing Customer Facade - buying coupon 5", 1);

        printTestTitle("Coupon record before buying a coupon ", 1);
        coupon5 = accessToCouponTable.getOneCoupon(5);
        printTestTitle(coupon5.toString(),1);

        customerFacade.purchaseCoupon(coupon5);

        printTestTitle("Coupon record after buying a coupon ", 1);
        coupon5 = accessToCouponTable.getOneCoupon(5);
        printTestTitle(coupon5.toString(),1);

        printTestTitle("Testing customer facade get details ", 1);
        System.out.println((customerFacade.getCustomerDetails().toString()));

    }

    private static void TestCompanyClient() throws SQLException, CustomCouponSystemException, InterruptedException {

        CouponsDAO accessToCouponTable = new CouponsDBDAO();
        CustomerCouponDBDAO customerCouponDBDAO = new CustomerCouponDBDAO();

        printTestTitle("Testing Exception - Company facade login withe wrong details", 2);
        CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().login("1Dani@gmail.com", "11111",ClientType.COMPANY);

        printTestTitle("Testing Company login with correct details", 1);
        System.out.println(companyFacade.login("company1@Email.com", "1111"));

        Coupon coupon1 = new Coupon(1, 1, 1, "Cool coupon - 1", "Cool Discount1", Date.valueOf("2021-01-01"), Date.valueOf("2023-01-01"), 1000, 58.50, "image1");
        printTestTitle("Testing Company facade Exception - trying to add existing coupon", 2);
        companyFacade.addCoupon(coupon1);
        Coupon coupon3 = new Coupon( 1, 3, "Cool coupon - 3", "Cool Discount3", Date.valueOf("2021-03-01"), Date.valueOf("2026-03-01"), 3000, 538.50, "image3");
        printTestTitle("Testing Company facade - trying to add valid coupon", 1);
        printTestTitle("company coupons before adding coupon", 1);
        companyFacade.getCompanyCoupons().forEach(System.out::println);
        companyFacade.addCoupon(coupon3);
        printTestTitle("company coupons after adding coupon", 1);
        companyFacade.getCompanyCoupons().forEach(System.out::println);

        printTestTitle("Testing company get all coupons max price of 100", 1);
        companyFacade.getCompanyCoupons(100).forEach(System.out::println);

        printTestTitle("Test company facade get all coupons by category [3]", 1);
        companyFacade.getCompanyCoupons(VACATION).forEach(System.out::println);

        printTestTitle("Test company facade get company details", 1);
        System.out.println(companyFacade.getCompanyDetails().toString());

    }
    private static void printTestTitle(String testTitle, int testType) {
        if(testType == testCase){
            System.out.println(ANSI_BLUE + testTitle + ANSI_RESET);
        }else {
            System.out.println(ANSI_YELLOW + testTitle + ANSI_RESET);
        }
    }

}