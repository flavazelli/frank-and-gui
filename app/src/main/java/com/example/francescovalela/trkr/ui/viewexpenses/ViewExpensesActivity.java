package com.example.francescovalela.trkr.ui.viewexpenses;

import com.example.francescovalela.trkr.R;
import android.app.Activity;
import android.os.Bundle;
import android.app.FragmentManager;
import android.util.Log;
import static android.content.ContentValues.TAG;


/**
 * Created by flavazelli on 2017-02-28.
 */

public class ViewExpensesActivity extends Activity {

    private ViewExpensesPresenter mViewExpensesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getFragmentManager();
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
