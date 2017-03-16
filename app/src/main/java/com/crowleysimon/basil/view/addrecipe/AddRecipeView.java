package com.crowleysimon.basil.view.addrecipe;

import android.support.annotation.NonNull;

public interface AddRecipeView {
    void showUrlIsRequired();
    void showUrlError();
    void showSuccess();
    void showStorageError();
    void clearErrorState();

    String getUrl();

    void saveRecipe();

    void getRecipeFromUrl(@NonNull String url);
}
