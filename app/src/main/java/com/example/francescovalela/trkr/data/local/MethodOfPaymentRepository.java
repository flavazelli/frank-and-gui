package com.example.francescovalela.trkr.data.local;

import android.provider.Settings;
import android.support.annotation.NonNull;

import com.example.francescovalela.trkr.data.local.methodofpayment.MethodOfPaymentLocalDataSource;
import com.example.francescovalela.trkr.logExpense.models.MethodOfPayment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by Francis on 2017-02-23.
 */

public class MethodOfPaymentRepository implements MethodOfPaymentDataSource {

    private static MethodOfPaymentRepository INSTANCE = null;

    private final MethodOfPaymentDataSource mMethodOfPaymentLocalDataSource;

    Map<String, MethodOfPayment> methodOfPaymentMap;

    private MethodOfPaymentRepository(@NonNull MethodOfPaymentDataSource methodOfPaymentLocalDataSource) {
        mMethodOfPaymentLocalDataSource = checkNotNull(methodOfPaymentLocalDataSource);
    }

    // Returns the single instance of this class, creating it if necessary.
    public static MethodOfPaymentRepository getInstance(@NonNull MethodOfPaymentDataSource methodOfPaymentTypeDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new MethodOfPaymentRepository(methodOfPaymentTypeDataSource);
        }
        return INSTANCE;
    }
    @Override
    public void getMethodOfPayments(@NonNull final LoadMethodOfPaymentsCallback callback) {
        mMethodOfPaymentLocalDataSource.getMethodOfPayments(new LoadMethodOfPaymentsCallback() {
            @Override
            public void onMethodOfPaymentsLoaded(List<MethodOfPayment> expenses) {
                callback.onMethodOfPaymentsLoaded(new ArrayList<> (methodOfPaymentMap.values()));
            }

            @Override
            public void onDataNotAvailable() {
                System.out.println("Data source not available");
            }
        });

    }

    @Override
    public void getMethodOfPayment(@NonNull String methodOfPaymentId, @NonNull final GetMethodOfPaymentCallback callback) {
        checkNotNull(methodOfPaymentId);
        checkNotNull(callback);
        mMethodOfPaymentLocalDataSource.getMethodOfPayment(methodOfPaymentId, new GetMethodOfPaymentCallback() {
            @Override
            public void onMethodOfPaymentLoaded(MethodOfPayment methodOfPayment) {
                //TODO: primary key or nah?
                //methodOfPaymentMap.put(String.valueOf(methodOfPayment.getId(), methodOfPayment));
                callback.onMethodOfPaymentLoaded(methodOfPayment);
            }

            @Override
            public void onDataNotAvailable() {
                System.out.println("Data source not available");
            }
        });

    }

    @Override
    public void saveMethodOfPayment(@NonNull MethodOfPayment methodOfPayment) {
        checkNotNull(methodOfPayment);
        mMethodOfPaymentLocalDataSource.saveMethodOfPayment(methodOfPayment);

    }

    @Override
    public void deleteAllMethodOfPayments() {
        mMethodOfPaymentLocalDataSource.deleteAllMethodOfPayments();

    }

    @Override
    public void deleteMethodOfPayment(@NonNull String methodOfPayment) {
        checkNotNull(methodOfPayment);
        mMethodOfPaymentLocalDataSource.deleteMethodOfPayment(methodOfPayment);

    }
}
