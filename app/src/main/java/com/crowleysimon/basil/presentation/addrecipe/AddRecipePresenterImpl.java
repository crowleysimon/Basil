package com.crowleysimon.basil.presentation.addrecipe;

import android.support.annotation.NonNull;
import android.util.Log;

import com.crowleysimon.basil.data.model.Recipe;
import com.crowleysimon.basil.data.repository.RecipeRepository;
import com.crowleysimon.basil.presentation.ViewNotFoundException;
import com.crowleysimon.basil.util.RecipeUtil;
import com.crowleysimon.basil.view.addrecipe.AddRecipeView;
import com.schinizer.rxunfurl.model.PreviewData;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddRecipePresenterImpl implements AddRecipePresenter {

    private static final String URL_REGEX = "^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$";

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
    public void generateRecipe() {
        if (view == null) {
            throw new ViewNotFoundException();
        }
        view.clearErrorState();
        if (view.getUrl() == null) {
            view.showUrlIsRequired();
            return;
        }
        view.getRecipeFromUrl(view.getUrl());
    }

    @Override
    public void isWebUrl(@NonNull String url) {
        if (view == null) {
            throw new ViewNotFoundException();
        }
        Pattern p = Pattern.compile(URL_REGEX);
        Matcher m = p.matcher(url);
        if (m.find()) {
            view.clearErrorState();
            view.isUrlFormatted(true);
        } else {
            view.showUrlError();
            view.isUrlFormatted(false);
        }
    }

    @Override
    public void saveRecipe() {
        if (view == null) {
            throw new ViewNotFoundException();
        }
        if (view.getData() == null) {
            view.showUrlIsRequired();
            return;
        }
        PreviewData previewData = view.getData();
        if (view.getTitle() == null) {
            view.showTitleIsRequired();
            return;
        }
        previewData.setTitle(view.getTitle());
        if (view.getDescription() == null) {
            view.showDescriptionIsRequired();
            return;
        }
        previewData.setDescription(view.getDescription());
        int rating = view.getRating();
        Recipe recipe = RecipeUtil.createRecipeFromPreview(previewData, rating);
        if (repository.storeRecipe(recipe)) {
            view.showSuccess();
        } else {
            view.showStorageError();
        }
    }
}
