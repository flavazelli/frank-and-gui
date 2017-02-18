package com.example.francescovalela.trkr.data.local;

import android.support.annotation.NonNull;

import com.example.francescovalela.trkr.logExpense.models.Category;

import java.util.List;

// Main entry point for accessing category data
public interface CategoryDataSource {

    //load category data into a list & checks if data is available
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

    void deleteAllCategories();

    void deleteCategory(@NonNull String categoryId);
}
