package com.jb.doa;

import com.jb.beans.Company;
import com.jb.dao.CompaniesDAO;
import com.jb.db.ConnectionPool;
import com.jb.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CompaniesDBDAO implements CompaniesDAO {

    private final String QUERY_INSERT_COMPANY = "INSERT INTO `bhp-g2-coup-sys-p2`.`companies` " +
            "(`id`, `name`, `email`, `password`) VALUES (?, ?, ?, ?);";

    private final String QUERY_UPDATE_COMPANY = "UPDATE `bhp-g2-coup-sys-p2`.`companies` SET `name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?);";

    private final String QUERY_SELECT_ONE = "SELECT COUNT(*) FROM `bhp-g2-coup-sys-p2`.`companies` WHERE `email` = ? AND `password` = ? ;";

    private final String QUERY_SELECT_ONE_BY_ID = "SELECT * FROM `bhp-g2-coup-sys-p2`.`companies` " +
            "WHERE `id` = ? ;";

    private final String QUERY_SELECT_ALL = "SELECT * FROM `bhp-g2-coup-sys-p2`.`companies` ;";

    private final String QUERY_DELETE_COMPANY = "DELETE FROM `bhp-g2-coup-sys-p2`.`companies` " +
            "WHERE `id` = ? ;";

    @Override
    public boolean isCompanyExists(String email, String password) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1,email);
        map.put(2,password);
        ResultSet resultSet = DBUtils.runQueryWithResults(QUERY_SELECT_ONE, map);
        resultSet.next();
        return (resultSet.getInt(1)) == 1;
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
    public void updateCompany(Company company) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, company.getName());
        map.put(2, company.getEmail());
        map.put(3, company.getPassword());
        map.put(4, company.getId());
        DBUtils.runQuery(QUERY_UPDATE_COMPANY, map);
    }

    @Override
    public void deleteCompany(int companyId) throws SQLException {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, companyId);
        DBUtils.runQuery(QUERY_DELETE_COMPANY, map);
    }

    @Override
    public List<Company> getAllCompanies() throws SQLException, InterruptedException {
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
    public Company getOneCompany(int id) throws SQLException {

        Map<Integer, Object> map = new HashMap<>();
        map.put(1,id);
        ResultSet resultSet = DBUtils.runQueryWithResults(QUERY_SELECT_ONE_BY_ID,map);
        resultSet.next();
        return new Company(resultSet.getInt(1),resultSet.getString(2),
               resultSet.getString(3),resultSet.getString(4),null);

    }
}
