package com.jb.db;

import com.jb.beans.Company;
import com.jb.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CompaniesDBDAO implements CompaniesDAO{

    private final String QUERY_INSERT_COMPANY = "INSERT INTO `bhp-g2-coup-sys-p2`.`companies` " +
            "(`id`, `name`, `email`, `password`) VALUES (?, ?, ?, ?);";

    private final String QUERY_UPDATE_COMPANY = "INSERT INTO `bhp-g2-coup-sys-p2`.`companies` " +
            "(`id`, `name`, `email`, `password`) VALUES (?, ?, ?, ?);";

    private final String QUERY_SELECT_ONE = "SELECT * FROM `bhp-g2-coup-sys-p2`.`companies` " +
            "WHERE `email` = ? AND `password` = ? ;";

    private final String QUERY_SELECT_ONE_BY_ID = "SELECT * FROM `bhp-g2-coup-sys-p2`.`companies` " +
            "WHERE `id` = ? ;";

    private final String QUERY_SELECT_ALL = "INSERT INTO `bhp-g2-coup-sys-p2`.`companies` " +
            "(`id`, `name`, `email`, `password`) VALUES (?, ?, ?, ?);";

    private final String QUERY_DELETE_COMPANY = "INSERT INTO `bhp-g2-coup-sys-p2`.`companies` " +
            "(`id`, `name`, `email`, `password`) VALUES (?, ?, ?, ?);";

    @Override
    public boolean isCompanyExists(String email, String password) {

        return false;
    }

    @Override
    public void addCompany(Company company) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, company.getId());
        map.put(2, company.getName());
        map.put(3, company.getEmail());
        map.put(4, company.getPassword());
        DBUtils.runQuery(QUERY_INSERT_COMPANY,map);
    }

    @Override
    public void updateCompany(Company company) {

    }

    @Override
    public void deleteCompany(Company company) {

    }

    @Override
    public List<Company> getAllCompanies() throws SQLException, InterruptedException {
//        QUERY_SELECT_ALL
        List<Company> companies = new ArrayList<>();
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
                Company company = new Company(id,name,email,password,null);
                companies.add(company);
            }
        }catch (Exception e ) {
            System.out.println(e.getMessage());
        }finally {
            ConnectionPool.getInstance().getConnection();
        }
        return companies;
    }


    @Override
    public Company getOneCompany(int id) {
        return null;
    }
}
