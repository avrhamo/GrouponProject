package com.jb.doa;
import com.jb.utils.DBUtils;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomerCouponDBDAO {

    private static final String INSERT_CUSTOMERS_COUPONS = "INSERT INTO `bhp-g2-coup-sys-p2`.`customers_coupons` (`customer_id`, `coupon_id`) VALUES ((SELECT `id` FROM `bhp-g2-coup-sys-p2`.`customers`WHERE `id` = ?), (SELECT `id` FROM `bhp-g2-coup-sys-p2`.`coupons` WHERE `id` = ?));";
    private static final String DELETE_CUSTOMERS_COUPONS = "DELETE FROM `bhp-g2-coup-sys-p2`.`customers_coupons` WHERE (`customer_id` = ?) and (`coupon_id` = ?);";

    public static void InsertCustomersCoupons(int customerId, int couponId) throws SQLException {

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, customerId);
        map.put(2, couponId);
        DBUtils.runQuery(INSERT_CUSTOMERS_COUPONS, map);

    }

    public static void DeleteRowCustomersCoupons(int customerId, int couponId) throws SQLException {

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, customerId);
        map.put(2, couponId);
        DBUtils.runQuery(DELETE_CUSTOMERS_COUPONS, map);

    }

}
