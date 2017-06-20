package com.example.francescovalela.trkr;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import  com.example.francescovalela.trkr.ui.addExpense.AddExpenseActivity;
import com.example.francescovalela.trkr.ui.analytics.AnalyticsActivity;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.common.Util;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Stetho.initializeWithDefaults(this);


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
    public static ObjectAnimator pulseAnimation(ImageView target) {

        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(target,
                PropertyValuesHolder.ofFloat("scaleX", 1.1f),
                PropertyValuesHolder.ofFloat("scaleY", 1.1f));
        scaleDown.setDuration(310);
        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);

        return scaleDown;
    }
//    @Override
//    protected void onDraw(Canvas canvas) {
//
//        int w = getMeasuredWidth();
//        int h = getMeasuredHeight();
//        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mCirclePaint.setColor(mColor);
//        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mBackgroundPaint.setColor(Util.adjustAlpha(mColor, 0.4f));
//        //Draw circle
//        canvas.drawCircle(w/2, h/2, MIN_RADIUS_VALUE , mCirclePaint);
//        if (mAnimationOn) {
//            if (mRadius >= MAX_RADIUS_VALUE)
//                mPaintGoBack = true;
//            else if(mRadius <= MIN_RADIUS_VALUE)
//                mPaintGoBack = false;
//            //Draw pulsating shadow
//            canvas.drawCircle(w / 2, h / 2, mRadius, mBackgroundPaint);
//            mRadius = mPaintGoBack ? (mRadius - 0.5f) : (mRadius + 0.5f);
//            invalidate();
//        }
//
//        super.onDraw(canvas);
//    }
//    public void animateButton(boolean animate){
//        if (!animate)
//            mRadius = MIN_RADIUS_VALUE;
//        mAnimationOn = animate;
//        invalidate();
//    }


}
