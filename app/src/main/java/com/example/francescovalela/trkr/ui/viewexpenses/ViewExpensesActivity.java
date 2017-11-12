package com.example.francescovalela.trkr.ui.viewexpenses;

import com.example.francescovalela.trkr.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


/**
 * Created by flavazelli on 2017-02-28.
 */

public class ViewExpensesActivity extends FragmentActivity {

    private ViewExpensesPresenter mViewExpensesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        ViewExpensesFragment fragment = (ViewExpensesFragment) fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new ViewExpensesFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
        // Create the presenter
        mViewExpensesPresenter = new ViewExpensesPresenter(fragment, getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
