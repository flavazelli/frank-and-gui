package com.example.francescovalela.trkr.ui.addExpense;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.*;
import android.widget.DatePicker;

//Fragment to display date picker
public class AddExpenseDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    TheListener listener;

    //interface to pass values from fragment to activity
    interface TheListener{
        void returnDate(long date);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        listener = (TheListener) getActivity();

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onDateSet(DatePicker view, int year, int month, int day) {

        Calendar c = Calendar.getInstance();

        c.set(year, month, day);

        if (listener != null)
        {
           listener.returnDate(c.getTimeInMillis());
        }
    }

}
