package com.jb.dao;

import com.jb.beans.Customer;

import java.sql.SQLException;
import java.util.List;

public interface customersDAO {

    public boolean isCustomerExists(String email, String password) throws SQLException;

    public void addCustomer(Customer Customer) throws SQLException;

    public void updateCustomer(Customer Customer) throws SQLException;

    public void deleteCustomer(int CustomerId) throws SQLException;

    public List<Customer> getAllcustomers() throws SQLException, InterruptedException;

    public Customer getOneCustomer(int id) throws SQLException;
}
