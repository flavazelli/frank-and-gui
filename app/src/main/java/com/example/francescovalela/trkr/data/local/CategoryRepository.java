package com.example.francescovalela.trkr.data.local;

import android.support.annotation.NonNull;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import android.support.annotation.Nullable;

import com.example.francescovalela.trkr.data.local.CategoryDataSource;
import com.example.francescovalela.trkr.logExpense.models.Category;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by francescovalela on 2017-02-16.
 *
 * Loads categories from data source (local, for now) into a cache.
 */

public class CategoryRepository implements CategoryDataSource {

    private static CategoryRepository INSTANCE = null;

    private final CategoryDataSource mCategoryLocalDataSource;

    Map<String, Category> mCachedCategory;

    // Marks the cache as invalid, to force an update the next time data is requested
    boolean mCacheIsDirty = false;

    private CategoryRepository(@NonNull CategoryDataSource categoryLocalDataSource) {
        mCategoryLocalDataSource = checkNotNull(categoryLocalDataSource);
    }


    /** Returns the single instance of this class, creating it if necessary.
     *
     * @param categoryLocalDataSource  the device storage data source.
     * @return the {@link CategoryRepository} instance
     * */
    public static CategoryRepository getInstance(@NonNull CategoryDataSource categoryLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new CategoryRepository(categoryLocalDataSource);
        }
        return INSTANCE;
    }


    /**
     * Used to force {@link #getInstance(CategoryDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Gets categories from cache, local data source (SQLite)
     * <p>
     * Note: {@link LoadCategoryCallback#onDataNotAvailable()} is fired if all data sources fail to
     * get the data.
     */
    public void getCategories(@NonNull final LoadCategoryCallback callback) {
        checkNotNull(callback);

        // Respond immediately with cache if available and not dirty
        if (mCachedCategory != null && !mCacheIsDirty) {
            callback.onCategoriesLoaded(new ArrayList<>(mCachedCategory.values()));
            return;
        }

        // Query the local storage if available. If not, query the network.
        mCategoryLocalDataSource.getCategories(new LoadCategoryCallback() {
            @Override
            public void onCategoriesLoaded(List<Category> categories) {
                refreshCache(categories);
                callback.onCategoriesLoaded(new ArrayList<>(mCachedCategory.values()));
            }

            @Override
            // would put getCategoriesRemoteDataSource(callback) if connected to remote db
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void getCategory(@NonNull String categoryId, @NonNull final GetCategoryCallback callback) {
        checkNotNull(categoryId);
        checkNotNull(callback);

        Category cachedCategory = getCategoryWithId(categoryId);

        // Respond immediately with cache if available
        if (cachedCategory != null) {
            callback.onCategoryLoaded(cachedCategory);
            return;
        }

        // Load from server/persisted if needed.

        // Is the category in the local data source? If not, query the network.
        mCategoryLocalDataSource.getCategory(categoryId, new GetCategoryCallback() {
            @Override
            public void onCategoryLoaded(Category category) {
                // Do in memory cache update to keep the app UI up to date
                if (mCachedCategory == null) {
                    mCachedCategory = new LinkedHashMap<>();
                }
                mCachedCategory.put(String.valueOf(category.getId()), category); //TODO Again, ID is string in the example. Do we change to string as well?
                callback.onCategoryLoaded(category);
            }

            @Override
            // would put getCategoriesRemoteDataSource(callback) if connected to remote db
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void saveCategory(@NonNull Category category) {
        checkNotNull(category);
        mCategoryLocalDataSource.saveCategory(category);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedCategory == null) {
            mCachedCategory = new LinkedHashMap<>();
        }
        mCachedCategory.put(category.getName(), category);
    }

    @Override
    public void refreshCategories() {
        mCacheIsDirty = true;
    }

    @Override
    public void deleteAllCategories() {
        mCategoryLocalDataSource.deleteAllCategories();

        if (mCachedCategory == null) {
            mCachedCategory = new LinkedHashMap<>();
        }
        mCachedCategory.clear();
    }

    @Override
    public void deleteCategory(@NonNull String categoryId) {

        mCategoryLocalDataSource.deleteCategory(checkNotNull(categoryId));

        mCachedCategory.remove(categoryId);
    }

    private void refreshCache(List<Category> categories) {
        if (mCachedCategory == null) {
            mCachedCategory = new LinkedHashMap<>();
        }
        mCachedCategory.clear();
        for (Category category : categories) {
            mCachedCategory.put(String.valueOf(category.getId()), category); //TODO ID is string in the example. Do we change to string as well?
        }
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(List<Category> categories) {
        mCategoryLocalDataSource.deleteAllCategories();
        for (Category category : categories) {
            mCategoryLocalDataSource.saveCategory(category);
        }
    }

    @Nullable
    private Category getCategoryWithId(@NonNull String id) { //TODO Again, ID is string in the example. Do we change to string as well?
        checkNotNull(id);
        if (mCachedCategory == null || mCachedCategory.isEmpty()) {
            return null;
        } else {
            return mCachedCategory.get(id);
        }
    }
    //TODO check if we need to "complete" a category
//
//    @Override
//    public void completeCategory(@NonNull Category category) {
//        checkNotNull(category;
//        mCategoryLocalDataSource.completeCategory(category;
//
//        Category completedCategory = new Category(category.getName(), category.getId());
//
//        // Do in memory cache update to keep the app UI up to date
//        if (mCachedCategory == null) {
//            mCachedCategory = new LinkedHashMap<>();
//        }
//        mCachedCategory.put(String.valueOf(category.getId()), completedCategory);//TODO Again, ID is string in the example. Do we change to string as well?
//    }
//
//    @Override
//    public void completeCategory(@NonNull String categoryId) {
//        checkNotNull(categoryId);
//        completeCategory(getCategoryIdWithId(categoryId));
//    }

    // TODO Check if we need "activate" category
//    @Override
//    public void activateCategory(@NonNull Category category) {
//
//    }
//
//    @Override
//    public void activateCategory(@NonNull String categoryId) {
//
//    }

    //TODO will need if we have completed category
//    @Override
//    public void clearCompletedCategories() {
//
//    }



/*  /**
     * only use if adding remote data source
     * @param callback
     //
  private void getTasksFromRemoteDataSource(@NonNull final LoadTasksCallback callback) {
       mTasksRemoteDataSource.getTasks(new LoadTasksCallback() {
          @Override
          public void onTasksLoaded(List<Task> tasks) {
              refreshCache(tasks);
              refreshLocalDataSource(tasks);
              callback.onTasksLoaded(new ArrayList<>(mCachedTasks.values()));
          }

          @Override
          public void onDataNotAvailable() {
              callback.onDataNotAvailable();
          }
      });
    }
*/
}
