package com.jb.beans;

import java.sql.Date;

public class Coupon {

    private int id;
    private int company_id;
    private String title;
    private String description;
    private Date start_date;
    private Date end_date;
    private int amount;
    private double price;
    private String image;

    public Coupon(int id, int company_id, String title, String description, Date start_date, Date end_date, int amount, double price, String image) {
        this(company_id, title, description, start_date, end_date, amount, price, image);
        this.id = id;
    }
    public Coupon(int company_id, String title, String description, Date start_date, Date end_date, int amount, double price, String image) {
        this.id = id;
        this.company_id = company_id;
        this.title = title;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", company_id=" + company_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", amount=" + amount +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}