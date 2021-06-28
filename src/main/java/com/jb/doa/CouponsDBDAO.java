package com.jb.doa;

import com.jb.beans.Company;
import com.jb.beans.Coupon;
import com.jb.dao.CouponsDAO;
import com.jb.dao.CustomersCouponDAO;
import com.jb.db.ConnectionPool;
import com.jb.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponsDBDAO implements CouponsDAO {

    public static final String QUERY_INSERT_COUPON = "INSERT INTO `bhp-g2-coup-sys-p2`.`coupons` (`id`, `company_id`, `category_id`, `title`, `description`, `start_date`, `end_date`, `amount`, `price`, `image`) " +
            "VALUES (?, (SELECT `id` FROM `bhp-g2-coup-sys-p2`.`companies` WHERE id = ?), (SELECT `id` FROM `bhp-g2-coup-sys-p2`.`categories` WHERE id = ?), ?, ?, ?, ?, ?, ?, ?) ;";
    public static final String QUERY_UPDATE_COUPON_BY_ID = "UPDATE `bhp-g2-coup-sys-p2`.`coupons` SET `title` = ?, `description` = ?, `start_date` = ?, `end_date` = ?, `amount` = ?, `price` = ?, `image` = ? WHERE (`id` = ?); ";
    public static final String QUERY_DELETE_COUPON = "DELETE FROM `bhp-g2-coup-sys-p2`.`coupons` WHERE `id` = ? ;";
    public static final String QUERY_GET_ALL_COUPONS = "SELECT * FROM `bhp-g2-coup-sys-p2`.`coupons`";
    public static final String QUERY_GET_ALL_COUPONS_UNDER_PRICE = "SELECT * FROM `bhp-g2-coup-sys-p2`.`coupons` WHERE `company_id` = ? AND `price` <= ? ;";
    public static final String QUERY_GET_ALL_COUPONS_BY_CATEGORY = "SELECT * FROM `bhp-g2-coup-sys-p2`.`coupons` WHERE `company_id` = ? AND `category_id` = ?";
    public static final String QUERY_GET_ALL_COMPANY_COUPONS = "SELECT * FROM `bhp-g2-coup-sys-p2`.`coupons` WHERE `company_id` = ?";
    public static final String QUERY_GET_ONE_COUPON_BY_ID = "SELECT * FROM `bhp-g2-coup-sys-p2`.`coupons` WHERE `id` = ? ;";
    public static final String QUERY_UPDATE_PURCHASE = "UPDATE `bhp-g2-coup-sys-p2`.`coupons` SET (`amount` = `amount` - 1) WHERE (`id` = ?); ";
    public static final String QUERY_DELETE_PURCHASE = "UPDATE `bhp-g2-coup-sys-p2`.`coupons` SET (`amount` = `amount` + 1) WHERE (`id` = ?); ";
    public static final String QUERY_SELECT_COUPON_NAME_BY_COMPANY_ID = " SELECT COUNT(*) FROM `bhp-g2-coup-sys-p2`.`coupons` WHERE `company_id` = ? AND `title` = ? ; ";


    @Override
    public void addCoupon(Coupon coupon) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, coupon.getId());
        map.put(2, coupon.getCompanyId());
        map.put(3, coupon.getCategoryId());
        map.put(4, coupon.getTitle());
        map.put(5, coupon.getDescription());
        map.put(6, coupon.getStart_date());
        map.put(7, coupon.getEnd_date());
        map.put(8, coupon.getAmount());
        map.put(9, coupon.getPrice());
        map.put(10, coupon.getImage());
        DBUtils.runQuery(QUERY_INSERT_COUPON, map);
    }

    @Override
    public void updateCoupon(Coupon coupon) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, coupon.getTitle());
        map.put(2, coupon.getDescription());
        map.put(3, coupon.getStart_date());
        map.put(4, coupon.getEnd_date());
        map.put(5, coupon.getAmount());
        map.put(6, coupon.getPrice());
        map.put(7, coupon.getImage());
        map.put(8, coupon.getId());
        DBUtils.runQuery(QUERY_UPDATE_COUPON_BY_ID, map);
    }

    @Override
    public void deleteCoupon(int couponId) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, couponId);
        DBUtils.runQuery(QUERY_DELETE_COUPON, map);
    }

    @Override
    public List<Coupon> getAllCoupons() throws SQLException, InterruptedException {
        List<Coupon> coupons = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL_COUPONS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int companyId = resultSet.getInt(2);
                int categoryId = resultSet.getInt(3);
                String title = resultSet.getString(4);
                String description = resultSet.getString(5);
                Date start_date = resultSet.getDate(6);
                Date end_date = resultSet.getDate(7);
                int amount = resultSet.getInt(8);
                double price = resultSet.getDouble(9);
                String image = resultSet.getString(10);
                Coupon coupon = new Coupon(id, companyId, categoryId, title, description, start_date, end_date, amount, price, image);
                coupons.add(coupon);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().getConnection();
        }
        return coupons;
    }

    @Override
    public List<Coupon> getCompanyAllCoupons(int companyId) throws SQLException, InterruptedException {
        List<Coupon> coupons = new ArrayList<>();
        Connection connection = null;
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, companyId);
        try {
            //connection = ConnectionPool.getInstance().getConnection();
            ResultSet resultSet = DBUtils.runQueryWithResults(QUERY_GET_ALL_COMPANY_COUPONS, map);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                //int companyId = resultSet.getInt(2);
                int categoryId = resultSet.getInt(3);
                String title = resultSet.getString(4);
                String description = resultSet.getString(5);
                Date start_date = resultSet.getDate(6);
                Date end_date = resultSet.getDate(7);
                int amount = resultSet.getInt(8);
                double price = resultSet.getDouble(9);
                String image = resultSet.getString(10);
                Coupon coupon = new Coupon(id, companyId, categoryId, title, description, start_date, end_date, amount, price, image);
                coupons.add(coupon);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().getConnection();
        }
        return coupons;
    }

    @Override
    public Coupon getOneCoupon(int couponId) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, couponId);
        ResultSet resultSet = DBUtils.runQueryWithResults(QUERY_GET_ONE_COUPON_BY_ID, map);
        resultSet.next();
        int id = resultSet.getInt(1);
        int companyId = resultSet.getInt(2);
        int categoryId = resultSet.getInt(3);
        String title = resultSet.getString(4);
        String description = resultSet.getString(5);
        Date start_date = resultSet.getDate(6);
        Date end_date = resultSet.getDate(7);
        int amount = resultSet.getInt(8);
        double price = resultSet.getDouble(9);
        String image = resultSet.getString(10);
        return new Coupon(id, companyId, categoryId, title, description, start_date, end_date, amount, price, image);
    }

    @Override
    public void addCouponPurchase(int customerId, int couponId) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, couponId);
        DBUtils.runQuery(QUERY_UPDATE_PURCHASE, map);

        CustomersCouponDAO customersCouponsDBDAO = new CustomerCouponDBDAO();
        if (!customersCouponsDBDAO.isExistCustomersCoupons(customerId, couponId)) {
            customersCouponsDBDAO.InsertCustomersCoupons(customerId, couponId);
        }
    }

    @Override
    public void deleteCouponPurchase(int customerId, int couponId) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, couponId);
        DBUtils.runQuery(QUERY_DELETE_PURCHASE, map);

        CustomersCouponDAO customersCouponsDBDAO = new CustomerCouponDBDAO();
        if (customersCouponsDBDAO.isExistCustomersCoupons(customerId, couponId)) {
            customersCouponsDBDAO.DeleteRowCustomersCoupons(customerId, couponId);
        }
    }

    @Override
    public boolean isCouponNameExistUnderCompany(int companyId, String title) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, companyId);
        map.put(2, title);
        ResultSet resultSet = DBUtils.runQueryWithResults(QUERY_SELECT_COUPON_NAME_BY_COMPANY_ID, map);
        resultSet.next();
        return (resultSet.getInt(1)) == 1;
    }

    @Override
    public List<Coupon> getAllCouponsByCategory(int companyId, int categoryId) throws SQLException, InterruptedException {
        List<Coupon> coupons = new ArrayList<>();
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, companyId);
        map.put(2, categoryId);
        try {
            ResultSet resultSet = DBUtils.runQueryWithResults(QUERY_GET_ALL_COUPONS_BY_CATEGORY, map);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                companyId = resultSet.getInt(2);
                categoryId = resultSet.getInt(3);
                String title = resultSet.getString(4);
                String description = resultSet.getString(5);
                Date start_date = resultSet.getDate(6);
                Date end_date = resultSet.getDate(7);
                int amount = resultSet.getInt(8);
                double price = resultSet.getDouble(9);
                String image = resultSet.getString(10);
                Coupon coupon = new Coupon(id, companyId, categoryId, title, description, start_date, end_date, amount, price, image);
                coupons.add(coupon);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().getConnection();
        }
        return coupons;
    }

    @Override
    public List<Coupon> getAllCouponsUnderMaxPrice(int companyId, double maxPrice) throws SQLException, InterruptedException {
        List<Coupon> coupons = new ArrayList<>();
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, companyId);
        map.put(2, maxPrice);
        try {
            ResultSet resultSet = DBUtils.runQueryWithResults(QUERY_GET_ALL_COUPONS_UNDER_PRICE, map);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                companyId = resultSet.getInt(2);
                int categoryId = resultSet.getInt(3);
                String title = resultSet.getString(4);
                String description = resultSet.getString(5);
                Date start_date = resultSet.getDate(6);
                Date end_date = resultSet.getDate(7);
                int amount = resultSet.getInt(8);
                double price = resultSet.getDouble(9);
                String image = resultSet.getString(10);
                Coupon coupon = new Coupon(id, companyId, categoryId, title, description, start_date, end_date, amount, price, image);
                coupons.add(coupon);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().getConnection();
        }
        return coupons;
    }
}
