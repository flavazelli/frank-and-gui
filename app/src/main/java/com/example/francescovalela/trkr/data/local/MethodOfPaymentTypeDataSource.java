package com.example.francescovalela.trkr.data.local;

import android.support.annotation.NonNull;

import com.example.francescovalela.trkr.data.local.methodofpayment.MethodOfPaymentLocalDataSource;
import com.example.francescovalela.trkr.logExpense.models.MethodOfPaymentType;

import java.util.List;

/**
 * Created by Francis on 2017-02-23.
 */

public interface MethodOfPaymentTypeDataSource  {

    //load expense data into a list & checks if data is available
    interface LoadMethodOfPaymentTypesCallback {
        void onMethodOfPaymentTypesLoaded(List<MethodOfPaymentType> expenses);
        void onDataNotAvailable();
    }

    interface GetMethodOfPaymentTypeCallback {
        void onMethodOfPaymentTypeLoaded(MethodOfPaymentType expense);
        void onDataNotAvailable();
    }

    void getMethodOfPaymentTypes(@NonNull MethodOfPaymentTypeDataSource.LoadMethodOfPaymentTypesCallback callback);

    void getMethodOfPaymentType(@NonNull String typeId, @NonNull GetMethodOfPaymentTypeCallback callback);

    void saveMethodOfPaymentType(@NonNull MethodOfPaymentType type);

    void deleteAllMethodOfPaymentTypes();

    void deleteMethodOfPaymentType(@NonNull String typeId);
}
