package com.example.francescovalela.trkr.data.local;

import android.support.annotation.NonNull;

import com.example.francescovalela.trkr.logExpense.models.MethodOfPayment;
import com.example.francescovalela.trkr.logExpense.models.MethodOfPaymentType;

/**
 * Created by Francis on 2017-02-23.
 */

public class MethodOfPaymentRepository implements MethodOfPaymentDataSource {
    @Override
    public void getMethodOfPayments(@NonNull LoadMethodOfPaymentsCallback callback) {

    }

    @Override
    public void getMethodOfPayment(@NonNull String methodOfPaymentId, @NonNull GetMethodOfPaymentCallback callback) {

    }

    @Override
    public void saveMethodOfPayment(@NonNull MethodOfPayment methodOfPayment) {

    }

    @Override
    public void deleteAllMethodOfPayments() {

    }

    @Override
    public void deleteMethodOfPayment(@NonNull String methodOfPayment) {

    }
}
