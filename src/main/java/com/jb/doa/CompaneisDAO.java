package com.jb.doa;

import com.jb.beans.Company;

import java.util.List;

public interface CompaneisDAO {

    boolean isCompanyExist(String Email, String password);

    void addCompany(Company company);

    void updateCompany(Company company);

    void deleteCompany(int Company);

    List<Company> getAllCompanies();

    Company getOneCompany(int companyId);

}
