package com.jb.beans;

public enum Category {
    FOOD,//1
    PC,//2
    VACATION,//3
    FESTIVAL,//4
    SPORTS,//5
    TRANSPORTATION,//6
    LIVE_SHOWS,//7
    BODY_CARE,//8
    HOUSE_HOLDING,//9
    GADGETS;//10

    public final int value = 1 + ordinal();

}
