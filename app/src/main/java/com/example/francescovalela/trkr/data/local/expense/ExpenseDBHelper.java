package com.example.francescovalela.trkr.data.local.expense;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.francescovalela.trkr.data.local.category.CategoryContract;
import com.example.francescovalela.trkr.logExpense.models.Expense;

import static com.example.francescovalela.trkr.data.local.expense.ExpenseContract.*;

/**
 * Created by francescovalela on 2017-02-14.
 */

public class ExpenseDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;

    public static final String DATABASE_NAME = "Expense.db";

    private static final String SQL_CREATE_ENTRIES_EXPENSE =
            "CREATE TABLE " + ExpenseContract.ExpenseEntry.TABLE_NAME + " (" +
                    ExpenseEntry.COLUMN_NAME_EXPENSEID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ExpenseEntry.COLUMN_NAME_NAME + " TEXT NOT NULL," +
                    ExpenseEntry.COLUMN_NAME_COST + " REAL NOT NULL," +
                    ExpenseEntry.COLUMN_NAME_LOCATIONLONG + " REAL NOT NULL," +
                    ExpenseEntry.COLUMN_NAME_LOCATIONLAT + " REAL NOT NULL," +
                    ExpenseEntry.COLUMN_NAME_METHODOFPAYMENTID + " INTEGER," +
                    ExpenseEntry.COLUMN_NAME_CATEGORYID + " INTEGER, " +
                    ExpenseEntry.COLUMN_NAME_DATE + " INTEGER NOT NULL" +
                    ");";

    private static final String SQL_DELETE_ENTRIES_EXPENSE =
            "DROP TABLE IF EXISTS " + ExpenseContract.ExpenseEntry.TABLE_NAME;

    private static final String INSERT_DEFAULT_DATA = "INSERT INTO " + ExpenseContract.ExpenseEntry.TABLE_NAME +
            " (" + ExpenseEntry.COLUMN_NAME_NAME + ", " +
            ExpenseEntry.COLUMN_NAME_COST + ", " +
            ExpenseEntry.COLUMN_NAME_LOCATIONLONG + ", " +
            ExpenseEntry.COLUMN_NAME_LOCATIONLAT + ", " +
            ExpenseEntry.COLUMN_NAME_METHODOFPAYMENTID + ", " +
            ExpenseEntry.COLUMN_NAME_CATEGORYID + ", " +
            ExpenseEntry.COLUMN_NAME_DATE + ") " +
            " VALUES ('1', 32.49, -70, 70, 1, 1, 1496980800000), " +
            "('2', 42.40, -70, 70, 2, 3, 1497240000000), " +
            "('3', 62.41, -70, 70, 3, 2, 1497326400000)";

    public ExpenseDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_EXPENSE);
        db.execSQL(INSERT_DEFAULT_DATA);
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
