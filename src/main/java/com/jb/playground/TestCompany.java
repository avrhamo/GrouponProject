package com.jb.playground;

import com.jb.beans.Company;
import com.jb.dao.CompaniesDAO;
import com.jb.dbdao.CompaniesDBDAO;
import com.jb.db.DatabaseManager;
import com.jb.utils.ArtUtils;

import java.sql.SQLException;
import java.util.List;

public class TestCompany {

    public static void main(String[] args) throws SQLException, InterruptedException {

        TestCompanyDB();

    }

    public static void TestCompanyDB() throws SQLException, InterruptedException {

        System.out.println("START");
        //Drop and Create DB
        DatabaseManager.DropAndCreate();

        //Access to company table
        CompaniesDAO CompanyDAO = new CompaniesDBDAO();

        // Insert companies
//        System.out.println(ArtUtils.INSERT);
        Company c1 = new Company("NIKE1", "nikeEmail1@nike.com", "1nike1234", null);
        Company c2 = new Company("NIKE2", "nikeEmail2@nike.com", "2nike1234", null);
        Company c3 = new Company("NIKE3", "nikeEmail3@nike.com", "3nike1234", null);

        CompanyDAO.addCompany(c1);
        CompanyDAO.addCompany(c2);
        CompanyDAO.addCompany(c3);

        //Get all companies
//        System.out.println(ArtUtils.GET_ALL);
        List<Company> companies = CompanyDAO.getAllCompanies();
        companies.forEach(System.out::println);

        //Get one company by id
//        System.out.println(ArtUtils.GET_SINGLE);
        System.out.println(CompanyDAO.getOneCompany(1).toString());

//        System.out.println(ArtUtils.GET_SINGLE);
        System.out.println(CompanyDAO.getOneCompany(2).toString());

        //Select company by mail and password
        System.out.println("Is company " + CompanyDAO.getOneCompany(2).getName() + " exist?");
        Company fromDB = CompanyDAO.getOneCompany(1);
        String email = fromDB.getEmail();
        String password = fromDB.getPassword();
        System.out.println(email + password);
        System.out.println(CompanyDAO.isCompanyExists(email, password));

        //Delete company
//        CompanyDAO.deleteCompany(2);
//        System.out.println("company NIKE2 was deleted");
//        System.out.println(CompanyDAO.isCompanyExists("nikeEmail2@nike.com","2nike1234"));

        //Update company
//        System.out.println(ArtUtils.UPDATE);
        System.out.println("changing password to `hello world` -- companyId = 1");
        c1 = CompanyDAO.getOneCompany(1);
        c1.setPassword("hello world");
        CompanyDAO.updateCompany(c1);
        System.out.println(CompanyDAO.getOneCompany(1).toString());

        System.out.println("END");

    }

}