package com.example.francescovalela.trkr.data.local.expense;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by francescovalela on 2017-02-14.
 */

public class ExpenseDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Expense.db";

    private static final String SQL_CREATE_ENTRIES_EXPENSE =
            "CREATE TABLE " + ExpenseContract.ExpenseEntry.TABLE_NAME + " (" +
                    ExpenseContract.ExpenseEntry.COLUMN_NAME_NAME + " TEXT NOT NULL," +
                    ExpenseContract.ExpenseEntry.COLUMN_NAME_COST + " REAL NOT NULL," +
                    ExpenseContract.ExpenseEntry.COLUMN_NAME_LOCATIONLONG + " REAL NOT NULL," +
                    ExpenseContract.ExpenseEntry.COLUMN_NAME_LOCATIONLAT + " REAL NOT NULL," +
                    ExpenseContract.ExpenseEntry.COLUMN_NAME_METHODOFPAYMENTID + " INTEGER NOT NULL," +
                    ExpenseContract.ExpenseEntry.COLUMN_NAME_CATEGORYID + " INTEGER NOT NULL" +
                    ExpenseContract.ExpenseEntry.COLUMN_NAME_DATE + " INTEGER NOT NULL," +
                    ");";

    private static final String SQL_DELETE_ENTRIES_EXPENSE =
            "DROP TABLE IF EXISTS " + ExpenseContract.ExpenseEntry.TABLE_NAME;

    public ExpenseDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_EXPENSE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
        db.execSQL(SQL_DELETE_ENTRIES_EXPENSE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}
