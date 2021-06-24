package com.jb.beans;

public enum Category {
    FOOD,
    PC,
    VACATION,
    SPORTS,
    BODY_CARE;

    public final int value = 1 + ordinal();

}
