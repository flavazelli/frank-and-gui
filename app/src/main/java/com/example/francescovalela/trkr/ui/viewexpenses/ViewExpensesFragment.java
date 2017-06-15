package com.example.francescovalela.trkr.ui.viewexpenses;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.francescovalela.trkr.R;
import com.example.francescovalela.trkr.logExpense.models.Expense;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by flavazelli on 2017-02-28.
 */

public class ViewExpensesFragment extends ListFragment
    implements ViewExpensesContract.View {

    ViewExpensesContract.Presenter mPresenter;
    ExpensesAdapter expenseAdapter;

    public ViewExpensesFragment() {
        // Requires empty public constructor
    }

    public static ViewExpensesFragment newInstance() {
        return new ViewExpensesFragment();
    }


    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        expenseAdapter = new ExpensesAdapter(new ArrayList<Expense>(0));
        setListAdapter(expenseAdapter);
    };

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    };

    @Override
    public void onPause () {
        super.onPause();
    };

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }


    @Override
    public void showExpenses(List<Expense> expenses) {
        expenseAdapter.replaceData(expenses);
    }

    @Override
    public void showNoExpenses() {

    }

    @Override
    public void showExpenseDetails(String expenseId) {

    }

    @Override
    public void setPresenter(@NonNull ViewExpensesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);

    }

    @Override
    public void showExpenseError() {

    }

    private class ExpensesAdapter extends BaseAdapter {

        private List<Expense> mExpenses;

        public ExpensesAdapter(List<Expense> expenses)
        {
            setList(expenses);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.d(TAG, "GET VIEW:" + convertView.toString());

            //if we weren't given a  view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.fragment_expense_list, null);
            }

            //Configure the view for this expense
            Expense e = getItem(position);
            TextView dateTextView = (TextView) convertView.findViewById(R.id.date);
            TextView costTextView = (TextView) convertView.findViewById(R.id.cost);
            TextView categoryTextView = (TextView) convertView.findViewById(R.id.category);

            Date date = new Date(e.getDate());
            dateTextView.setText(date.toString());
            costTextView.setText(String.valueOf(e.getCost()));
            //TODO: create category methods in expense
            categoryTextView.setText(e.getName());

            return convertView;
        }

        public void replaceData(List<Expense> expenses) {
            setList(expenses);
            this.notifyDataSetChanged();
        }

        private void setList(List<Expense> expenses) {
            mExpenses = checkNotNull(expenses);
        }

        @Override
        public Expense getItem(int i) {
            return mExpenses.get(i);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getCount() {
            return mExpenses.size();
        }



    }
}
