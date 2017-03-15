package com.crowleysimon.basil.ui.addrecipe;

public interface AddRecipeView {
    void showUrlIsRequired();
    void showUrlError();
    void showSuccess();

    String getUrl();
}
