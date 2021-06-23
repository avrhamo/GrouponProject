package com.jb.doa;

import com.jb.beans.Customer;
import com.jb.dao.CustomersDAO;
import java.sql.SQLException;
import java.util.List;

public class CustomerDBDAO implements CustomersDAO {
    @Override
    public boolean iscCustomerExists(String email, String password) throws SQLException {
        return false;
    }

    @Override
    public void addCustomer(Customer company) throws SQLException {

    }

    @Override
    public void updateCustomer(Customer company) throws SQLException {

    }

    @Override
    public void deleteCustomer(int companyId) throws SQLException {

    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException, InterruptedException {
        return null;
    }

    @Override
    public Customer getOneCustomer(int id) throws SQLException {
        return null;
    }
}
