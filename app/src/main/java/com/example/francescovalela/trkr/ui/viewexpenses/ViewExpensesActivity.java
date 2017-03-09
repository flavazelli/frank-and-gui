package com.example.francescovalela.trkr.ui.viewexpenses;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import com.example.francescovalela.trkr.R;

import static android.content.ContentValues.TAG;

/**
 * Created by flavazelli on 2017-02-28.
 */

public class ViewExpensesActivity extends Activity {

    private ViewExpensesPresenter mViewExpensesPresenter;
    FragmentManager fragmentManager = getFragmentManager();
    ViewExpensesFragment viewExpensesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        ViewExpensesFragment viewExpensesFragment =  new ViewExpensesFragment();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, viewExpensesFragment)
                .commit();

        // Create the presenter
        mViewExpensesPresenter = new ViewExpensesPresenter(viewExpensesFragment, getApplicationContext());
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
