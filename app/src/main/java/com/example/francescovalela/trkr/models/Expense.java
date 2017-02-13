package com.example.francescovalela.trkr.models;

import java.util.Date;

/**
 * Created by francescovalela on 2017-02-12.
 * Allows user to log an expense
 */

public class Expense {

    private String name;
    private double cost, locationLong, locationLat;
    private long date;
    private int methodOfPaymentId, categoryId;

    //Constructor for inputting all attributes except date which defaults to the current time
    public Expense(double cost, double locationLong, double locationLat, int methodOfPaymentId, int categoryId, String name) {
        this.cost = cost;
        this.locationLong = locationLong;
        this.locationLat = locationLat;
        this.methodOfPaymentId = methodOfPaymentId;
        this.categoryId = categoryId;
        this.name = name;
        Date currentDate = new Date();
        date = currentDate.getTime(); //defaults to the current time in milli
    }

    //Constructor for inputting all attributes including date
    public Expense(String name, double cost, double locationLong, double locationLat, int methodOfPaymentId, int categoryId, long date) {
        this.name = name;
        this.cost = cost;
        this.locationLong = locationLong;
        this.locationLat = locationLat;
        this.methodOfPaymentId = methodOfPaymentId;
        this.categoryId = categoryId;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    // returns array of both latitude and longitude
    public double[] getLocation() {

        double[] location = {locationLat, locationLong};

        return location;
    }

    public void setLocationLong(double locationLong) {
        this.locationLong = locationLong;
    }

    public void setLocationLat(double locationLat) {
        this.locationLat = locationLat;
    }

    public Date getDate() {

        Date currentDate = new Date(date);

        return currentDate;
    }

    public void setDate(long date) {
        this.date = date;
    }

    //TODO: getMethodOfPayment - search internal memory to get methodOfPayment from methodOfPaymentID
    /*
    public int getMethodOfPayment() {

        MethodOfPayment

        return methodOfPaymentId;
    }
    */

    public void setMethodOfPaymentId(int methodOfPaymentId) {
        this.methodOfPaymentId = methodOfPaymentId;
    }

    //TODO: getCategory - search internal memory to get category from categoryID
    /*
    public int getCategory() {
        return categoryId;
    }
    */

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
