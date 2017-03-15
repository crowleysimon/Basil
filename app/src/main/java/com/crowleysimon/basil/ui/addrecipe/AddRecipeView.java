package com.crowleysimon.basil.ui.addrecipe;

import android.support.annotation.NonNull;

public interface AddRecipeView {
    void showUrlIsRequired();
    void showUrlError();
    void showSuccess();
    void showStorageError();
    void clearErrorState();

    String getUrl();

    void getRecipeFromUrl(@NonNull String url);
}
