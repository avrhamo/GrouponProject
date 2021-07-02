package com.jb.test;

import com.jb.clients.Clients;
import com.jb.exception.CustomCouponSystemException;
import com.jb.facade.AdminFacade;
import com.jb.facade.ClientFacade;

import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws SQLException, CustomCouponSystemException, InterruptedException {
        TestCreateDB.runTest();
        ClientFacade clientFacade = Clients.getInstance().login("admin@admin.com", "admin",1);
        AdminFacade adminFacade = (AdminFacade) clientFacade;
        adminFacade.getAllCompanies().forEach(System.out::println);


    }
}
