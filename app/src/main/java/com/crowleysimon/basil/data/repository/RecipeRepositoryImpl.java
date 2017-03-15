package com.crowleysimon.basil.data.repository;

import android.support.annotation.NonNull;

import com.crowleysimon.basil.data.model.Recipe;

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
