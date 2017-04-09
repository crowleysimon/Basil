package com.crowleysimon.basil.view.addrecipe;

import android.support.annotation.NonNull;

import com.schinizer.rxunfurl.model.PreviewData;

public interface AddRecipeView {
    void showUrlIsRequired();

    void showTitleIsRequired();

    void showDescriptionIsRequired();

    void showUrlError();

    void showSuccess();

    void showStorageError();

    void clearErrorState();

    void isUrlFormatted(boolean formatted);

    String getUrl();

    String getTitle();

    String getDescription();

    PreviewData getData();

    int getRating();

    void saveRecipe();

    void getRecipeFromUrl(@NonNull String url);
}
