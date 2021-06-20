package com.jb.db;

import com.jb.beans.Company;
import com.jb.utils.DBUtils;
import com.mysql.cj.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompaniesDBDAO implements CompaniesDAO{

    private final String QUERY_INSERT = "INSERT INTO `bhp-g2-coup-sys-p2`.`companies` (`id`, `name`, `email`, `password`) VALUES (?, ?, ?, ?);";

    @Override
    public boolean isCompanyExists(String email, String password) {
        return false;
    }

    @Override
    public void addCompany(Company company) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(QUERY_INSERT);
            statement.setString(2, company.getName());
            statement.setString(3, company.getEmail());
            statement.setString(4, company.getPassword());
            statement.executeUpdate();
        }catch (Exception e) {

        }finally {

        }
    }

    @Override
    public void updateCompany(Company company) {

    }

    @Override
    public void deleteCompany(Company company) {

    }

    @Override
    public void getAllCompanies() {

    }

    @Override
    public Company getOneCompany(int id) {
        return null;
    }
}
