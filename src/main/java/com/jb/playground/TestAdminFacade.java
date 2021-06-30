package com.jb.playground;

import com.jb.beans.Company;
import com.jb.beans.Customer;
import com.jb.db.DatabaseManager;
import com.jb.exception.CustomCouponSystemException;
import com.jb.facade.AdminFacade;

import java.sql.SQLException;
import java.util.List;

public class TestAdminFacade {

    public static void main(String[] args) throws SQLException, InterruptedException, CustomCouponSystemException {
        //Drop and Create DB
        DatabaseManager.DropAndCreate();
        //TestCompany.TestCompanyDB();
        TestCoupon.TestCouponsDB();
//        TestCustomer.TestCustomersDB();

        System.out.println("******** S T A R T *********");
        AdminFacade adminFacade = new AdminFacade();

        if (adminFacade.login("admin@admin.com", "admin")) {
            System.out.println("admin logged in!!!");
        }

        List<Company> cs = adminFacade.getAllCompanies();
        cs.forEach(System.out::println);
        Company company = new Company("NIKE3", "nikeEmail3@ni*ke.com", "3nike1234", null);
        adminFacade.addCompany(company);
        //adminFacade.addCompany(company);
        //adminFacade.deleteCompany(2);
        Customer customer = new Customer(1,"Dani", "Din", "Dani@Din.mail.com", "1234");

        adminFacade.addCustomer(customer);

        adminFacade.deleteCustomer(customer);

        System.out.println("----- E N D ---------------");
    }
}
