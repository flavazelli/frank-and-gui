package com.example.francescovalela.trkr.data.local.methodofpayment;


import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by flavazelli on 2017-02-14.
 */

public class MethodOfPaymentDbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 2;

    public static final String DATABASE_NAME = "MethodOfPayment.db";

    private static final String SQL_CREATE_METHODOFPAYMENT =
            "CREATE TABLE " + MethodOfPaymentContract.MethodOfPaymentEntry.TABLE_NAME + " (" +
                    MethodOfPaymentContract.MethodOfPaymentEntry.COLUMN_NAME_METHODOFPAYMENTID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    MethodOfPaymentContract.MethodOfPaymentEntry.COLUMN_NAME_NICKNAME + " TEXT NOT NULL," +
                    MethodOfPaymentContract.MethodOfPaymentEntry.COLUMN_NAME_TYPEID + " INTEGER NOT NULL," +
                    MethodOfPaymentContract.MethodOfPaymentEntry.COLUMN_NAME_DATE + " INTEGER NOT NULL" +
                    ");";

    private static final String INSERT_DEFAULT_DATA = "INSERT INTO " + MethodOfPaymentContract.MethodOfPaymentEntry.TABLE_NAME +
            " (" + MethodOfPaymentContract.MethodOfPaymentEntry.COLUMN_NAME_NICKNAME + ", " +
            MethodOfPaymentContract.MethodOfPaymentEntry.COLUMN_NAME_TYPEID + ", " +
            MethodOfPaymentContract.MethodOfPaymentEntry.COLUMN_NAME_DATE + ") " +
            " VALUES ('Cash', 0, 0), " +
            " ('Credit Card1', 1, 0), " +
            " ('Debit Card1', 2, 0);";

    private static final String SQL_DELETE_METHODOFPAYMENT =
            "DROP TABLE IF EXISTS " + MethodOfPaymentContract.MethodOfPaymentEntry.TABLE_NAME;

    public MethodOfPaymentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_METHODOFPAYMENT);
        db.execSQL(INSERT_DEFAULT_DATA);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_METHODOFPAYMENT);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
