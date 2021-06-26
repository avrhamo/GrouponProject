package com.jb.doa;
import com.jb.dao.CustomersCouponDAO;
import com.jb.utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomerCouponDBDAO implements CustomersCouponDAO {

    private static final String INSERT_CUSTOMERS_COUPONS = "INSERT INTO `bhp-g2-coup-sys-p2`.`customers_coupons` (`customer_id`, `coupon_id`) VALUES ((SELECT `id` FROM `bhp-g2-coup-sys-p2`.`customers`WHERE `id` = ?), (SELECT `id` FROM `bhp-g2-coup-sys-p2`.`coupons` WHERE `id` = ?));";
    private static final String DELETE_CUSTOMERS_COUPONS = "DELETE FROM `bhp-g2-coup-sys-p2`.`customers_coupons` WHERE (`customer_id` = ?) and (`coupon_id` = ?);";
    private static final String SELECT_ONE_CUSTOMERS_COUPONS = "SELECT COUNT(*) FROM `bhp-g2-coup-sys-p2`.`customers_coupons` WHERE (`customer_id` = ?) and (`coupon_id` = ?);";

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
}
