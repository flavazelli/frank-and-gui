package com.example.francescovalela.trkr.ui.viewexpenses;

import android.support.annotation.NonNull;

import com.example.francescovalela.trkr.BasePresenter;
import com.example.francescovalela.trkr.data.local.ExpenseDataSource;
import com.example.francescovalela.trkr.data.local.ExpenseRepository;
import com.example.francescovalela.trkr.logExpense.models.Expense;

import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by flavazelli on 2017-02-28.
 */

public class ViewExpensesPresenter implements ViewExpensesContract.Presenter {

    ExpenseRepository mExpenseRepository;

    ViewExpensesContract.View mExpensesView;

    public ViewExpensesPresenter(@NonNull ExpenseRepository expensesRepository, @NonNull ViewExpensesContract.View expenseView) {
        mExpenseRepository = checkNotNull(expensesRepository, "expense repository cannot be null");
        mExpensesView = checkNotNull(expenseView, "expense view cannot be null");
    }

    @Override
    public void start() {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadExpenses() {
        mExpenseRepository.getExpenses(new ExpenseDataSource.LoadExpenseCallback() {
            @Override
            public void onExpensesLoaded(List<Expense> expenses) {
                mExpensesView.showExpenses(expenses);

                processExpenses(expenses);
            }

            @Override
            public void onDataNotAvailable() {
                mExpensesView.showExpenseError();
            }
        });

    }

    private void processExpenses(List<Expense> expenses) {
        if (expenses.isEmpty()) {
            mExpensesView.showNoExpenses();
        } else {
            // Show the list of expenses
            mExpensesView.showExpenses(expenses);

        }
    }


    @Override
    public void openExpenseDetails(@NonNull Expense requestedExpense) {

    }
}
