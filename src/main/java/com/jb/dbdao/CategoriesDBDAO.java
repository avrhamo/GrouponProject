package com.jb.dbdao;

import com.jb.beans.Category;
import com.jb.utils.DBUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CategoriesDBDAO {

    private static final String QUERY_INSERT_CATEGORY = "INSERT INTO `bhp-g2-coup-sys-p2`.`categories` (`id`, `name`) VALUES (?, ?) ; ";

    public static void addCategories() throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        Category[] allCategories = Category.values();
        for (Category c : allCategories) {
            map.put(1, c.value);
            map.put(2, c.toString());
            DBUtils.runQuery(QUERY_INSERT_CATEGORY, map);
        }
    }

}
