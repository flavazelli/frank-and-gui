package com.example.francescovalela.trkr.ui.addExpense;

import com.example.francescovalela.trkr.BasePresenter;
import com.example.francescovalela.trkr.BaseView;
import com.example.francescovalela.trkr.logExpense.models.Category;
import com.example.francescovalela.trkr.logExpense.models.MethodOfPayment;

import java.util.List;

import static android.R.attr.name;

/**
 * Created by flavazelli on 2017-02-28.
 * Contract that sets up interaction between view and presenter.
 * For adding an expense log
 */

public interface AddExpenseContract {

    interface View extends BaseView<Presenter> {

        // checks if all fields match
        boolean validateExpenseFields();

        // resets all fields
        void resetExpenseFields();

        void loadCategoriesSpinnerData(List<Category> categoriesToShow);

        void loadMethodOfPaymentSpinnerData(List<MethodOfPayment> methodOfPayments);

    }

    interface Presenter extends BasePresenter {

        void addExpense(int expenseId, long date, String name, double cost,
                        double locationLat, double locationLong, int methodOfPaymentId, int categoryId);

        void loadCategoriesInSpinner();

        void loadMethodOfPaymentInSpinner();

        String getCategoryColumnName();

        String getMethodOfPaymentColumnName();

    }
}
