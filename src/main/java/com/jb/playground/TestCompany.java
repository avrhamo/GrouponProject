package com.jb.playground;

import com.jb.beans.Customer;
import com.jb.dao.customersDAO;
import com.jb.doa.customersDBDAO;
import com.jb.db.DatabaseManager;
import com.jb.utils.ArtUtils;

import java.sql.SQLException;
import java.util.List;

public class TestCustomer {

    public static void main(String[] args) throws SQLException, InterruptedException {

        System.out.println("START");
        //Drop and Create DB
        DatabaseManager.DropAndCreate();

        //Access to Customer table
        customersDAO CustomerDAO = new customersDBDAO();

        // Insert customers
        System.out.println(ArtUtils.INSERT);
        Customer c1 = new Customer("NIKE1","nikeEmail1@nike.com","1nike1234",null);
        Customer c2 = new Customer("NIKE2","nikeEmail2@nike.com","2nike1234",null);
        Customer c3 = new Customer("NIKE3","nikeEmail3@nike.com","3nike1234",null);

        CustomerDAO.addCustomer(c1);
        CustomerDAO.addCustomer(c2);
        CustomerDAO.addCustomer(c3);

        //Get all customers
        System.out.println(ArtUtils.GET_ALL);
        List<Customer> customers = CustomerDAO.getAllcustomers();
        customers.forEach(System.out::println);

        //Get one Customer by id
        System.out.println(ArtUtils.GET_SINGLE);
        System.out.println(CustomerDAO.getOneCustomer(1).toString());

        System.out.println(ArtUtils.GET_SINGLE);
        System.out.println(CustomerDAO.getOneCustomer(2).toString());

        //Select Customer by mail and password
        System.out.println("Is Customer "+ CustomerDAO.getOneCustomer(2).getName() + " exist?");
        Customer fromDB = CustomerDAO.getOneCustomer(1);
        String email = fromDB.getEmail();
        String password = fromDB.getPassword();
        System.out.println(email + password);
        System.out.println(CustomerDAO.isCustomerExists(email, password));

        //Delete Customer
        CustomerDAO.deleteCustomer(2);
        System.out.println("Customer NIKE2 was deleted");
        System.out.println(CustomerDAO.isCustomerExists("nikeEmail2@nike.com","2nike1234"));

        //Update Customer
        System.out.println(ArtUtils.UPDATE);
        System.out.println("changing password to `hello world` -- comapnyId = 1");
        c1 = CustomerDAO.getOneCustomer(1);
        c1.setPassword("hello world");
        CustomerDAO.updateCustomer(c1);
        System.out.println(CustomerDAO.getOneCustomer(1).toString());

        System.out.println("END");

    }

}
