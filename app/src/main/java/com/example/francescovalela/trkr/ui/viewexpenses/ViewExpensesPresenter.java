package com.example.francescovalela.trkr.ui.viewexpenses;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.francescovalela.trkr.BasePresenter;
import com.example.francescovalela.trkr.data.local.ExpenseDataSource;
import com.example.francescovalela.trkr.data.local.ExpenseRepository;
import com.example.francescovalela.trkr.data.local.expense.ExpenseLocalDataSource;
import com.example.francescovalela.trkr.logExpense.models.Expense;

import java.util.List;

import static android.content.ContentValues.TAG;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by flavazelli on 2017-02-28.
 */

public class ViewExpensesPresenter implements ViewExpensesContract.Presenter {

    private ExpenseRepository mExpenseRepository;
    private ExpenseLocalDataSource mLocalDataSource;

    private ViewExpensesContract.View mExpensesView;

    public ViewExpensesPresenter(@NonNull ViewExpensesContract.View expenseView, Context context) {
        mExpenseRepository = mExpenseRepository.getInstance(mLocalDataSource.getInstance(context));

        mExpensesView = checkNotNull(expenseView, "expense view cannot be null");
        mExpensesView.setPresenter(this);
    }

    @Override
    public void start() {
        loadExpenses();
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }

    @Override
    public void loadExpenses() {
        mExpenseRepository.getExpenses(new ExpenseDataSource.LoadExpenseCallback() {
            @Override
            public void onExpensesLoaded(List<Expense> expenses) {
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
