package com.example.francescovalela.trkr.ui.expenseDetail;

import com.example.francescovalela.trkr.BaseView;
import com.example.francescovalela.trkr.BasePresenter;

import static android.R.id.edit;

/**
 * Created by francescovalela on 2017-02-28.
 * Contract that sets up interaction between view and presenter.
 * For viewing a single expense log in detail
 */

public interface ExpenseDetailContract {

    interface View extends BaseView<Presenter> {

        void showDetails();

    }

    interface Presenter extends BasePresenter {

        void editExpense();

    }
}
