package com.jb.dao;

import com.jb.beans.Company;
import java.sql.SQLException;
import java.util.List;

public interface CompaniesDAO {

    boolean isCompanyExists(String email, String password) throws SQLException;

    void addCompany(Company company) throws SQLException;

    void updateCompany(Company company) throws SQLException;

    void deleteCompany(int companyId) throws SQLException;

    List<Company> getAllCompanies() throws SQLException, InterruptedException;

    Company getOneCompany(int id) throws SQLException;

    boolean isCompanyNameEmailExist (String companyName, String companyEmail) throws SQLException;

    Company getOneCompany(String name, String email) throws SQLException;

    Company getOneCompanyByEmailAndPassword(String name, String email) throws SQLException;
}
