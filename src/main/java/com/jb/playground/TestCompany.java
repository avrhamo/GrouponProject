package com.jb.playground;

import com.jb.beans.Company;
import com.jb.dao.CompaniesDAO;
import com.jb.doa.CompaniesDBDAO;
import com.jb.db.DatabaseManager;
import com.jb.utils.ArtUtils;

import java.sql.SQLException;
import java.util.List;

public class TestCompany {

    public static void main(String[] args) throws SQLException, InterruptedException {

        System.out.println("START");
        //Drop and Create DB
        DatabaseManager.DropAndCreate();

        //Access to company table
        CompaniesDAO companyDAO = new CompaniesDBDAO();

        // Insert companies
        System.out.println(ArtUtils.INSERT);
        Company c1 = new Company("NIKE1","nikeEmail1@nike.com","1nike1234",null);
        Company c2 = new Company("NIKE2","nikeEmail2@nike.com","2nike1234",null);
        Company c3 = new Company("NIKE3","nikeEmail3@nike.com","3nike1234",null);

        companyDAO.addCompany(c1);
        companyDAO.addCompany(c2);
        companyDAO.addCompany(c3);

        //Get all companies
        System.out.println(ArtUtils.GET_ALL);
        List<Company> companies = companyDAO.getAllCompanies();
        companies.forEach(System.out::println);

        //Get one company by id
        System.out.println(ArtUtils.GET_SINGLE);
        System.out.println(companyDAO.getOneCompany(1).toString());

        System.out.println(ArtUtils.GET_SINGLE);
        System.out.println(companyDAO.getOneCompany(2).toString());

        //Select company by mail and password
        System.out.println("Is company "+ companyDAO.getOneCompany(2).getName() + " exist?");
        Company fromDB = companyDAO.getOneCompany(1);
        String email = fromDB.getEmail();
        String password = fromDB.getPassword();
        System.out.println(email + password);
        System.out.println(companyDAO.isCompanyExists(email, password));

        //Delete company
        companyDAO.deleteCompany(2);
        System.out.println("Company NIKE2 was deleted");
        System.out.println(companyDAO.isCompanyExists("nikeEmail2@nike.com","2nike1234"));

        //Update company
        System.out.println(ArtUtils.UPDATE);
        System.out.println("changing password to `hello world` -- comapnyId = 1");
        c1 = companyDAO.getOneCompany(1);
        c1.setPassword("hello world");
        companyDAO.updateCompany(c1);
        System.out.println(companyDAO.getOneCompany(1).toString());

        System.out.println("END");

    }

}
