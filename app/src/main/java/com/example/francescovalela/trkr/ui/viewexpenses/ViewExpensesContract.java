package com.example.francescovalela.trkr.ui.viewexpenses;

import android.support.annotation.NonNull;

import com.example.francescovalela.trkr.BasePresenter;
import com.example.francescovalela.trkr.BaseView;
import com.example.francescovalela.trkr.logExpense.models.Expense;

import java.util.List;

/**
 * Created by flavazelli on 2017-02-28.
 */

public interface ViewExpensesContract {

    interface View extends BaseView<Presenter> {

        void showExpenses(List<Expense> expenses);

        void showExpenseDetails(String expenseId);

        void showExpenseError();

        void showNoExpenses();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadExpenses();

        void openExpenseDetails(@NonNull Expense requestedExpense);

    }
}
