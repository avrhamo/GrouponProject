package com.jb.playground;

import com.jb.beans.Customer;
import com.jb.dao.CustomersDAO;
import com.jb.db.DatabaseManager;
import com.jb.dbdao.CustomerDBDAO;
import com.jb.utils.ArtUtils;

import java.sql.SQLException;

public class TestCustomer {

    public static void main(String[] args) throws SQLException, InterruptedException {

        TestCustomersDB();

    }

    public static void TestCustomersDB() throws SQLException, InterruptedException {
        System.out.println("START");
        //Drop and Create DB
        DatabaseManager.DropAndCreate();

        //Access to company table
        CustomersDAO customerDAO = new CustomerDBDAO();

        // Insert companies
        System.out.println(ArtUtils.INSERT);
        Customer c1 = new Customer("Dani", "Din", "Dani@Din.mail.com", "1234");
        Customer c2 = new Customer("David", "Fogel", "nikeEmail2@nike.com", "12345678");
        Customer c3 = new Customer("Beni", "Davidov", "nikeEmail3@nike.com", "3nike1234");

        customerDAO.addCustomer(c1);
        customerDAO.addCustomer(c2);
        customerDAO.addCustomer(c3);

        System.out.println("is Dani@Din.mail.com,1234  exist? ");
        System.out.println(customerDAO.isCustomerExists(c1.getEmail(), c1.getPassword()));

        //Get all customers
        System.out.println(ArtUtils.GET_ALL);
        customerDAO.getAllCustomers().forEach(System.out::println);

        //Update customer
        System.out.println(ArtUtils.UPDATE);
        c1.setLastName("newFirstName");
        customerDAO.updateCustomer(c1);
        customerDAO.getOneCustomer(1);

        //delete customer
//        System.out.println(ArtUtils.DELETE);
//        System.out.println("customer 1 was deleted");
//        customerDAO.deleteCustomer(1);
//        System.out.println("is Dani@Din.mail.com,1234  exist? ");
//        System.out.println(customerDAO.iscCustomerExists(c1.getEmail(), c1.getPassword()));

        //get one by ID
        System.out.println(ArtUtils.GET_SINGLE);
        System.out.println(customerDAO.getOneCustomer(2).toString());
        System.out.println(customerDAO.getOneCustomer(3).toString());
    }
}
