package com.jb.exception;

public enum ExceptionsMap {

    //General error messages
    ERROR_LOGIN ("Incorrect login details please check your email and passwaord"),
    ERROR_CANNOT_UPDATE_COMPANY_CODE ("You cannot update company code"),

    //Admin error messages
    ERROR_SAME_NAME_COMPANY ("Another comapny with the same name already exist"),
    ERROR_SAME_EMAIL_COMPANY ("Another comapny with the same Email already exist"),
    ERROR_SAME_EMAIL_CUSTOMER ("Another customer with the same Email already exist"),
    ERROR_CANNOT_UPDATE_COMPANY_NAME ("You cannot update company name or code"),
    ERROR_CANNOT_UPDATE_CUSTOMER_CODE ("You cannot update customer name or code"),

    //Company error messages
    ERROR_CANNOT_ADD_SAME_COMPANY_NAME ("You cannot update company name"),
    ERROR_CANNOT_ADD_SAME_COUPON_NAME ("This company already have a coupon with the same name"),
    ERROR_CANNOT_UPDATE_COUPON_CODE ("You cannot update coupon code"),


    //Customer error messages
    ERROR_CANNOT_BUY_THE_SAME_COUPON ("You cannot buy the same coupon again"),
    ERROR_COUPON_SOLD_OUT_OR_EXPIRED ("Coupon sold out or expired"),
    ERROR_COUPON_EXPIERD ("Coupon expierd!");

    private String message;

    ExceptionsMap(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
