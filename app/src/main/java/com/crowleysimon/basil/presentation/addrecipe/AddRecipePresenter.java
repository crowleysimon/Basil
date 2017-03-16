package com.crowleysimon.basil.presentation.addrecipe;

import android.support.annotation.NonNull;

import com.crowleysimon.basil.LifeCyclePresenter;
import com.crowleysimon.basil.view.addrecipe.AddRecipeView;
import com.schinizer.rxunfurl.model.PreviewData;

public interface AddRecipePresenter extends LifeCyclePresenter {
    void setView(AddRecipeView view);
    void saveRecipe();
    void generateRecipe(@NonNull PreviewData previewData);
}