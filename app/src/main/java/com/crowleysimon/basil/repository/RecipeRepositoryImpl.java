package com.crowleysimon.basil.repository;

import android.support.annotation.NonNull;

import com.crowleysimon.basil.model.Recipe;

import okhttp3.OkHttpClient;

public class RecipeRepositoryImpl implements RecipeRepository {

    OkHttpClient okHttpClient;

    public RecipeRepositoryImpl(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Override
    public boolean storeRecipe(@NonNull Recipe recipe) {
        return true;
    }

    @Override
    public Recipe getRecipe() {
        return null;
    }
}
