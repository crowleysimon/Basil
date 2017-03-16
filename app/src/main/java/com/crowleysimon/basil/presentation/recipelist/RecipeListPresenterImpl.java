package com.crowleysimon.basil.presentation.recipelist;

import android.support.annotation.NonNull;

import com.crowleysimon.basil.data.repository.RecipeRepository;
import com.crowleysimon.basil.presentation.ViewNotFoundException;
import com.crowleysimon.basil.view.recipelist.RecipeListView;

public class RecipeListPresenterImpl implements RecipeListPresenter {

    @NonNull
    private RecipeRepository repository;

    private RecipeListView view;

    public RecipeListPresenterImpl(@NonNull RecipeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setView(RecipeListView view) {
        this.view = view;
    }

    @Override
    public void getListOfRecipes() {
        if (view == null) {
            throw new ViewNotFoundException();
        }

        view.displayRecipes(repository.getRecipes());
    }

    @Override
    public void onRecipeClick(@NonNull String url) {
        if (view == null) {
            throw new ViewNotFoundException();
        }

    }
}
