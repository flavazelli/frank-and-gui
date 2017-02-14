package com.example.francescovalela.trkr.data.local.methodofpaymenttype;

import android.provider.BaseColumns;

/**
 * Created by francescovalela on 2017-02-14.
 *
 * The contract used for the db to save the tasks locally.
 */

public class MethodOfPaymentTypeContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private MethodOfPaymentTypeContract() {}

    public static abstract class MethodOfPaymentTypeEntry implements BaseColumns {
        public static final String TABLE_NAME = "method_of_payment_type";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
    }
}
