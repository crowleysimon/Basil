package com.crowleysimon.basil.presentation.addrecipe;

import android.support.annotation.NonNull;

import com.crowleysimon.basil.data.model.Recipe;
import com.crowleysimon.basil.data.repository.RecipeRepository;
import com.crowleysimon.basil.presentation.ViewNotFoundException;
import com.crowleysimon.basil.util.RecipeUtil;
import com.crowleysimon.basil.view.addrecipe.AddRecipeView;
import com.schinizer.rxunfurl.model.PreviewData;

import java.net.MalformedURLException;
import java.net.URL;

public class AddRecipePresenterImpl implements AddRecipePresenter {

    @NonNull
    private RecipeRepository repository;

    private AddRecipeView view;

    private String url;

    public AddRecipePresenterImpl(@NonNull RecipeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setView(AddRecipeView view) {
        this.view = view;
    }

    @Override
    public void saveRecipe() {
        if (view == null) {
            throw new ViewNotFoundException();
        }
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

    public boolean isWebUrl(@NonNull String url) {
        if (url.startsWith("www")) {
            this.url = formattedUrl(url);
        }
        try {
            URL url1 = new URL(url);
        } catch (MalformedURLException e) {
            return false;
        }
        return true;
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
