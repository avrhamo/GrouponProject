package com.jb.facade;

import com.jb.beans.Category;
import com.jb.beans.Company;
import com.jb.beans.Coupon;
import com.jb.dao.CompaniesDAO;

import java.util.List;

public class CompanyFacade extends ClientFacade{

    private int companyId;

    public CompanyFacade(CompaniesDAO companiesDAO) {
        super(companiesDAO);
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public boolean login(String email, String password) {
        return false;
    }

    public void addCoupon(Coupon coupon) {

    }

    public void updateCoupon(Coupon coupon) {

    }

    public void deleteCoupon(int couponId) {

    }

    public List<Coupon> getCompanyCoupons() {
        return null;
    }

    public List<Coupon> getCompanyCoupons(Category category) {
        return null;
    }

    public List<Coupon> getCompanyCoupons(double maxPrice) {
        return null;
    }

    public Company getCompanyDetails() {
        return null;
    }

}
