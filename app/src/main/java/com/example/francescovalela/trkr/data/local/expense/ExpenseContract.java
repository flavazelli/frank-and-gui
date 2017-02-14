package com.example.francescovalela.trkr.data.local.expense;

import android.provider.BaseColumns;

/**
 * Created by francescovalela on 2017-02-14.
 *
 * The contract used for the db to save the tasks locally.
 */

public class ExpenseContract  {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ExpenseContract() {}

    /* Inner class that defines the table contents */
    public static abstract class ExpenseEntry implements BaseColumns {
        public static final String TABLE_NAME = "expense";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_COST = "cost";
        public static final String COLUMN_NAME_METHODOFPAYMENTID = "method_of_payment_id";
        public static final String COLUMN_NAME_CATEGORYID = "category_id";
    }

    private static final String SQL_CREATE_ENTRIES_EXPENSE =
            "CREATE TABLE " + ExpenseEntry.TABLE_NAME + " (" +
                    ExpenseEntry._ID + " INTEGER PRIMARY KEY," +
                    ExpenseEntry.COLUMN_NAME_DATE + " INTEGER NOT NULL," +
                    ExpenseEntry.COLUMN_NAME_NAME + " TEXT NOT NULL," +
                    ExpenseEntry.COLUMN_NAME_COST + " REAL NOT NULL," +
                    ExpenseEntry.COLUMN_NAME_METHODOFPAYMENTID + " INTEGER NOT NULL," +
                    ExpenseEntry.COLUMN_NAME_CATEGORYID + " INTEGER NOT NULL" +
                    ");";

    private static final String SQL_CREATE_ENTRIES_CATEGORY =
            "CREATE TABLE " + CategoryEntry.TABLE_NAME + " (" +
                    CategoryEntry._ID + " INTEGER PRIMARY KEY," +
                    CategoryEntry.COLUMN_NAME_ID + " INTEGER NOT NULL," +
                    CategoryEntry.COLUMN_NAME_NAME + " TEXT NOT NULL" +
                    ");";

    private static final String SQL_CREATE_ENTRIES_METHODOFPAYMENT =
            "CREATE TABLE " + MethodOfPaymentEntry.TABLE_NAME + " (" +
                    MethodOfPaymentEntry._ID + " INTEGER PRIMARY KEY," +
                    MethodOfPaymentEntry.COLUMN_NAME_NICKNAME + " TEXT NOT NULL," +
                    MethodOfPaymentEntry.COLUMN_NAME_TYPEID + " INTEGER NOT NULL," +
                    MethodOfPaymentEntry.COLUMN_NAME_DATE + " INTEGER NOT NULL" +
                    ");";

    private static final String SQL_CREATE_ENTRIES_METHODOFPAYMENTTYPE =
            "CREATE TABLE " + MethodOfPaymentTypeEntry.TABLE_NAME + " (" +
                    MethodOfPaymentTypeEntry._ID + " INTEGER PRIMARY KEY," +
                    MethodOfPaymentTypeEntry.COLUMN_NAME_ID + " INTEGER NOT NULL," +
                    MethodOfPaymentTypeEntry.COLUMN_NAME_NAME + " TEXT NOT NULL" +
                    ");";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ExpenseEntry.TABLE_NAME + ", " +
                    CategoryEntry.TABLE_NAME + ", " +
                    MethodOfPaymentEntry.TABLE_NAME + ", " +
                    MethodOfPaymentTypeEntry.TABLE_NAME;

}
