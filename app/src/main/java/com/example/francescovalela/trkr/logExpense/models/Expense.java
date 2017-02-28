package com.example.francescovalela.trkr.logExpense.models;

import java.util.Date;

import static android.R.attr.id;

/**
 * Created by francescovalela on 2017-02-12.
 * Allows user to log an expense
 */

public class Expense {

    private String name;
    private double cost, locationLong, locationLat;
    private long date;

    private int expenseId, methodOfPaymentId, categoryId;

    //Constructor for inputting all attributes except date which defaults to the current time
    public Expense(int expenseId, String name, double cost, double locationLat, double locationLong, int methodOfPaymentId, int categoryId) {
        this.expenseId = expenseId;
        this.name = name;
        this.cost = cost;
        this.locationLat = locationLat;
        this.locationLong = locationLong;
        this.methodOfPaymentId = methodOfPaymentId;
        this.categoryId = categoryId;
        Date currentDate = new Date();
        date = currentDate.getTime(); //defaults to the current time in milli
    }

    //Constructor for inputting all attributes including date
    public Expense(int expenseId, long date, String name, double cost, double locationLat, double locationLong, int methodOfPaymentId, int categoryId) {
        this.expenseId = expenseId;
        this.date = date;
        this.name = name;
        this.cost = cost;
        this.locationLat = locationLat;
        this.locationLong = locationLong;
        this.methodOfPaymentId = methodOfPaymentId;
        this.categoryId = categoryId;
    }

    public Date getDate() {

        Date currentDate = new Date(date);

        return currentDate;
    }


    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public void setDate(long date) {
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

    public void setLocationLat(double locationLat) {
        this.locationLat = locationLat;
    }

    public void setLocationLong(double locationLong) { this.locationLong = locationLong; }

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
