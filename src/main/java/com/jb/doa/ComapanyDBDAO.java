package com.jb.doa;

import com.jb.beans.Company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComapanyDBDAO implements CompaneisDAO {

    private static final String QUERY_IS_COMPANY_EXIST = "SELECT `id` FROM `bhp-g2-coup-sys-p2`.`companies`" +
            "WHERE (`id` = ?);";
    private static final String QUERY_INSERT_COMPANY = "INSERT INTO `bhp-g2-coup-sys-p2`.`companies` " +
            "(`id`, `name`, `email`, `password`) VALUES (?, ?, ?, ?);";
    private static final String QUERY_UPDATE_COMPANY = "UPDATE `bhp-g2-coup-sys-p2`.`companies` " +
            "SET `id` = ?, `name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?);";
    private static final String QUERY_DELETE_COMPANY = "DELETE FROM `bhp-g2-coup-sys-p2`.`companies` " +
            "WHERE (`id` = ?);";
    private static final String QUERY_GET_ALL_COMPANIES = "SELECT * FROM `bhp-g2-coup-sys-p2`.`companies`;";
    private static final String QUERY_GET_ONE_COMPANIES = "SELECT * FROM `bhp-g2-coup-sys-p2`.`companies`" +
            "WHERE (`id` = ?);";

    @Override
    public boolean isCompanyExist(String Email, String password) {
        Map<Integer, Object> map = new HashMap<>();

        return false;
    }

    @Override
    public void addCompany(Company company) {

    }

    @Override
    public void updateCompany(Company company) {

    }

    @Override
    public void deleteCompany(int Company) {

    }

    @Override
    public List<Company> getAllCompanies() {
        return null;
    }

    @Override
    public Company getOneCompany(int companyId) {
        return null;
    }
}
