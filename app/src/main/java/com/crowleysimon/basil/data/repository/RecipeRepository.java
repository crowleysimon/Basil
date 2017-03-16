package com.crowleysimon.basil.data.repository;

import android.support.annotation.NonNull;

import com.crowleysimon.basil.data.model.Recipe;

import java.util.List;

public interface RecipeRepository {
    boolean storeRecipe(@NonNull Recipe recipe);

    List<Recipe> getRecipes();
}
