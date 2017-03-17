package com.example.francescovalela.trkr.ui.addExpense;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.francescovalela.trkr.R;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by flavazelli on 2017-02-28.
 */

public class AddExpenseActivity extends AppCompatActivity implements AddExpenseContract.View, AddExpenseDateFragment.TheListener{

    private String name;
    private double cost, locationLong, locationLat;
    private long date;
    private int expenseId, methodOfPaymentId, categoryId;

    private AddExpensePresenter mAddExpensePresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexpense);

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

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

        if (!isValidCost() || !isValidName()) return false;
        else return true;
    }

    @Override
    //todo start this
    public void resetExpenseFields() {

    }

    @Override
    public void setPresenter(AddExpenseContract.Presenter presenter) {

    }

    public void submit(){
        //todo add in if missing fields...

        if (!validateExpenseFields()) {
            Snackbar errorMessage = Snackbar.make(findViewById(android.R.id.content), "Invalid fields", Snackbar.LENGTH_SHORT)
                    .setActionTextColor(Color.RED);

            errorMessage.show();

            resetExpenseFields();
        }
        else {
            //todo debug this
            mAddExpensePresenter.addExpense(getExpenseId(), getDate(), getName(),
                    getCost(), getLocationLat(), getLocationLong(),
                    getMethodOfPaymentId(), getCategoryId());
        }
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

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new AddExpenseDateFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    //todo have date show/be set to current date right away
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void returnDate(long date) {

        setDate(date);

        Date formattedDate = new Date(date);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String selectedDate = sdf.format(formattedDate.getTime());

        TextView dateTV = (TextView) findViewById(R.id.expense_date_text_view);
        dateTV.setText(selectedDate);
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

    private boolean isValidName() {

        String mName = this.getName();

        //return false if field is empty
        if (mName.equals("") || mName.equals(null)) return false;

        String NAME_PATTERN = "^[a-zA-Z0-9_]+( [a-zA-Z0-9_]+)*$";

        Pattern pattern = Pattern.compile(NAME_PATTERN);

        Matcher matcher = pattern.matcher(mName);

        //return true if field matches restrictions
        return matcher.matches();
    }

    private boolean isValidCost() {

        String mCost = String.valueOf(this.getCost());

        //return false if field is empty
        if (mCost.equals("") || mCost.equals(null)) return false;

        String COST_PATTERN = "^\\d{1,7}\\.?\\d{1,4}$";

        Pattern pattern = Pattern.compile(COST_PATTERN);

        Matcher matcher = pattern.matcher(mCost);

        //return true if field matches restrictions
        return matcher.matches();
    }
}
