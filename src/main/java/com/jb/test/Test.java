package com.jb.test;

import com.jb.beans.Company;
import com.jb.beans.Customer;
import com.jb.clients.Clients;
import com.jb.exception.CustomCouponSystemException;
import com.jb.facade.AdminFacade;
import com.jb.facade.ClientFacade;

import java.awt.*;
import java.sql.SQLException;

public class Test {

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) throws SQLException, CustomCouponSystemException, InterruptedException {


        //Create DB
        TestCreateDB.runTest();
        TestAdminClient();

    }

    public static void TestAdminClient () throws SQLException, CustomCouponSystemException, InterruptedException {

        //login
        ClientFacade clientFacade = Clients.getInstance().login("admin@admin2.com", "admin",1);
        //AdminFacade adminFacade = (AdminFacade) clientFacade;
        AdminFacade adminFacade = (AdminFacade) Clients.getInstance().login("admin@admin.com", "admin",1);

        printTestTitle("Testing admin facade getAllCustomers()");
        adminFacade.getAllCustomers().forEach(System.out::println);

        printTestTitle("Testing admin facade addCustomer(customer) - Customer name Shimi");
        Customer customer  = new Customer("Shimi", "Shalom", "shimi@gmail.com", "1234");
        adminFacade.addCustomer(customer);

        printTestTitle("Testing admin facade getAllCustomers()");
        adminFacade.getAllCustomers().forEach(System.out::println);

        printTestTitle("Testing exception - admin facade addCustomer(customer) - Customer name Shimi");
        adminFacade.addCustomer(customer);

        printTestTitle("Testing Admin facade getAllCompanies()");
        adminFacade.getAllCompanies().forEach(System.out::println);

        printTestTitle("Testing Admin facade addCompany(TestCompany)");
        Company TestCompany = new Company(7,"tetsCompany", "testCompany@Email.com", "TEST", null);
        adminFacade.addCompany(TestCompany);

        printTestTitle("testing Admin facade getAllCompanies()");
        adminFacade.getAllCompanies().forEach(System.out::println);

        printTestTitle("Testing exception - Admin facade addCompany(TestCompany)");
        adminFacade.addCompany(TestCompany);

        printTestTitle("Testing Admin facade updateCompany(TestCompany)");
        TestCompany.setEmail("newTestEmail@newTestCompanyMail.com");
        adminFacade.updateCompany(TestCompany);

        printTestTitle("Testing Admin facade getOneCompany(7)");
        System.out.println(adminFacade.getOneCompany(TestCompany.getId()).toString());

        printTestTitle("Testing Admin facade updateCompany(TestCompany) Trying to update company_id");
        TestCompany.setId(8);
        adminFacade.updateCompany(TestCompany);

        printTestTitle("Testing Admin facade deleteCompany(6)");
        adminFacade.deleteCompany(6);

    }

    private static void printTestTitle(String testTitle) {
        System.out.println(ANSI_BLUE + testTitle + ANSI_RESET);
    }
}
