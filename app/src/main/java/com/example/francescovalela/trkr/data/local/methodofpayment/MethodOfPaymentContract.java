package com.example.francescovalela.trkr.data.local.methodofpayment;

import android.provider.BaseColumns;

/**
 * Created by francescovalela on 2017-02-14.
 *
 * The contract used for the db to save the tasks locally.
 */

public class MethodOfPaymentContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private MethodOfPaymentContract() {}

    public static abstract class MethodOfPaymentEntry implements BaseColumns {
        public static final String TABLE_NAME = "method_of_payment";
        public static final String COLUMN_NAME_METHODOFPAYMENTID = "method_of_payment_id";
        public static final String COLUMN_NAME_NICKNAME = "nickname";
        public static final String COLUMN_NAME_TYPEID = "type_id";
        public static final String COLUMN_NAME_DATE = "date";
    }
}
