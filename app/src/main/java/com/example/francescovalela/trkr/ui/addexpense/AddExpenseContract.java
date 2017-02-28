package com.example.francescovalela.trkr.ui.addExpense;

import com.example.francescovalela.trkr.BasePresenter;
import com.example.francescovalela.trkr.BaseView;

/**
 * Created by flavazelli on 2017-02-28.
 * Contract that sets up interaction between view and presenter.
 * For adding an expense log
 */

public interface AddExpenseContract {

    interface View extends BaseView<Presenter> {

        //for initial load
        void showExpenseFields();

        //for submit button result
        void showExpenseSuccess();

        //for submit button result
        void showMissingFields();

        //popup location map
        void showLocationMap();

        //popup calendar
        void showDateCalender();

    }

    interface Presenter extends BasePresenter {

        void addExpense();

        // checks if all fields match
        void validateExpenseFields();

        // resets all fields
        void resetExpenseFields();

        //gets location from showLocationMap
        void getLocation();

        //sets location from showLocationMap
        void setLocation(double locationLat, double locationLong);

        //gets date from showDateCalendar
        void getDate();

        //sets date from showDateCalendar
        void setDate(long date);

    }
}
