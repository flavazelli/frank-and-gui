package com.example.francescovalela.trkr.ui.analytics;

import com.example.francescovalela.trkr.data.local.CategoryDataSource;
import com.example.francescovalela.trkr.data.local.ExpenseDataSource;
import com.example.francescovalela.trkr.data.local.MethodOfPaymentDataSource;
import com.example.francescovalela.trkr.logExpense.models.Category;
import com.example.francescovalela.trkr.logExpense.models.Expense;
import com.example.francescovalela.trkr.logExpense.models.MethodOfPayment;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by francescovalela on 2017-02-28.
 */

public class AnalyticsPresenter implements AnalyticsContract.Presenter {

    private final ExpenseDataSource mExpenseRepository;

    private final CategoryDataSource mCategoryRepository;

    private final MethodOfPaymentDataSource mMethodOfPaymentRepository;

    private final AnalyticsContract.View mAnalyticsView;

    public AnalyticsPresenter(CategoryDataSource categoryRepository, MethodOfPaymentDataSource methodOfPaymentRepository, AnalyticsContract.View analyticsView, ExpenseDataSource expenseRepository) {
        mCategoryRepository = checkNotNull(categoryRepository, "categoryRepository cannot be null!");
        mMethodOfPaymentRepository = checkNotNull(methodOfPaymentRepository, "methodOfPaymentRepository cannot be null!");
        mAnalyticsView = checkNotNull(analyticsView, "analyticsView cannot be null!");
        mExpenseRepository = checkNotNull(expenseRepository, "expenseRepository cannot be null!");

        mAnalyticsView.setPresenter(this);
    }

    @Override
    public void loadCategories() {
        mCategoryRepository.getCategories(new CategoryDataSource.LoadCategoryCallback() {
            @Override
            public void onCategoriesLoaded(List<Category> categories) {
                List<Category> categoriesToShow = new ArrayList<Category>();

                for (Category category : categories) {
                    categoriesToShow.add(category);

                    mAnalyticsView.getCategoriesData(categoriesToShow);
                }
            }
            @Override
            public void onDataNotAvailable() {
                //todo create method
            }
        });
    }

    @Override
    public void loadMethodOfPayments() {
        mMethodOfPaymentRepository.getMethodOfPayments(new MethodOfPaymentDataSource.LoadMethodOfPaymentsCallback() {
            @Override
            public void onMethodOfPaymentsLoaded(List<MethodOfPayment> methodOfPayments) {
                List<MethodOfPayment> methodOfPaymentToShow = new ArrayList<MethodOfPayment>();

                for (MethodOfPayment methodOfPayment : methodOfPayments) {
                    methodOfPaymentToShow.add(methodOfPayment);
                }

                mAnalyticsView.getMethodOfPaymentsData(methodOfPaymentToShow);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void loadExpenses() {
        mExpenseRepository.getExpenses(new ExpenseDataSource.LoadExpenseCallback() {

            @Override
            public void onExpensesLoaded(List<Expense> expenses) {
                List<Expense> expensesToShow = new ArrayList<Expense>();

                for (Expense expense : expenses) {
                    expensesToShow.add(expense);
                }

                mAnalyticsView.getExpensesData(expensesToShow);
            }



            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void start() {

    }
}
