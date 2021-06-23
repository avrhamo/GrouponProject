package com.jb.doa;

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

    private final String QUERY_INSERT_Customer = "INSERT INTO `bhp-g2-coup-sys-p2`.`customers` " +
            "(`id`, `name`, `email`, `password`) VALUES (?, ?, ?, ?);";

    private final String QUERY_UPDATE_Customer = "UPDATE `bhp-g2-coup-sys-p2`.`customers` SET `name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?);";

    private final String QUERY_SELECT_ONE = "SELECT COUNT(*) FROM `bhp-g2-coup-sys-p2`.`customers` WHERE `email` = ? AND `password` = ? ;";

    private final String QUERY_SELECT_ONE_BY_ID = "SELECT * FROM `bhp-g2-coup-sys-p2`.`customers` " +
            "WHERE `id` = ? ;";

    private final String QUERY_SELECT_ALL = "SELECT * FROM `bhp-g2-coup-sys-p2`.`customers` ;";

    private final String QUERY_DELETE_Customer = "DELETE FROM `bhp-g2-coup-sys-p2`.`customers` " +
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
    public void addCustomer(Customer Customer) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, Customer.getId());
        map.put(2, Customer.getName());
        map.put(3, Customer.getEmail());
        map.put(4, Customer.getPassword());
        DBUtils.runQuery(QUERY_INSERT_Customer,map);
    }

    @Override
    public void updateCustomer(Customer Customer) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, Customer.getName());
        map.put(2, Customer.getEmail());
        map.put(3, Customer.getPassword());
        map.put(4, Customer.getId());
        DBUtils.runQuery(QUERY_UPDATE_Customer, map);
    }

    @Override
    public void deleteCustomer(int CustomerId) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, CustomerId);
        DBUtils.runQuery(QUERY_DELETE_Customer, map);
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
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                String password = resultSet.getString(4);
                Customer Customer = new Customer(id,name,email,password,null);
                customers.add(Customer);
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
                resultSet.getString(3),resultSet.getString(4),null);

    }

}
