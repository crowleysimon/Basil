package com.crowleysimon.basil.injection;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.crowleysimon.basil.repository.RecipeRepository;
import com.crowleysimon.basil.repository.RecipeRepositoryImpl;
import com.crowleysimon.basil.ui.addrecipe.AddRecipePresenter;
import com.crowleysimon.basil.ui.addrecipe.AddRecipePresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class ApplicationModule {

    @NonNull
    private Application application;

    public ApplicationModule(@NonNull Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    public RecipeRepository provideRecipeRepository() {
        return new RecipeRepositoryImpl();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    public AddRecipePresenter provideAddRecipePresenter() {
        return new AddRecipePresenterImpl();
    }
}
