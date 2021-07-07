package com.jb.dbdao;
import com.jb.beans.Coupon;
import com.jb.beans.CustomerVsCoupon;
import com.jb.dao.CustomersCouponDAO;
import com.jb.db.ConnectionPool;
import com.jb.utils.DBUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerCouponDBDAO implements CustomersCouponDAO {

    private static final String INSERT_CUSTOMERS_COUPONS = "INSERT INTO `bhp-g2-coup-sys-p2`.`customers_coupons` (`customer_id`, `coupon_id`) VALUES ((SELECT `id` FROM `bhp-g2-coup-sys-p2`.`customers`WHERE `id` = ?), (SELECT `id` FROM `bhp-g2-coup-sys-p2`.`coupons` WHERE `id` = ?));";
    private static final String DELETE_CUSTOMERS_COUPONS = "DELETE FROM `bhp-g2-coup-sys-p2`.`customers_coupons` WHERE (`customer_id` = ?) and (`coupon_id` = ?);";
    private static final String DELETE_ALL_COUPONS_BY_COP_ID = "DELETE FROM `bhp-g2-coup-sys-p2`.customers_coupons where coupon_id = ?;";
    private static final String DELETE_ALL_COUPONS_BY_CUST_ID = "DELETE FROM `bhp-g2-coup-sys-p2`.`customers_coupons` WHERE (`customer_id` = ?) ;";
    private static final String SELECT_ONE_CUSTOMERS_COUPONS = "SELECT COUNT(*) FROM `bhp-g2-coup-sys-p2`.`customers_coupons` WHERE (`customer_id` = ?) and (`coupon_id` = ?);";
    private static final String SELECT_ALL_CUSTOMERS_COUPONS_BY_ID = "SELECT * FROM `bhp-g2-coup-sys-p2`.`customers_coupons` WHERE (`customer_id` = ?) and (`coupon_id` = ?);";

    @Override
    public void InsertCustomersCoupons(int customerId, int couponId) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, customerId);
        map.put(2, couponId);
        DBUtils.runQuery(INSERT_CUSTOMERS_COUPONS, map);
    }

    @Override
    public void DeleteRowCustomersCoupons(int customerId, int couponId) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, customerId);
        map.put(2, couponId);
        DBUtils.runQuery(DELETE_CUSTOMERS_COUPONS, map);
    }

    @Override
    public boolean isExistCustomersCoupons(int customerId, int couponId) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1,customerId);
        map.put(2,couponId);
        ResultSet resultSet = DBUtils.runQueryWithResults(SELECT_ONE_CUSTOMERS_COUPONS, map);
        resultSet.next();
        return (resultSet.getInt(1)) == 1;
    }

    @Override
    public void deleteByCouponId(int couponId) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, couponId);
        DBUtils.runQuery(DELETE_ALL_COUPONS_BY_COP_ID, map);
    }

    @Override
    public void DeleteCustomer(int couponId) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, couponId);
        DBUtils.runQuery(DELETE_ALL_COUPONS_BY_CUST_ID, map);
    }

    @Override
    public List<CustomerVsCoupon> getAllByCouponId(int couponId) throws SQLException, InterruptedException {

        List<CustomerVsCoupon> customerVsCoupons = new ArrayList<>();
        //Connection connection = null;
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, couponId);
        try {
            //connection = ConnectionPool.getInstance().getConnection();
            ResultSet resultSet = DBUtils.runQueryWithResults(SELECT_ALL_CUSTOMERS_COUPONS_BY_ID, map);
            while (resultSet.next()) {
                int resultCustomerId = resultSet.getInt(1);
                int resultCouponId = resultSet.getInt(2);
                CustomerVsCoupon customerVsCoupon = new CustomerVsCoupon(resultCustomerId, resultCouponId);
                customerVsCoupons.add(customerVsCoupon);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().getConnection();
        }
        return customerVsCoupons;
    }

}
