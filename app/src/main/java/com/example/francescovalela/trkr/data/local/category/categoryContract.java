package com.example.francescovalela.trkr.data.local.category;

import android.provider.BaseColumns;

/**
 * Created by francescovalela on 2017-02-14.
 *
 * The contract used for the db to save the tasks locally.
 */

public final class CategoryContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private CategoryContract() {}

    public static abstract class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME_NAME = "name";

    }
}
