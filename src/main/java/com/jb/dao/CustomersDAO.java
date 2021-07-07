package com.jb.dao;

import com.jb.beans.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomersDAO {

    boolean isCustomerExists(String email, String password) throws SQLException;

    void addCustomer(Customer customer) throws SQLException;

    void updateCustomer(Customer customer) throws SQLException;

    void deleteCustomer(int customerId) throws SQLException;

    List<Customer> getAllCustomers() throws SQLException, InterruptedException;

    Customer getOneCustomer(int id) throws SQLException;

    boolean isEmailExist(String email) throws SQLException;

    int getIdCustomerByEmail(String email, String password)throws SQLException;
}
