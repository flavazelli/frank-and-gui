package com.example.francescovalela.trkr;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import  com.example.francescovalela.trkr.ui.addExpense.AddExpenseActivity;
import com.example.francescovalela.trkr.ui.analytics.AnalyticsActivity;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.common.Util;
import com.github.mikephil.charting.utils.Utils;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;


public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize Facebook Stetho
        Stetho.initializeWithDefaults(this);

        //pulsator
        PulsatorLayout pulsator = (PulsatorLayout) findViewById(R.id.pulsator);
        pulsator.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logExpense(View view) {

//        Intent LogExpenseScreenIntent = new Intent(this,AnalyticsActivity.class);
        Intent LogExpenseScreenIntent = new Intent(this,AddExpenseActivity.class);

        startActivity(LogExpenseScreenIntent);

    }

}
