package com.example.francescovalela.trkr.ui.analytics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.francescovalela.trkr.R;
import com.example.francescovalela.trkr.data.local.CategoryRepository;
import com.example.francescovalela.trkr.data.local.ExpenseRepository;
import com.example.francescovalela.trkr.data.local.MethodOfPaymentRepository;
import com.example.francescovalela.trkr.data.local.category.CategoryLocalDataSource;
import com.example.francescovalela.trkr.data.local.expense.ExpenseLocalDataSource;
import com.example.francescovalela.trkr.data.local.methodofpayment.MethodOfPaymentLocalDataSource;

/**
 * Created by francescovalela on 2017-02-28.
 */

public class AnalyticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        //Fragment Manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment mAnalyticsFragment = new AnalyticsFragment();
        fragmentTransaction.add(R.id.fragmentPlaceholderAnalytics, mAnalyticsFragment).commit();

        ExpenseRepository mExpenseRepository = ExpenseRepository.getInstance(ExpenseLocalDataSource.getInstance(getApplicationContext()));

        CategoryRepository mCategoryRepository = CategoryRepository.getInstance(CategoryLocalDataSource.getInstance(getApplicationContext()));

        MethodOfPaymentRepository mMethodOfPaymentRepository = MethodOfPaymentRepository.getInstance(MethodOfPaymentLocalDataSource.getInstance(getApplicationContext()));

        AnalyticsPresenter mAnalyticsPresenter = new AnalyticsPresenter(mCategoryRepository, mMethodOfPaymentRepository, (AnalyticsContract.View) mAnalyticsFragment, mExpenseRepository);
    }
}
