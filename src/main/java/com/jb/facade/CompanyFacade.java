package com.jb.facade;

import com.jb.beans.Category;
import com.jb.beans.Company;
import com.jb.beans.Coupon;

import java.util.List;

public class CompanyFacade {

    private int companyId;

    public CompanyFacade(int companyId) {
        this.companyId = companyId;
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
