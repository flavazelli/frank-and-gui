package com.example.francescovalela.trkr.data.local;

import android.support.annotation.NonNull;

import com.example.francescovalela.trkr.logExpense.models.Expense;

import java.util.List;

// Main entry point for accessing expense data
public interface ExpenseDataSource {

    //load expense data into a list & checks if data is available
    interface LoadExpenseCallback {

        void onExpensesLoaded(List<Expense> expenses);
        void onDataNotAvailable();
    }

    interface GetExpenseCallback {

        void onExpenseLoaded(Expense expense);
        void onDataNotAvailable();
    }

    void getExpenses(@NonNull LoadExpenseCallback callback);

    void getExpense(@NonNull String expenseId, @NonNull GetExpenseCallback callback);

    void saveExpense(@NonNull Expense expense);

    void deleteAllExpenses();

    void deleteExpense(@NonNull String expenseId);
}
