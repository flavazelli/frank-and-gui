package com.example.francescovalela.trkr.data.local;

import android.support.annotation.NonNull;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

import com.example.francescovalela.trkr.logExpense.models.Expense;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by francescovalela on 2017-02-17.
 */

public class ExpenseRepository implements ExpenseDataSource {

    private static ExpenseRepository INSTANCE = null;

    private final ExpenseDataSource mExpenseLocalDataSource;

    Map<String, Expense> expenseMap;

    // Makes sure to only instantiate once
    private ExpenseRepository(@NonNull ExpenseDataSource expenseLocalDataSource) {
        mExpenseLocalDataSource = checkNotNull(expenseLocalDataSource);
    }


    // Returns the single instance of this class, creating it if necessary.
    public static ExpenseRepository getInstance(@NonNull ExpenseDataSource expenseLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ExpenseRepository(expenseLocalDataSource);
        }
        return INSTANCE;
    }


    // Used to force {@link #getInstance(ExpenseDataSource)} to create a new instance
    public static void destroyInstance() {
        INSTANCE = null;
    }

    // Gets expenses from local data source (SQLite db)
    // onDataNotAvailable() is fired if all data sources (currently just SQLite) fail to get the data
    @Override
    public void getExpenses(@NonNull final LoadExpenseCallback callback) {
        checkNotNull(callback);

        // Queries the local storage
        // calls method and sends arraylist with values
        mExpenseLocalDataSource.getExpenses(new LoadExpenseCallback() {
            @Override
            public void onExpensesLoaded(List<Expense> expenses) {
                callback.onExpensesLoaded(new ArrayList<>(expenseMap.values()));
            }

            @Override
            // Would put getExpensesRemoteDataSource(callback) if connected to remote db
            public void onDataNotAvailable() {
                System.out.print("No data source connected");
            }
        });
    }

    // TODO do we change expenseId for the primary keys of expense?
    // Gets single expense from local data source (SQLite db) and puts it into the map.
    // onDataNotAvailable() is fired if all data sources (currently just SQLite) fail to get the data
    @Override
    public void getExpense(@NonNull String expenseId, @NonNull final GetExpenseCallback callback) {
        checkNotNull(expenseId);
        checkNotNull(callback);

        // Queries the local storage
        mExpenseLocalDataSource.getExpense(expenseId, new GetExpenseCallback() {
            @Override
            public void onExpenseLoaded(Expense expense) {
                // TODO do we change getId() for the primary keys of expense?
                //expenseMap.put(String.valueOf(expense.getId()), expense);
                callback.onExpenseLoaded(expense);
            }

            @Override
            // Would put getExpensesRemoteDataSource(callback) if connected to remote db
            public void onDataNotAvailable() {
                System.out.print("No data source connected");
            }
        });
    }

    // Saves expense to db
    @Override
    public void saveExpense(@NonNull Expense expense) {
        checkNotNull(expense);
        mExpenseLocalDataSource.saveExpense(expense);
    }

    @Override
    public void deleteAllExpenses() {
        mExpenseLocalDataSource.deleteAllExpenses();
    }

    @Override
    public void deleteExpense(@NonNull String expenseId) {
        mExpenseLocalDataSource.deleteExpense(checkNotNull(expenseId));
    }
}
