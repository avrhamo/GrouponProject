package com.jb.doa;

import com.jb.beans.Company;
import com.jb.beans.Customer;
import com.jb.dao.CustomersDAO;
import com.jb.db.ConnectionPool;
import com.jb.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDBDAO implements CustomersDAO {

    private final String QUERY_INSERT_CUSTOMER = "INSERT INTO `bhp-g2-coup-sys-p2`.`customers` " +
            "(`first_name`, `last_name`, `email`, `password`) VALUES ( ?, ?, ?, ?) ; ";


    private final String QUERY_UPDATE_CUSTOMER = "UPDATE `bhp-g2-coup-sys-p2`.`customers` " +
            "SET `first_name` = ?, `last_name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?);";

    private final String QUERY_SELECT_ONE = "SELECT COUNT(*) FROM `bhp-g2-coup-sys-p2`.`customers` " +
            "WHERE `email` = ? AND `password` = ? ;" ;

    private final String QUERY_SELECT_ID_BY_MAIL_AND_PASS = "SELECT `id` FROM `bhp-g2-coup-sys-p2`.`customers` " +
            "WHERE `email` = ? AND `password` = ? ;" ;

    private final String QUERY_SELECT_ONE_BY_EMAIL = "SELECT COUNT(*) FROM `bhp-g2-coup-sys-p2`.`customers` WHERE `email` = ? ;" ;

    private final String QUERY_SELECT_ONE_BY_ID = "SELECT * FROM `bhp-g2-coup-sys-p2`.`customers` " +
            "WHERE `id` = ? ;";

    private final String QUERY_SELECT_ALL = "SELECT * FROM `bhp-g2-coup-sys-p2`.`customers` ;";

    private final String QUERY_DELETE_CUSTOMER = "DELETE FROM `bhp-g2-coup-sys-p2`.`customers` " +
            "WHERE `id` = ? ;";

    @Override
    public boolean isCustomerExists(String email, String password) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1,email);
        map.put(2,password);
        ResultSet resultSet = DBUtils.runQueryWithResults(QUERY_SELECT_ONE, map);
        resultSet.next();
        return (resultSet.getInt(1)) == 1;
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, customer.getFirstName());
        map.put(2, customer.getLastName());
        map.put(3, customer.getEmail());
        map.put(4, customer.getPassword());
        DBUtils.runQuery(QUERY_INSERT_CUSTOMER,map);
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, customer.getFirstName());
        map.put(2, customer.getLastName());
        map.put(3, customer.getEmail());
        map.put(4, customer.getPassword());
        map.put(5, customer.getId());
        DBUtils.runQuery(QUERY_UPDATE_CUSTOMER, map);
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, customerId);
        DBUtils.runQuery(QUERY_DELETE_CUSTOMER, map);
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException, InterruptedException {
        List<Customer> customers = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_ALL);
            ResultSet resultSet  = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(2);
                String email = resultSet.getString(3);
                String password = resultSet.getString(4);
                Customer customer = new Customer(id, firstName, lastName, email, password);
                customers.add(customer);
            }
        }catch (Exception e ) {
            System.out.println(e.getMessage());
        }finally {
            ConnectionPool.getInstance().getConnection();
        }
        return customers;
    }

    @Override
    public Customer getOneCustomer(int id) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1,id);
        ResultSet resultSet = DBUtils.runQueryWithResults(QUERY_SELECT_ONE_BY_ID,map);
        resultSet.next();
        return new Customer(resultSet.getInt(1),resultSet.getString(2),
                resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
    }

    @Override
    public boolean isEmailExist(String email) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1,email);
        ResultSet resultSet = DBUtils.runQueryWithResults(QUERY_SELECT_ONE_BY_EMAIL, map);
        resultSet.next();
        return (resultSet.getInt(1)) == 1;
    }

    @Override
    public int getIdCustomerByEmail(String email, String password) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1,email);
        map.put(2,password);
        ResultSet resultSet = DBUtils.runQueryWithResults(QUERY_SELECT_ID_BY_MAIL_AND_PASS, map);
        resultSet.next();
        return resultSet.getInt(1);
    }

}
