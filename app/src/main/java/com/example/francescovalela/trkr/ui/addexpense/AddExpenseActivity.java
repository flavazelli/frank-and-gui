package com.example.francescovalela.trkr.ui.addExpense;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.francescovalela.trkr.R;

/**
 * Created by flavazelli on 2017-02-28.
 */

public class AddExpenseActivity extends Activity implements AddExpenseContract.View {

    private String name;
    private double cost, locationLong, locationLat;
    private long date;
    private int expenseId, methodOfPaymentId, categoryId;

    private AddExpensePresenter mAddExpensePresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexpense);

        Intent activityThatCalled = getIntent();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_submit_expense);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }

    @Override
    public void showMissingFields() {

    }

    @Override
    public void showLocationMap() {

    }

    @Override
    public void showDateCalender() {

    }

    //if all fields are validated, return true
    @Override
    public boolean validateExpenseFields() {


        return true;
    }

    @Override
    public void resetExpenseFields() {

    }

    @Override
    public void setPresenter(AddExpenseContract.Presenter presenter) {

    }

    public void submit(){
        if (validateExpenseFields())
            mAddExpensePresenter.addExpense(getExpenseId(), getDate(), getName(),
                    getCost(), getLocationLat(), getLocationLong(),
                    getMethodOfPaymentId(), getCategoryId());

    }

    public String getName() {

        EditText expenseNameET = (EditText) findViewById(R.id.expense_name_edit_text);

        String expenseName = String.valueOf(expenseNameET.getText());

        return expenseName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {

        EditText expenseCostET = (EditText) findViewById(R.id.expense_cost_edit_text);

        double expenseCost = Double.parseDouble(String.valueOf(expenseCostET.getText()));

        return expenseCost;
    }

    public void setCost(double cost) { this.cost = cost; }

    // TODO change when location fragment is created
    public double getLocationLong() {
        return locationLong;
    }

    // TODO change when location fragment is created
    public void setLocationLong(double locationLong) {
        this.locationLong = locationLong;
    }

    // TODO change when location fragment is created
    public double getLocationLat() {
        return locationLat;
    }

    // TODO change when location fragment is created
    public void setLocationLat(double locationLat) {
        this.locationLat = locationLat;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getExpenseId() {

        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getMethodOfPaymentId() {
        return methodOfPaymentId;
    }

    public void setMethodOfPaymentId(int methodOfPaymentId) {
        this.methodOfPaymentId = methodOfPaymentId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
