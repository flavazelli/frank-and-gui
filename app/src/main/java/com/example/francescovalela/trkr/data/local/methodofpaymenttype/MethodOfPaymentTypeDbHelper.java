package com.example.francescovalela.trkr.data.local.methodofpaymenttype;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.francescovalela.trkr.data.local.methodofpaymenttype.MethodOfPaymentTypeContract.*;

/**
 * Created by flavazelli on 2017-02-14.
 */


public class MethodOfPaymentTypeDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "MethodOfPaymentType.db";

    private static final String SQL_CREATE_METHODOFPAYMENTTYPE =
            "CREATE TABLE " + MethodOfPaymentTypeEntry.TABLE_NAME + " (" +
                    MethodOfPaymentTypeEntry._ID + " INTEGER PRIMARY KEY," +
                    MethodOfPaymentTypeEntry.COLUMN_NAME_ID + " INTEGER NOT NULL," +
                    MethodOfPaymentTypeEntry.COLUMN_NAME_NAME + " TEXT NOT NULL" +
                    ");";

    private static final String SQL_DELETE_METHODOFPAYMENTTYPE =
            "DROP TABLE IF EXISTS " + MethodOfPaymentTypeEntry.TABLE_NAME;

    public MethodOfPaymentTypeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_METHODOFPAYMENTTYPE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_METHODOFPAYMENTTYPE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
