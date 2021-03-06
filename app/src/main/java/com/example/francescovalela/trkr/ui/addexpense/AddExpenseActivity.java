package com.example.francescovalela.trkr.ui.addExpense;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.francescovalela.trkr.R;
import com.example.francescovalela.trkr.data.local.CategoryRepository;
import com.example.francescovalela.trkr.data.local.ExpenseRepository;
import com.example.francescovalela.trkr.data.local.MethodOfPaymentRepository;
import com.example.francescovalela.trkr.data.local.category.CategoryLocalDataSource;
import com.example.francescovalela.trkr.data.local.expense.ExpenseLocalDataSource;
import com.example.francescovalela.trkr.data.local.methodofpayment.MethodOfPaymentLocalDataSource;

import java.util.Date;

/**
 * Created by flavazelli on 2017-02-28.
 */

public class AddExpenseActivity extends AppCompatActivity implements AddExpenseDateFragment.TheListener, View.OnTouchListener {

    private long date;


    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexpense);

        //hides soft keyboard once clicking outside editText
        findViewById(R.id.addexpense_fragmentPlaceholder).setOnTouchListener(this);

        //Fragment Manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment mAddExpenseFragment = new AddExpenseFragment();
        fragmentTransaction.add(R.id.addexpense_fragmentPlaceholder, mAddExpenseFragment).commit();

        ExpenseRepository mExpenseRepository = ExpenseRepository.getInstance(ExpenseLocalDataSource.getInstance(getApplicationContext()));

        CategoryRepository mCategoryRepository = CategoryRepository.getInstance(CategoryLocalDataSource.getInstance(getApplicationContext()));

        MethodOfPaymentRepository mMethodOfPaymentRepository = MethodOfPaymentRepository.getInstance(MethodOfPaymentLocalDataSource.getInstance(getApplicationContext()));

        AddExpensePresenter mAddExpensePresenter = new AddExpensePresenter(mExpenseRepository, mCategoryRepository, mMethodOfPaymentRepository, (AddExpenseContract.View) mAddExpenseFragment);
    }

    //Takes passed value, sets the value to date and then displays it in textView
    //Used by AddExpenseDateFragment
    //Very buggy with nested fragment. Took it out and put it in activity instead.
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void returnDate(long date) {

        setDate(date);

        Date formattedDate = new Date(date);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String selectedDate = sdf.format(formattedDate.getTime());

        TextView dateTV = (TextView) findViewById(R.id.expense_date_text_view);
        dateTV.setText(selectedDate);
    }

    public void showDatePickerDialog(View v) {

        DialogFragment newFragment = new AddExpenseDateFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    //hides soft keyboard once clicking outside editText
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return false;
    }
}
