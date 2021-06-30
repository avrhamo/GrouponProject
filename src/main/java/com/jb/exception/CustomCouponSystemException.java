package com.jb.exception;

public class CustomCouponSystemException extends Exception{

    public CustomCouponSystemException(ExceptionsMap map) {
        super(map.getMessage());
    }
}
