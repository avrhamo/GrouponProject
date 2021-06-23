package com.jb.dao;

import com.jb.beans.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomersDAO {

    public boolean iscCustomerExists(String email, String password) throws SQLException;

    public void addCustomer(Customer customer) throws SQLException;

    public void updateCustomer(Customer customer) throws SQLException;

    public void deleteCustomer(int customerId) throws SQLException;

    public List<Customer> getAllCustomers() throws SQLException, InterruptedException;

    public Customer getOneCustomer(int id) throws SQLException;
}
