package com.crowleysimon.basil.ui.addrecipe;

import android.support.annotation.NonNull;
import android.util.Patterns;

import com.crowleysimon.basil.model.Recipe;
import com.crowleysimon.basil.repository.RecipeRepository;
import com.crowleysimon.basil.util.RecipeUtil;
import com.schinizer.rxunfurl.model.PreviewData;

public class AddRecipePresenterImpl implements AddRecipePresenter {

    @NonNull
    private RecipeRepository repository;

    private AddRecipeView view;

    private String url;

    public AddRecipePresenterImpl(@NonNull RecipeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void setView(AddRecipeView view) {
        this.view = view;
    }

    @Override
    public void saveRecipe() {
        view.clearErrorState();
        url = view.getUrl();
        if (url != null && !url.isEmpty()) {
            if (isWebUrl(url)) {
                view.getRecipeFromUrl(url);
            } else {
                view.showUrlError();
            }
        } else {
            view.showUrlIsRequired();
        }
    }

    private boolean isWebUrl(@NonNull String url) {
        if (url.startsWith("www")) {
            this.url = formattedUrl(url);
        }
        return Patterns.WEB_URL.matcher(url).matches();
    }

    private String formattedUrl(@NonNull String url) {
        return "http://" + url;
    }

    @Override
    public void generateRecipe(@NonNull PreviewData previewData) {
        Recipe recipe = RecipeUtil.createRecipeFromPreview(previewData);
        if (repository.storeRecipe(recipe)) {
            view.showSuccess();
        } else {
            view.showStorageError();
        }
    }
}
