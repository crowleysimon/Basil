package com.crowleysimon.basil.repository;

import android.support.annotation.NonNull;

import com.crowleysimon.basil.model.Recipe;

public interface RecipeRepository {
    boolean storeRecipe(@NonNull Recipe recipe);
    Recipe getRecipe();
}
