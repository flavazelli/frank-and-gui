package com.example.francescovalela.trkr.ui.addExpense;

import android.support.v4.app.*;
import android.support.v4.widget.*;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.francescovalela.trkr.R;
import com.example.francescovalela.trkr.logExpense.models.Category;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_OK;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by francescovalela on 2017-04-04.
 */

public class AddExpenseFragment extends Fragment implements AddExpenseContract.View, AdapterView.OnItemSelectedListener {

    private String name;
    private double cost, locationLong, locationLat;
    private long date;
    private int expenseId, methodOfPaymentId, categoryId;
    private AddExpenseActivity mAddExpenseActivity;
    private AddExpenseContract.Presenter mAddExpensePresenter;
    private Button showDate;
    private Button get_place;
    private TextView locationTV;
    int PLACE_PICKER_REQUEST = 1;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private View RootView;
    private Spinner spinner;
    private List<Category> categoryList;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Fragment Manager
        fragmentManager = getChildFragmentManager();

        fragmentTransaction = getChildFragmentManager().beginTransaction();

        mAddExpenseActivity = (AddExpenseActivity) getActivity();


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        RootView = inflater.inflate(R.layout.fragment_addexpense, container, false);

        //sets date to current date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        long currentDate = c.getTimeInMillis();

        returnDate(currentDate, RootView);



        //sets location objects
        locationTV = (TextView) RootView.findViewById(R.id.expense_location_text_view);
        get_place = (Button) RootView.findViewById(R.id.expense_location_button);
        get_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                Intent intent;
                try {
                    intent = builder.build(getActivity());
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });


        mAddExpensePresenter.loadCategoriesInSpinner();

        //FAB
        FloatingActionButton fab = (FloatingActionButton) RootView.findViewById(R.id.fab_submit_expense);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit(RootView);
            }
        });

        return RootView;
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

    public double getLocationLong() {
        return locationLong;
    }

    public void setLocationLong(double locationLong) {
        this.locationLong = locationLong;
    }

    public double getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(double locationLat) {
        this.locationLat = locationLat;
    }

    //Used for AddExpenseLocationFragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place selectedPlace = PlacePicker.getPlace(getActivity(), data);
                final String name = selectedPlace.getName().toString();
                final String address = selectedPlace.getAddress().toString();
                final String placeId = selectedPlace.getId();
                final double latitude = selectedPlace.getLatLng().latitude;
                final double longitude = selectedPlace.getLatLng().longitude;

                returnLocation(name, address, latitude, longitude, placeId);
            }
        }
    }

    //Used by AddExpenseLocationFragment
    public void returnLocation(String name, String address, double latitude, double longitude, String placeId) {

        setLocationLat(latitude);
        setLocationLong(longitude);

        locationTV.setText(name + " at " + address);
    }

    //Takes passed value, sets the value to date and then displays it in textView
    //Also in calling activity for when using the AddExpenseDateFragment
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void returnDate(long date, View RootView) {

        mAddExpenseActivity.setDate(date);

        Date formattedDate = new Date(date);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String selectedDate = sdf.format(formattedDate.getTime());

        @SuppressWarnings("ConstantConditions") TextView dateTV = (TextView) RootView.findViewById(R.id.expense_date_text_view);
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

    public void setMethodOfPaymentId(int methodOfPaymentId) { //TODO same spinner thing as categories but with this
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

        EditText expenseCostET = (EditText) getView().findViewById(R.id.expense_cost_edit_text);

        String mCost = String.valueOf(expenseCostET.getText());

        //return false if field is empty
        if (mCost.equals("") || mCost.equals(null)) return false;

        String COST_PATTERN = "^\\d{1,7}\\.?\\d{1,4}$";

        Pattern pattern = Pattern.compile(COST_PATTERN);

        Matcher matcher = pattern.matcher(mCost);

        //return true if field matches restrictions
        return matcher.matches();
    }

    @Override
    public void setPresenter(AddExpenseContract.Presenter presenter) {
        mAddExpensePresenter = checkNotNull(presenter);

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

    public void submit(View rootView) {

        if (!validateExpenseFields()) {
            Snackbar errorMessage = Snackbar.make(rootView, "Invalid fields: Please correct red underlined fields", Snackbar.LENGTH_LONG)
                    .setActionTextColor(Color.RED);

            errorMessage.show();

        } else {
            mAddExpensePresenter.addExpense(getExpenseId(), mAddExpenseActivity.getDate(), getName(),
                    getCost(), getLocationLat(), getLocationLong(),
                    getMethodOfPaymentId(), getCategoryId());

            Snackbar successMessage = Snackbar.make(rootView, "expense has been saved", Snackbar.LENGTH_LONG)
                    .setActionTextColor(Color.GREEN);

            successMessage.show();

        }
    }

    public void loadSpinnerData(List<Category> categories) {

        categoryList = new ArrayList<>(categories);

        List<String> categoriesToShow = new ArrayList<String>();

        for (Category category: categories) {
            categoriesToShow.add(category.getName());
        }

        //Populate spinner for category types
        spinner = (Spinner) RootView.findViewById(R.id.expense_category_spinner);

        spinner.setOnItemSelectedListener(this);

        //Columns from DB to put into spinner
        String[] fromColumns = {mAddExpensePresenter.getCategoryColumnName()};


        //Load categories
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categoriesToShow);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(categoryAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        for (Category category : categoryList) {
            if ( category.getName() == parent.getItemAtPosition(position) ) {
                setCategoryId(category.getId());
                break;
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        setCategoryId(1);
    }
}
