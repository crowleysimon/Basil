package com.crowleysimon.basil.view.recipelist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crowleysimon.basil.R;
import com.crowleysimon.basil.view.addrecipe.AddRecipeActivity;
import com.crowleysimon.basil.view.base.BaseFragment;

import butterknife.OnClick;

public class RecipeListFragment extends BaseFragment {

    public RecipeListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @OnClick(R.id.fab)
    public void onFabCLick() {
        startActivity(new Intent(getActivity(), AddRecipeActivity.class));
    }
}