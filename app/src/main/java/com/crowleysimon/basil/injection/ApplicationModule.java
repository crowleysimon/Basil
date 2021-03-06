package com.crowleysimon.basil.injection;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.crowleysimon.basil.BuildConfig;
import com.crowleysimon.basil.data.repository.RecipeRepository;
import com.crowleysimon.basil.data.repository.RecipeRepositoryImpl;
import com.crowleysimon.basil.presentation.addrecipe.AddRecipePresenter;
import com.crowleysimon.basil.presentation.addrecipe.AddRecipePresenterImpl;
import com.crowleysimon.basil.presentation.recipelist.RecipeListPresenter;
import com.crowleysimon.basil.presentation.recipelist.RecipeListPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
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
    public RecipeRepository provideRecipeRepository(Realm realm) {
        return new RecipeRepositoryImpl(realm);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    public AddRecipePresenter provideAddRecipePresenter(RecipeRepository recipeRepository) {
        return new AddRecipePresenterImpl(recipeRepository);
    }

    @Provides
    public RecipeListPresenter provideRecipeListPresenter(RecipeRepository recipeRepository) {
        return new RecipeListPresenterImpl(recipeRepository);
    }

    @Provides
    static RealmConfiguration provideRealmConfiguration() {
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
        if(BuildConfig.DEBUG) { builder = builder.deleteRealmIfMigrationNeeded(); }
        return builder.build();
    }

    @Provides
    static Realm provideRealm(RealmConfiguration realmConfiguration) {
        return Realm.getInstance(realmConfiguration);
    }
}
