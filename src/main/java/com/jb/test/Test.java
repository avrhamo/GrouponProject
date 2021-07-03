package com.jb.test;

import com.jb.beans.Customer;
import com.jb.clients.Clients;
import com.jb.exception.CustomCouponSystemException;
import com.jb.facade.AdminFacade;
import com.jb.facade.ClientFacade;

import java.sql.SQLException;

public class Test {

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

        adminFacade.getAllCompanies().forEach(System.out::println);

        Customer customer  = new Customer("Shimi", "Shalom", "shimi@gmail.com", "1234");
        adminFacade.addCustomer(customer);

        adminFacade.getAllCustomers();

    }
}
