package com.example.francescovalela.trkr.data.local.methodofpaymenttype;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.francescovalela.trkr.data.local.MethodOfPaymentTypeDataSource;
import com.example.francescovalela.trkr.logExpense.models.MethodOfPaymentType;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.internal.util.Checks.checkNotNull;
import static com.example.francescovalela.trkr.data.local.methodofpaymenttype.MethodOfPaymentTypeContract.*;

/**
 * Created by flavazelli on 2017-02-14.
 */

public class MethodOfPaymentTypeLocalDataSource implements MethodOfPaymentTypeDataSource {

    private MethodOfPaymentTypeDbHelper mDbHelper;

    private static MethodOfPaymentTypeLocalDataSource INSTANCE;

    //private to prevent instantiation
    private MethodOfPaymentTypeLocalDataSource(@NonNull Context context) {
        checkNotNull(context);
        mDbHelper = new MethodOfPaymentTypeDbHelper(context);
    }

    //constructor
    public static MethodOfPaymentTypeLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new MethodOfPaymentTypeLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getMethodOfPaymentTypes(@NonNull LoadMethodOfPaymentTypesCallback callback) {
        List<MethodOfPaymentType> types = new ArrayList<MethodOfPaymentType>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
               MethodOfPaymentTypeEntry.COLUMN_NAME_NAME
        };

        Cursor c = db.query(MethodOfPaymentTypeEntry.TABLE_NAME, projection, null, null, null,null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex(MethodOfPaymentTypeEntry.COLUMN_NAME_METHODOFPAYMENTTYPEID));
                String name = c.getString(c.getColumnIndexOrThrow(MethodOfPaymentTypeEntry.COLUMN_NAME_NAME));
                MethodOfPaymentType type = new MethodOfPaymentType(id, name);
                types.add(type);
            }
        }

        if (c != null) {
            c.close();
        }

        db.close();

        // will go in if table is empty/new
        if (types.isEmpty()) {
            callback.onDataNotAvailable();
        } else {
            callback.onMethodOfPaymentTypesLoaded(types);
        }
    }

    @Override
    public void getMethodOfPaymentType(@NonNull String typeId, @NonNull GetMethodOfPaymentTypeCallback callback) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                MethodOfPaymentTypeEntry.COLUMN_NAME_NAME,
        };

        // because ID is primary key, it's all we need to search
        String selection = MethodOfPaymentTypeEntry.COLUMN_NAME_METHODOFPAYMENTTYPEID + " LIKE ?";
        String[] selectionArgs = { typeId };


        Cursor c = db.query(MethodOfPaymentTypeEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        MethodOfPaymentType type = null;

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();

            int id = c.getInt(c.getColumnIndex(MethodOfPaymentTypeEntry.COLUMN_NAME_METHODOFPAYMENTTYPEID));
            String name = c.getString(c.getColumnIndexOrThrow(MethodOfPaymentTypeEntry.COLUMN_NAME_NAME));
            type = new MethodOfPaymentType(id, name);
        }

        if (c != null) {
            c.close();
        }

        db.close();

        // will go in if expense is populated
        if (type != null) {
            callback.onMethodOfPaymentTypeLoaded(type);
        } else {
            callback.onDataNotAvailable();
        }

    }

    @Override
    public void saveMethodOfPaymentType(@NonNull MethodOfPaymentType type) {
        checkNotNull(type);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MethodOfPaymentTypeEntry.COLUMN_NAME_NAME, type.getName());

        db.insert(MethodOfPaymentTypeEntry.TABLE_NAME, null, contentValues);
        db.close();
    }

    @Override
    public void deleteAllMethodOfPaymentTypes() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        db.delete(MethodOfPaymentTypeEntry.TABLE_NAME, null, null);

        db.close();
    }

    @Override
    public void deleteMethodOfPaymentType(@NonNull String typeId) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String selection = MethodOfPaymentTypeEntry.COLUMN_NAME_METHODOFPAYMENTTYPEID + " LIKE ?";
        String[] arguments = {typeId};

        db.delete(MethodOfPaymentTypeEntry.TABLE_NAME, selection, arguments);

        db.close();
    }
}
