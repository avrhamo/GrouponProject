package com.jb.playground;

import com.jb.beans.Company;
import com.jb.db.CompaniesDAO;
import com.jb.db.CompaniesDBDAO;
import com.jb.db.DatabaseManager;

import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws SQLException {

        System.out.println("START");

        DatabaseManager.DropAndCreate();
        Company c1 = new Company("NIKE","nikeEmail@nike.com","nike1234",null);
        System.out.println("END");

    }

    public void add(Company c1) throws SQLException {

//        CompaniesDBDAO.addCompany(c1);
    }
}
