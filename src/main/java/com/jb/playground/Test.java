package com.jb.playground;

import com.jb.beans.Company;
import com.jb.db.CompaniesDAO;
import com.jb.db.CompaniesDBDAO;
import com.jb.db.DatabaseManager;
import com.jb.utils.ArtUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws SQLException, InterruptedException {

        System.out.println("START");

        DatabaseManager.DropAndCreate();
        Company c1 = new Company("NIKE1","nikeEmail1@nike.com","1nike1234",null);
        Company c2 = new Company("NIKE2","nikeEmail2@nike.com","2nike1234",null);
        Company c3 = new Company("NIKE3","nikeEmail3@nike.com","3nike1234",null);
        System.out.println(ArtUtils.INSERT);
        CompaniesDAO companyDAO = new CompaniesDBDAO();
        companyDAO.addCompany(c1);
        companyDAO.addCompany(c2);
        companyDAO.addCompany(c3);
        System.out.println(ArtUtils.GET_ALL);
        List<Company> companies = companyDAO.getAllCompanies();
        for (int i = 0; i < companies.size(); i++) {
            System.out.println(companies.get(i).toString());
        }
        System.out.println("END");

    }

}
