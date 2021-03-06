package com.crowleysimon.basil.presentation.addrecipe;

import android.support.annotation.NonNull;

import com.crowleysimon.basil.view.addrecipe.AddRecipeView;
import com.schinizer.rxunfurl.model.PreviewData;

public interface AddRecipePresenter {
    void setView(AddRecipeView view);
    void generateRecipe();
    void saveRecipe();
    void isWebUrl(@NonNull String url);
}
