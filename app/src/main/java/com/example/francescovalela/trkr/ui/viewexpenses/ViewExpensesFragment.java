package com.example.francescovalela.trkr.ui.viewexpenses;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.francescovalela.trkr.R;
import com.example.francescovalela.trkr.logExpense.models.Expense;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by flavazelli on 2017-02-28.
 */

public class ViewExpensesFragment extends Fragment
    implements ViewExpensesContract.View {

    private RecyclerView mExpenseRecyclerView;

    ViewExpensesContract.Presenter mPresenter;
    private ExpensesAdapter mExpenseAdapter;

    public ViewExpensesFragment() {
        // Requires empty public constructor
    }

    public static ViewExpensesFragment newInstance() {

        return new ViewExpensesFragment();
    }


    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    };

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_expense_list, container, false);

        mExpenseRecyclerView = (RecyclerView) view
                .findViewById(R.id.expense_recycler_view);
        mExpenseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
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
    public void showExpenses(List<Expense> expenses){
        updateUI(expenses);
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

    private void updateUI(List<Expense> expenses) {
        mExpenseAdapter = new ExpensesAdapter(expenses);
        mExpenseRecyclerView.setAdapter(mExpenseAdapter);
    }

    private class ExpenseHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Expense mExpense;

        private TextView mTitleTextView;
        private TextView mCostTextView;

        public ExpenseHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_expense_title);
            mCostTextView = (TextView) itemView.findViewById(R.id.list_item_expense_cost);
        }

        public void bindExpense(Expense expense) {
            mExpense = expense;
            mTitleTextView.setText(expense.getName());
            mCostTextView.setText("$" + expense.getCost());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),
                    mExpense.getName() + " clicked!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private class ExpensesAdapter extends RecyclerView.Adapter<ExpenseHolder> {

        private List<Expense> mExpenses;

        private ExpensesAdapter(List<Expense> expenses) {
            mExpenses = expenses;
        }

        @Override
        public ExpenseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_expense, parent, false);
            return new ExpenseHolder(view);
        }

        @Override
        public void onBindViewHolder(ExpenseHolder holder, int position) {
            Expense expense = mExpenses.get(position);

            holder.bindExpense(expense);
        }

        @Override
        public int getItemCount() {
            return mExpenses.size();
        }
    }
}
