package com.example.francescovalela.trkr.data.local.category;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.francescovalela.trkr.data.local.category.CategoryContract.CategoryEntry;
import com.example.francescovalela.trkr.data.local.CategoryDataSource;
import com.example.francescovalela.trkr.logExpense.models.Category;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by francescovalela on 2017-02-17.
 */

public class CategoryLocalDataSource implements CategoryDataSource {

    private static CategoryLocalDataSource INSTANCE;

    private CategoryDBHelper mDbHelper;

    //private to prevent instantiation
    private CategoryLocalDataSource(@NonNull Context context) {
        checkNotNull(context);
        mDbHelper = new CategoryDBHelper(context);
    }

    //constructor
    public static CategoryLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CategoryLocalDataSource(context);
        }
        return INSTANCE;
    }

    // Goes through all category queries and adds to list.
    @Override
    public void getCategories(@NonNull LoadCategoryCallback callback) {
        List<Category> categories = new ArrayList<Category>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                CategoryEntry.COLUMN_NAME_NAME
        };

        Cursor c = db.query(CategoryEntry.TABLE_NAME, projection, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {

                // TODO check if this has to be a string
                int id = c.getInt(c.getColumnIndexOrThrow(CategoryEntry._ID));
                String name = c.getString(c.getColumnIndexOrThrow(CategoryEntry.COLUMN_NAME_NAME));

                Category category = new Category(id, name);
                categories.add(category);
            }
        }

        if (c != null) {
            c.close();
        }

        db.close();

        // will go in if table is empty/new
        if (categories.isEmpty()) {
            callback.onDataNotAvailable();
        } else {
            callback.onCategoriesLoaded(categories);
        }
    }

    @Override
    public void getCategory(@NonNull String categoryId, @NonNull GetCategoryCallback callback) {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                CategoryEntry.COLUMN_NAME_NAME
        };

        // because ID is primary key, it's all we need to search
        String selection = CategoryEntry._ID + " LIKE ?";
        String[] selectionArgs = { categoryId };


        Cursor c = db.query(CategoryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        Category category = null;

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            // TODO check if this has to be a string
            int id = c.getInt(c.getColumnIndexOrThrow(CategoryEntry._ID));
            String name = c.getString(c.getColumnIndexOrThrow(CategoryEntry.COLUMN_NAME_NAME));

            category = new Category(id, name);
        }

        if (c != null) {
            c.close();
        }

        db.close();

        // will go in if category is populated
        if (category != null) {
            callback.onCategoryLoaded(category);
        } else {
            callback.onCategoryLoaded(category);
        }
    }

    @Override
    public void saveCategory(@NonNull Category category) {

        checkNotNull(category);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CategoryEntry._ID, category.getId());
        values.put(CategoryEntry.COLUMN_NAME_NAME, category.getName());

        db.insert(CategoryEntry.TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void deleteAllCategories() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        db.delete(CategoryEntry.TABLE_NAME, null, null);

        db.close();
    }

    @Override
    public void deleteCategory(@NonNull String categoryId) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String selection = CategoryEntry._ID + " LIKE ?";
        String[] selectionArgs = { categoryId };

        db.delete(CategoryEntry.TABLE_NAME, selection, selectionArgs);

        db.close();
    }
}
