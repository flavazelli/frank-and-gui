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

    fdsfdsfsdfsdfgfdgsdf;
}
