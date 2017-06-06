package com.example.francescovalela.trkr.ui.analytics;

import com.example.francescovalela.trkr.BasePresenter;
import com.example.francescovalela.trkr.BaseView;
import com.example.francescovalela.trkr.logExpense.models.Category;
import com.example.francescovalela.trkr.logExpense.models.Expense;
import com.example.francescovalela.trkr.logExpense.models.MethodOfPayment;

import java.util.List;

/**
 * Created by francescovalela on 2017-02-28.
 */

public interface AnalyticsContract {

    interface View extends BaseView<Presenter> {

        void getCategoriesData(List<Category> categoriesToShow);

        void getMethodOfPaymentsData(List<MethodOfPayment> methodOfPaymentToShow);

        void getExpensesData(List<Expense> expensesToShow);


    }

    interface Presenter extends BasePresenter {
        void loadCategories();
        void loadMethodOfPayments();
        void loadExpenses();

    }
}
