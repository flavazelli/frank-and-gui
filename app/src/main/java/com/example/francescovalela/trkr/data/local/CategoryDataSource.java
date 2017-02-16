package com.example.francescovalela.trkr.data.local;

import android.support.annotation.NonNull;

import com.example.francescovalela.trkr.logExpense.models.Category;

import java.util.List;

/**
 * Created by francescovalela on 2017-02-14.
 *
 * Main entry point for accessing tasks data.
 *
 * For simplicity, only getTasks() and getTask() have callbacks. Consider adding callbacks to other
 * methods to inform the user of network/database errors or successful operations.
 * For example, when a new task is created, it's synchronously stored in cache but usually every
 * operation on database or network should be executed in a different thread.
 */


// Main entry point for accessing category data
public interface CategoryDataSource {

    //load category data into a list & checks if data isn't available
    interface LoadCategoryCallback {

        void onCategoriesLoaded(List<Category> categories);
        void onDataNotAvailable();
    }

    interface GetCategoryCallback {

        void onCategoryLoaded(Category category);
        void onDataNotAvailable();
    }

    void getCategories(@NonNull LoadCategoryCallback callback);

    void getCategory(@NonNull String categoryId, @NonNull GetCategoryCallback callback);

    void saveCategory(@NonNull Category category);

    // TODO Do we need these?
//    void completeCategory(@NonNull Category category);
//
//    void completeCategory(@NonNull String categoryId);
//
//    void activateCategory(@NonNull Category category);
//
//    void activateCategory(@NonNull String categoryId);
//
//    void clearCompletedCategories();

    void refreshCategories();

    void deleteAllCategories();

    void deleteCategory(@NonNull String categoryId);
}
