package com.example.francescovalela.trkr.data.local.expense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.francescovalela.trkr.data.local.ExpenseDataSource;
import com.example.francescovalela.trkr.data.local.expense.ExpenseContract.ExpenseEntry;
import com.example.francescovalela.trkr.logExpense.models.Expense;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;


public class ExpenseLocalDataSource implements ExpenseDataSource {

    private static ExpenseLocalDataSource INSTANCE;

    private ExpenseDBHelper mDbHelper;

    //private to prevent instantiation
    private ExpenseLocalDataSource(@NonNull Context context) {
        checkNotNull(context);
        mDbHelper = new ExpenseDBHelper(context);
    }

    //constructor
    public static ExpenseLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ExpenseLocalDataSource(context);
        }
        return INSTANCE;
    }

    // Goes through all expense queries and adds to list.
    @Override
    public void getExpenses(@NonNull ExpenseDataSource.LoadExpenseCallback callback) {
        List<Expense> expenses = new ArrayList<Expense>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                ExpenseEntry.COLUMN_NAME_DATE,
                ExpenseEntry.COLUMN_NAME_NAME,
                ExpenseEntry.COLUMN_NAME_COST,
                ExpenseEntry.COLUMN_NAME_LOCATIONLAT,
                ExpenseEntry.COLUMN_NAME_LOCATIONLONG,
                ExpenseEntry.COLUMN_NAME_METHODOFPAYMENTID,
                ExpenseEntry.COLUMN_NAME_CATEGORYID
        };

        Cursor c = db.query(ExpenseEntry.TABLE_NAME, projection, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {

                int id = c.getInt(c.getColumnIndexOrThrow(ExpenseEntry.COLUMN_NAME_EXPENSEID));
                long date = c.getLong(c.getColumnIndexOrThrow(ExpenseEntry.COLUMN_NAME_DATE));
                String name = c.getString(c.getColumnIndexOrThrow(ExpenseEntry.COLUMN_NAME_NAME));
                double cost = c.getDouble(c.getColumnIndexOrThrow(ExpenseEntry.COLUMN_NAME_COST));
                double locationLat = c.getDouble(c.getColumnIndexOrThrow(ExpenseEntry.COLUMN_NAME_LOCATIONLAT));
                double locationLong = c.getDouble(c.getColumnIndexOrThrow(ExpenseEntry.COLUMN_NAME_LOCATIONLONG));
                int methodOfPaymentTypeId = c.getInt(c.getColumnIndexOrThrow(ExpenseEntry.COLUMN_NAME_METHODOFPAYMENTID));
                int categoryId = c.getInt(c.getColumnIndexOrThrow(ExpenseEntry.COLUMN_NAME_CATEGORYID));

                Expense expense = new Expense(id, date, name, cost, locationLat, locationLong, methodOfPaymentTypeId, categoryId);
                expenses.add(expense);
            }
        }

        if (c != null) {
            c.close();
        }

        db.close();

        // will go in if table is empty/new
        if (expenses.isEmpty()) {
            for (int i = 0; i < 20; i++)
            {
                Expense expense = new Expense(i, 1, "Expense" + i, 10.00, 1, 1, 1, 1);
                expenses.add(expense);
            }

            callback.onExpensesLoaded(expenses);
        } else {
            callback.onExpensesLoaded(expenses);
        }
    }

    @Override
    public void getExpense(@NonNull String expenseId, @NonNull GetExpenseCallback callback) {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                ExpenseEntry.COLUMN_NAME_EXPENSEID,
                ExpenseEntry.COLUMN_NAME_DATE,
                ExpenseEntry.COLUMN_NAME_NAME,
                ExpenseEntry.COLUMN_NAME_COST,
                ExpenseEntry.COLUMN_NAME_LOCATIONLAT,
                ExpenseEntry.COLUMN_NAME_LOCATIONLONG,
                ExpenseEntry.COLUMN_NAME_METHODOFPAYMENTID,
                ExpenseEntry.COLUMN_NAME_CATEGORYID
        };

        // because ID is primary key, it's all we need to search
        String selection = ExpenseEntry.COLUMN_NAME_EXPENSEID + " LIKE ?";
        String[] selectionArgs = { expenseId };


        Cursor c = db.query(ExpenseEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        Expense expense = null;

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();

            int id = c.getInt(c.getColumnIndexOrThrow(ExpenseEntry.COLUMN_NAME_EXPENSEID));
            long date = c.getLong(c.getColumnIndexOrThrow(ExpenseEntry.COLUMN_NAME_DATE));
            String name = c.getString(c.getColumnIndexOrThrow(ExpenseEntry.COLUMN_NAME_NAME));
            double cost = c.getDouble(c.getColumnIndexOrThrow(ExpenseEntry.COLUMN_NAME_COST));
            double locationLat = c.getDouble(c.getColumnIndexOrThrow(ExpenseEntry.COLUMN_NAME_LOCATIONLAT));
            double locationLong = c.getDouble(c.getColumnIndexOrThrow(ExpenseEntry.COLUMN_NAME_LOCATIONLONG));
            int methodOfPaymentTypeId = c.getInt(c.getColumnIndexOrThrow(ExpenseEntry.COLUMN_NAME_METHODOFPAYMENTID));
            int categoryId = c.getInt(c.getColumnIndexOrThrow(ExpenseEntry.COLUMN_NAME_CATEGORYID));


            expense = new Expense(id, date, name, cost, locationLat, locationLong, methodOfPaymentTypeId, categoryId);
        }

        if (c != null) {
            c.close();
        }

        db.close();

        // will go in if expense is populated
        if (expense != null) {
            callback.onExpenseLoaded(expense);
        } else {
            callback.onExpenseLoaded(expense);
        }
    }

    @Override
    public void saveExpense(@NonNull Expense expense) {

        checkNotNull(expense);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ExpenseEntry.COLUMN_NAME_DATE, String.valueOf(expense.getDate()));
        values.put(ExpenseEntry.COLUMN_NAME_NAME, expense.getName());
        values.put(ExpenseEntry.COLUMN_NAME_COST, expense.getCost());
        values.put(ExpenseEntry.COLUMN_NAME_LOCATIONLAT, expense.getLocation()[0]);
        values.put(ExpenseEntry.COLUMN_NAME_LOCATIONLONG, expense.getLocation()[1]);
        // TODO find out how to get methodofpayment from the ID
        values.put(ExpenseEntry.COLUMN_NAME_METHODOFPAYMENTID, 1);
        // TODO find out how to get category from the ID
        values.put(ExpenseEntry.COLUMN_NAME_CATEGORYID, 1);

        db.insert(ExpenseEntry.TABLE_NAME, null, values);

        Log.d("insert expense in db", "insert expense in db");
        db.close();
    }

    @Override
    public void deleteAllExpenses() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        db.delete(ExpenseEntry.TABLE_NAME, null, null);

        db.close();
    }

    @Override
    public void deleteExpense(@NonNull String expenseId) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        
        String selection = ExpenseEntry.COLUMN_NAME_EXPENSEID + " LIKE ?";
        String[] selectionArgs = { expenseId };

        db.delete(ExpenseEntry.TABLE_NAME, selection, selectionArgs);

        db.close();
    }
}
