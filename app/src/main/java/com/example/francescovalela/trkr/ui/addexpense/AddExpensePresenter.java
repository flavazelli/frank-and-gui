package com.example.francescovalela.trkr.ui.addExpense;

import com.example.francescovalela.trkr.data.local.ExpenseRepository;
import com.example.francescovalela.trkr.data.local.expense.ExpenseContract;
import com.example.francescovalela.trkr.logExpense.models.Expense;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by flavazelli on 2017-02-28.
 */

public class AddExpensePresenter implements AddExpenseContract.Presenter {


    private final ExpenseRepository mExpenseRepository;

    private final AddExpenseContract.View mExpenseView;

    public AddExpensePresenter(ExpenseRepository expenseRepository, AddExpenseContract.View expenseView ) {
        mExpenseRepository = checkNotNull(expenseRepository, "expenseRepository cannot be null");
        mExpenseView = checkNotNull(expenseView, "tasksView cannot be null!");

        mExpenseView.setPresenter(this);
    }

    // TODO stopped here

    @Override
    public void addExpense() {

    }

    @Override
    public void validateExpenseFields() {

    }

    @Override
    public void resetExpenseFields() {

    }

    @Override
    public void getLocation() {

    }

    @Override
    public void setLocation(double locationLat, double locationLong) {

    }

    @Override
    public void getDate() {

    }

    @Override
    public void setDate(long date) {

    }

    @Override
    public void start() {

    }
}
