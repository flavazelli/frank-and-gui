package com.example.francescovalela.trkr.data.local.category;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.francescovalela.trkr.data.local.expense.ExpenseContract;

/**
 * Created by francescovalela on 2017-02-14.
 */

public class CategoryDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Category.db";

    private static final String SQL_CREATE_ENTRIES_CATEGORY =
            "CREATE TABLE " + CategoryContract.CategoryEntry.TABLE_NAME + " (" +
                    CategoryContract.CategoryEntry.COLUMN_NAME_NAME + " TEXT NOT NULL" +
                    ");";

    private static final String INSERT_DEFAULT_DATA = "INSERT INTO " + CategoryContract.CategoryEntry.TABLE_NAME +
            " VALUES ('Automobile'), " +
            " ('Bank Charges'), " +
            " ('Charity'), " +
            " ('Childcare'), " +
            " ('Clothing'), " +
            " ('Credit Card Fees'), " +
            " ('Education'), " +
            " ('Events') , " +
            " ('Food & Drinks'), " +
            " ('Gifts'), " +
            " ('Healthcare'), " +
            " ('Household'), " +
            " ('Insurance'), " +
            " ('Leisure'), " +
            " ('Hobbies'), " +
            " ('Loans'), " +
            " ('Pet Care'), " +
            " ('Vacation')";

    private static final String SQL_DELETE_ENTRIES_CATEGORY =
            "DROP TABLE IF EXISTS " + CategoryContract.CategoryEntry.TABLE_NAME;

    public CategoryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_CATEGORY);
        db.execSQL(INSERT_DEFAULT_DATA);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
        db.execSQL(SQL_DELETE_ENTRIES_CATEGORY);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}
