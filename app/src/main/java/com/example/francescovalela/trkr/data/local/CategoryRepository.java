package com.example.francescovalela.trkr.data.local;

import android.support.annotation.NonNull;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;

import com.example.francescovalela.trkr.logExpense.models.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Mediator between DataSource and domain layer
// Should never call DataSource from domain layer

public class CategoryRepository implements CategoryDataSource {

    private static CategoryRepository INSTANCE = null;

    private final CategoryDataSource mCategoryLocalDataSource;

    Map<String, Category> categoryMap;

    // Makes sure to only instantiate once
    private CategoryRepository(@NonNull CategoryDataSource categoryLocalDataSource) {
        mCategoryLocalDataSource = checkNotNull(categoryLocalDataSource);
    }


    // Returns the single instance of this class, creating it if necessary.
    public static CategoryRepository getInstance(@NonNull CategoryDataSource categoryLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new CategoryRepository(categoryLocalDataSource);
        }
        return INSTANCE;
    }


    // Used to force {@link #getInstance(CategoryDataSource)} to create a new instance
    public static void destroyInstance() {
        INSTANCE = null;
    }

    // Gets categories from local data source (SQLite db)
    // onDataNotAvailable() is fired if all data sources (currently just SQLite) fail to get the data
    @Override
    public void getCategories(@NonNull final LoadCategoryCallback callback) {
        checkNotNull(callback);

        // Queries the local storage
        // calls method and sends arraylist with values
        mCategoryLocalDataSource.getCategories(new LoadCategoryCallback() {
            @Override
            public void onCategoriesLoaded(List<Category> categories) {
                callback.onCategoriesLoaded(new ArrayList<>(categoryMap.values()));
            }

            @Override
            // Would put getCategoriesRemoteDataSource(callback) if connected to remote db
            public void onDataNotAvailable() {
                System.out.print("No data source connected");
            }
        });
    }

    // Gets single category from local data source (SQLite db) and puts it into the map.
    // onDataNotAvailable() is fired if all data sources (currently just SQLite) fail to get the data
    @Override
    public void getCategory(@NonNull String categoryId, @NonNull final GetCategoryCallback callback) {
        checkNotNull(categoryId);
        checkNotNull(callback);

        // Queries the local storage
        mCategoryLocalDataSource.getCategory(categoryId, new GetCategoryCallback() {
            @Override
            public void onCategoryLoaded(Category category) {

                categoryMap.put(String.valueOf(category.getId()), category);
                callback.onCategoryLoaded(category);
            }

            @Override
            // Would put getCategoriesRemoteDataSource(callback) if connected to remote db
            public void onDataNotAvailable() {
                System.out.print("No data source connected");
            }
        });
    }

    // Saves category to db
    @Override
    public void saveCategory(@NonNull Category category) {
        checkNotNull(category);
        mCategoryLocalDataSource.saveCategory(category);
    }

    @Override
    public void deleteAllCategories() {
        mCategoryLocalDataSource.deleteAllCategories();
    }

    @Override
    public void deleteCategory(@NonNull String categoryId) {
        mCategoryLocalDataSource.deleteCategory(checkNotNull(categoryId));
    }

}
