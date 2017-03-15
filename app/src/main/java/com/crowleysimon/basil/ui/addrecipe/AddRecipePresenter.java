package com.crowleysimon.basil.ui.addrecipe;

import com.crowleysimon.basil.LifeCyclePresenter;

public interface AddRecipePresenter extends LifeCyclePresenter {
    void setView(AddRecipeView view);
    void saveRecipe();
}
