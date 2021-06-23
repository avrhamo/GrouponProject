package com.jb.doa;

import com.jb.beans.Customer;
import com.jb.dao.CustomersDAO;
import java.sql.SQLException;
import java.util.List;

public class CustomerDBDAO implements CustomersDAO {

    private final String QUERY_INSERT_company = "INSERT INTO `bhp-g2-coup-sys-p2`.`customer` " +
            "(`id`, `first_name`, `email`, `password`) VALUES (?, ?, ?, ?);";

    private final String QUERY_UPDATE_company = "UPDATE `bhp-g2-coup-sys-p2`.`customer` SET `name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?);";

    private final String QUERY_SELECT_ONE = "SELECT COUNT(*) FROM `bhp-g2-coup-sys-p2`.`customer` WHERE `email` = ? AND `password` = ? ;";

    private final String QUERY_SELECT_ONE_BY_ID = "SELECT * FROM `bhp-g2-coup-sys-p2`.`customer` " +
            "WHERE `id` = ? ;";

    private final String QUERY_SELECT_ALL = "SELECT * FROM `bhp-g2-coup-sys-p2`.`customer` ;";

    private final String QUERY_DELETE_company = "DELETE FROM `bhp-g2-coup-sys-p2`.`customer` " +
            "WHERE `id` = ? ;";

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
