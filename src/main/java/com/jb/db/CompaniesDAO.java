package com.jb.db;

import com.jb.beans.Company;

import java.sql.SQLException;

public interface CompaniesDAO {

    public boolean isCompanyExists(String email, String password);

    public void addCompany(Company company) throws SQLException;

    public void updateCompany(Company company);

    public void deleteCompany(Company company);

    public void getAllCompanies();

    public Company getOneCompany(int id);
}
