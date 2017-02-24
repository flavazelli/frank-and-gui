package com.example.francescovalela.trkr.data.local;

import android.support.annotation.NonNull;

import com.example.francescovalela.trkr.logExpense.models.MethodOfPayment;
import com.example.francescovalela.trkr.logExpense.models.MethodOfPaymentType;

import java.util.List;

/**
 * Created by Francis on 2017-02-23.
 */

public interface MethodOfPaymentDataSource {

    //load expense data into a list & checks if data is available
    interface LoadMethodOfPaymentsCallback {
        void onMethodOfPaymentsLoaded(List<MethodOfPayment> expenses);
        void onDataNotAvailable();
    }

    interface GetMethodOfPaymentCallback {
        void onMethodOfPaymentLoaded(MethodOfPayment expense);
        void onDataNotAvailable();
    }

    void getMethodOfPayments(@NonNull MethodOfPaymentDataSource.LoadMethodOfPaymentsCallback callback);

    void getMethodOfPayment(@NonNull String methodOfPaymentId, @NonNull GetMethodOfPaymentCallback callback);

    void saveMethodOfPayment(@NonNull MethodOfPayment methodOfPayment);

    void deleteAllMethodOfPayments();

    void deleteMethodOfPayment(@NonNull String methodOfPayment);
}
