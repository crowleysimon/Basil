package com.crowleysimon.basil.view.recipelist;

import android.support.annotation.Nullable;

import com.crowleysimon.basil.data.model.Recipe;

import java.util.List;

public interface RecipeListView {
    void displayRecipes(@Nullable List<Recipe> recipes);
}
