package com.example.francescovalela.trkr.data.local.methodofpayment;


import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.francescovalela.trkr.logExpense.models.MethodOfPayment;

import static com.example.francescovalela.trkr.data.local.methodofpayment.MethodOfPaymentContract.*;

/**
 * Created by flavazelli on 2017-02-14.
 */

public class MethodOfPaymentDbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "MethodOfPayment.db";

    private static final String SQL_CREATE_METHODOFPAYMENT =
            "CREATE TABLE " + MethodOfPaymentEntry.TABLE_NAME + " (" +
                    MethodOfPaymentEntry.COLUMN_NAME_NICKNAME + " TEXT NOT NULL," +
                    MethodOfPaymentEntry.COLUMN_NAME_TYPEID + " INTEGER NOT NULL," +
                    MethodOfPaymentEntry.COLUMN_NAME_DATE + " INTEGER NOT NULL" +
                    ");";
    private static final String SQL_DELETE_METHODOFPAYMENT =
            "DROP TABLE IF EXISTS " + MethodOfPaymentEntry.TABLE_NAME;

    public MethodOfPaymentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_METHODOFPAYMENT);
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
