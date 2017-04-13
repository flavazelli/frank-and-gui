package com.example.francescovalela.trkr.ui.addExpense;

import com.example.francescovalela.trkr.BasePresenter;
import com.example.francescovalela.trkr.BaseView;

import static android.R.attr.name;

/**
 * Created by flavazelli on 2017-02-28.
 * Contract that sets up interaction between view and presenter.
 * For adding an expense log
 */

public interface AddExpenseContract {

    interface View extends BaseView<Presenter> {

        //for submit button result
        void showMissingFields();

        //popup location map //todo will open up fragment that will then send back location object/list
        void showLocationMap();

        // checks if all fields match
        boolean validateExpenseFields();

        // resets all fields
        void resetExpenseFields();

    }

    interface Presenter extends BasePresenter {

        void addExpense(int expenseId, long date, String name, double cost,
                        double locationLat, double locationLong, int methodOfPaymentId, int categoryId);

    }
}
