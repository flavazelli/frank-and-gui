package com.example.francescovalela.trkr.ui.addExpense;

import com.example.francescovalela.trkr.data.local.CategoryDataSource;
import com.example.francescovalela.trkr.data.local.CategoryRepository;
import com.example.francescovalela.trkr.data.local.ExpenseDataSource;
import com.example.francescovalela.trkr.data.local.ExpenseRepository;
import com.example.francescovalela.trkr.data.local.category.CategoryContract;
import com.example.francescovalela.trkr.data.local.expense.ExpenseContract;
import com.example.francescovalela.trkr.logExpense.models.Category;
import com.example.francescovalela.trkr.logExpense.models.Expense;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

/**
 * Created by flavazelli on 2017-02-28.
 */

public class AddExpensePresenter implements AddExpenseContract.Presenter {


    private final ExpenseDataSource mExpenseRepository;

    private final CategoryDataSource mCategoryRepository;

    private final AddExpenseContract.View mExpenseView;

    public AddExpensePresenter(ExpenseRepository expenseRepository, CategoryRepository categoryRepository, AddExpenseContract.View expenseView ) {
        mExpenseRepository = checkNotNull(expenseRepository, "expenseRepository cannot be null");
        mCategoryRepository = checkNotNull(categoryRepository, "categoryRespository cannot be null");
        mExpenseView = checkNotNull(expenseView, "tasksView cannot be null!");

        mExpenseView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    public void addExpense(int expenseId, long date, String name, double cost, double locationLat, double locationLong, int methodOfPaymentId, int categoryId) {
        Expense expense = new Expense(expenseId, date, name, cost, locationLat, locationLong, methodOfPaymentId, categoryId);

        mExpenseRepository.saveExpense(expense);
    }

    public void loadCategoriesInSpinner() {

        mCategoryRepository.getCategories(new CategoryDataSource.LoadCategoryCallback() {
            @Override
            public void onCategoriesLoaded(List<Category> categories) {
                List<Category> categoriesToShow = new ArrayList<Category>();

                for (Category category: categories) {
                    categoriesToShow.add(category);
                }

                mExpenseView.loadSpinnerData(categoriesToShow);

            }

            @Override
            public void onDataNotAvailable() {

            }


        });
    }

    public String getCategoryColumnName() {
        return CategoryContract.CategoryEntry.COLUMN_NAME_NAME;
    }

}
