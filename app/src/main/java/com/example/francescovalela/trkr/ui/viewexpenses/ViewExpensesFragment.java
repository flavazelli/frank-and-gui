package com.example.francescovalela.trkr.ui.viewexpenses;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.francescovalela.trkr.logExpense.models.Expense;

import java.util.List;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by flavazelli on 2017-02-28.
 */

public class ViewExpensesFragment extends ListFragment
    implements ViewExpensesContract.View {

    ViewExpensesContract.Presenter mPresenter;

    @Override
    public void onCreate (Bundle savedInstanceState) {

    };

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    };

    @Override
    public void onPause () {

    };

    @Override
    public void onResume() {
        mPresenter.start();
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }


    @Override
    public void showExpenses(List<Expense> expenses) {

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
}
