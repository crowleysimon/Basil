package com.crowleysimon.basil.view.recipelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crowleysimon.basil.BasilApplication;
import com.crowleysimon.basil.R;
import com.crowleysimon.basil.data.model.Recipe;
import com.crowleysimon.basil.presentation.recipelist.RecipeListPresenter;
import com.crowleysimon.basil.view.addrecipe.AddRecipeActivity;
import com.crowleysimon.basil.view.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class RecipeListFragment extends BaseFragment implements RecipeListView {

    @Inject
    RecipeListPresenter presenter;

    @BindView(R.id.list_recipes)
    RecyclerView listRecipes;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @NonNull
    private RecipeAdapter recipeAdapter = new RecipeAdapter(null);

    public RecipeListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BasilApplication) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getListOfRecipes();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listRecipes.setHasFixedSize(true);
        listRecipes.setLayoutManager(new LinearLayoutManager(getActivity()));
        listRecipes.setAdapter(recipeAdapter);
    }

    @OnClick(R.id.fab)
    public void onFabCLick() {
        startActivity(new Intent(getActivity(), AddRecipeActivity.class));
    }

    @Override
    public void displayRecipes(@Nullable List<Recipe> recipes) {
        recipeAdapter = new RecipeAdapter(recipes);
        listRecipes.setAdapter(recipeAdapter);
    }
}
