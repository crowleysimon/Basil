package com.crowleysimon.basil.presentation.recipelist;

import android.support.annotation.NonNull;

import com.crowleysimon.basil.view.recipelist.RecipeListView;

public interface RecipeListPresenter {
    void setView(RecipeListView view);
    void getListOfRecipes();
    void onRecipeClick(@NonNull String url);
}
