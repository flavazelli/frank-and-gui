package com.example.francescovalela.trkr.data.local;

import android.support.annotation.NonNull;

import com.example.francescovalela.trkr.logExpense.models.MethodOfPaymentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by Francis on 2017-02-23.
 */

public class MethodOfPaymentTypeRepository implements MethodOfPaymentTypeDataSource {

    private static MethodOfPaymentTypeRepository INSTANCE = null;

    private final MethodOfPaymentTypeDataSource mMethodOfPaymentTypeLocalDataSource;

    Map<String, MethodOfPaymentType> typeMap;

    private MethodOfPaymentTypeRepository(@NonNull MethodOfPaymentTypeDataSource methodOfPaymentTypeLocalDataSource) {
        mMethodOfPaymentTypeLocalDataSource = checkNotNull(methodOfPaymentTypeLocalDataSource);
    }


    // Returns the single instance of this class, creating it if necessary.
    public static MethodOfPaymentTypeRepository getInstance(@NonNull MethodOfPaymentTypeDataSource methodOfPaymentTypeDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new MethodOfPaymentTypeRepository(methodOfPaymentTypeDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getMethodOfPaymentTypes(@NonNull final LoadMethodOfPaymentTypesCallback callback) {
        mMethodOfPaymentTypeLocalDataSource.getMethodOfPaymentTypes(new LoadMethodOfPaymentTypesCallback() {
            @Override
            public void onMethodOfPaymentTypesLoaded(List<MethodOfPaymentType> methodOfPaymentTypes) {
                callback.onMethodOfPaymentTypesLoaded(new ArrayList<>(typeMap.values()));
            }

            @Override
            public void onDataNotAvailable() {
                System.out.print("No data source connected");
            }

        });

    }

    @Override
    public void getMethodOfPaymentType(@NonNull final String typeId, @NonNull final GetMethodOfPaymentTypeCallback callback) {
        checkNotNull(typeId);
        checkNotNull(callback);
        mMethodOfPaymentTypeLocalDataSource.getMethodOfPaymentType(typeId, new GetMethodOfPaymentTypeCallback() {
            @Override
            public void onMethodOfPaymentTypeLoaded(MethodOfPaymentType methodOfPaymentType) {
                typeMap.put(String.valueOf(methodOfPaymentType.getId()), methodOfPaymentType);
                callback.onMethodOfPaymentTypeLoaded(methodOfPaymentType);
            }

            @Override
            public void onDataNotAvailable() {
                System.out.print("No data source connected");
            }
        });

    }

    @Override
    public void saveMethodOfPaymentType(@NonNull MethodOfPaymentType type) {
        checkNotNull(type);
        mMethodOfPaymentTypeLocalDataSource.saveMethodOfPaymentType(type);
    }

    @Override
    public void deleteAllMethodOfPaymentTypes() {
        mMethodOfPaymentTypeLocalDataSource.deleteAllMethodOfPaymentTypes();
    }

    @Override
    public void deleteMethodOfPaymentType(@NonNull String typeId) {
        mMethodOfPaymentTypeLocalDataSource.deleteMethodOfPaymentType(typeId);
    }

    private void refreshLocalDataSource(List<MethodOfPaymentType> types) {
        mMethodOfPaymentTypeLocalDataSource.deleteAllMethodOfPaymentTypes();
        for (MethodOfPaymentType type : types) {
            mMethodOfPaymentTypeLocalDataSource.saveMethodOfPaymentType(type);
        }
    }
}
