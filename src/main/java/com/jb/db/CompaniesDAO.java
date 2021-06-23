package com.jb.db;

import com.jb.beans.Company;

import java.sql.SQLException;
import java.util.List;

public interface CompaniesDAO {

    public boolean isCompanyExists(String email, String password) throws SQLException;

    public void addCompany(Company company) throws SQLException;

    public void updateCompany(Company company) throws SQLException;

    public void deleteCompany(int companyId) throws SQLException;

    public List<Company> getAllCompanies() throws SQLException, InterruptedException;

    public Company getOneCompany(int id) throws SQLException;
}
