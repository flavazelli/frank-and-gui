package com.example.francescovalela.trkr.ui.addExpense;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.francescovalela.trkr.R;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by francescovalela on 2017-04-04.
 */

public class AddExpenseFragment extends Fragment implements AddExpenseContract.View {

    private String name;
    private double cost, locationLong, locationLat;
    private long date;
    private int expenseId, methodOfPaymentId, categoryId;
    private AddExpenseActivity mAddExpenseActivity;
    private AddExpenseContract.Presenter mAddExpensePresenter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets date to current date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        long currentDate = c.getTimeInMillis();

        returnDate(currentDate);

        //Fragment Manager
        FragmentManager fragmentManager = getChildFragmentManager();

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();


        //FAB
        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab_submit_expense);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_addexpense, container, false);
    }

    @Override
    public void showMissingFields() {

    }

    @Override
    public void showLocationMap() {

    }

    public String getName() {

        EditText expenseNameET = (EditText) getView().findViewById(R.id.expense_name_edit_text);

        String expenseName = String.valueOf(expenseNameET.getText());

        return expenseName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {

        EditText expenseCostET = (EditText) getView().findViewById(R.id.expense_cost_edit_text);

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

    public void showPlacePicker(View v) {
        final String FRAGTAG = "PlacePickerSample";
    }

        //Used by AddExpenseLocationFragment
    public void returnLocation(String name, String address, double latitude, double longitude, String placeId) {

        setLocationLat(latitude);
        setLocationLong(longitude);

        TextView locationTV = (TextView) getView().findViewById(R.id.expense_location_text_view);
        locationTV.setText(name + " at " + address);
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void showDatePickerDialog(View v) {
        mAddExpenseActivity = new AddExpenseActivity();

        DialogFragment newFragment = new AddExpenseDateFragment();
        newFragment.show(mAddExpenseActivity.getSupportFragmentManager(), "datePicker");
    }

    //Takes passed value, sets the value to date and then displays it in textView
    //Used by AddExpenseDateFragment
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void returnDate(long date) {

        setDate(date);

        Date formattedDate = new Date(date);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String selectedDate = sdf.format(formattedDate.getTime());

        TextView dateTV = (TextView) getView().findViewById(R.id.expense_date_text_view);
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

    //Checks if name is of valid format
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

    //Checks if cost is of valid format
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

    public void showLocationPickerFragment(View view) {

    }

    @Override
    public void setPresenter(AddExpenseContract.Presenter presenter) {

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


    public void submit() {
        //todo add in if missing fields...

        if (!validateExpenseFields()) {
            Snackbar errorMessage = Snackbar.make(getView().findViewById(android.R.id.content), "Invalid fields", Snackbar.LENGTH_SHORT)
                    .setActionTextColor(Color.RED);

            errorMessage.show();

            resetExpenseFields();
        } else {
            //todo debug this
            mAddExpensePresenter.addExpense(getExpenseId(), getDate(), getName(),
                    getCost(), getLocationLat(), getLocationLong(),
                    getMethodOfPaymentId(), getCategoryId());
        }
    }

}
