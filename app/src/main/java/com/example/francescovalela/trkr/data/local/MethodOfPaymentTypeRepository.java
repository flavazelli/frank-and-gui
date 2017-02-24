package com.example.francescovalela.trkr.data.local;

import android.support.annotation.NonNull;

import com.example.francescovalela.trkr.logExpense.models.MethodOfPaymentType;

/**
 * Created by Francis on 2017-02-23.
 */

public class MethodOfPaymentTypeRepository implements MethodOfPaymentTypeDataSource {

    @Override
    public void getMethodOfPaymentTypes(@NonNull LoadMethodOfPaymentTypesCallback callback) {

    }

    @Override
    public void getMethodOfPaymentType(@NonNull String typeId, @NonNull GetMethodOfPaymentTypeCallback callback) {

    }

    @Override
    public void saveMethodOfPaymentType(@NonNull MethodOfPaymentType type) {

    }

    @Override
    public void deleteAllMethodOfPaymentTypes() {

    }

    @Override
    public void deleteMethodOfPaymentType(@NonNull String typeId) {

    }
}
