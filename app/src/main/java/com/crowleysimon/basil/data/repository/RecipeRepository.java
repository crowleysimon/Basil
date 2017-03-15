package com.crowleysimon.basil.data.repository;

import android.support.annotation.NonNull;

import com.crowleysimon.basil.data.model.Recipe;

public interface RecipeRepository {
    boolean storeRecipe(@NonNull Recipe recipe);
    Recipe getRecipe();
}
