package com.example.francescovalela.trkr.ui.addExpense;

import com.example.francescovalela.trkr.data.local.ExpenseRepository;
import com.example.francescovalela.trkr.data.local.expense.ExpenseContract;
import com.example.francescovalela.trkr.logExpense.models.Expense;

import static android.R.attr.name;
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

    @Override
    public void start() {

    }

    public void addExpense(int expenseId, long date, String name, double cost, double locationLat, double locationLong, int methodOfPaymentId, int categoryId) {
        Expense expense = new Expense(expenseId, date, name, cost, locationLat, locationLong, methodOfPaymentId, categoryId);

        mExpenseRepository.saveExpense(expense);
    }
}
