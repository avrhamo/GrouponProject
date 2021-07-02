package com.jb.facade;
import com.jb.beans.Category;
import com.jb.beans.Company;
import com.jb.beans.Coupon;
import com.jb.dao.CompaniesDAO;
import com.jb.dao.CustomersCouponDAO;
import com.jb.doa.CustomerCouponDBDAO;
import com.jb.exception.CustomCouponSystemException;
import com.jb.exception.ExceptionsMap;

import java.sql.SQLException;
import java.util.List;

import static com.jb.exception.ExceptionsMap.*;

public class CompanyFacade extends ClientFacade{

    private int companyId;

    public CompanyFacade(int companyId) {
        this.companyId = companyId;
    }

    public CompanyFacade(CompaniesDAO companiesDAO) {
        super();
    }

    public CompanyFacade() {    }

    public boolean login(String email, String password) throws SQLException {
        return companiesDAO.isCompanyExists(email, password);
    }

    public void addCoupon(Coupon coupon) throws SQLException, CustomCouponSystemException {
        if (!couponsDAO.isCouponNameExistUnderCompany(coupon.getCompanyId(), coupon.getTitle())) {
            couponsDAO.addCoupon(coupon);
        }else {
            throw new CustomCouponSystemException(ERROR_CANNOT_ADD_SAME_COUPON_NAME);
        }
    }

    public int getCompanyId() throws SQLException  {
        return companyId;
    }

    public void setCompanyId(int companyId) throws SQLException  {
        this.companyId = companyId;
    }

    public void updateCoupon(Coupon coupon) throws SQLException, CustomCouponSystemException {
        Coupon DBCoupon = couponsDAO.getOneCoupon(coupon.getId());
        if(companyId == DBCoupon.getCompanyId() && coupon.getId() == DBCoupon.getId()) {
            couponsDAO.updateCoupon(coupon);
        }else throw new CustomCouponSystemException(ERROR_CANNOT_UPDATE_COUPON_CODE);
    }

    public void deleteCoupon(int couponId)  throws SQLException {
        CustomersCouponDAO customersCouponDAO = new CustomerCouponDBDAO();
        customersCouponDAO.DeleteByCouponId(couponId);
        couponsDAO.deleteCoupon(couponId);

    }

    public List<Coupon> getCompanyCoupons() throws SQLException, InterruptedException {
        return couponsDAO.getCompanyAllCoupons(companyId);
    }

    public List<Coupon> getCompanyCoupons(Category category) throws SQLException  {
        return null;
    }

    public List<Coupon> getAllCouponsByCategory(int categoryId) throws SQLException, InterruptedException {
        return couponsDAO.getAllCouponsByCategory(companyId, categoryId);
    }

    public List<Coupon> getCompanyCoupons(double maxPrice) throws SQLException, InterruptedException {
        return couponsDAO.getAllCouponsUnderMaxPrice(companyId, maxPrice);
    }

    public Company getCompanyDetails() throws SQLException  {
        return companiesDAO.getOneCompany(companyId);
    }

}
