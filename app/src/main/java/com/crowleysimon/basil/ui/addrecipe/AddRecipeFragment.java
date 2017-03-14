package com.crowleysimon.basil.ui.addrecipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crowleysimon.basil.R;
import com.crowleysimon.basil.ui.base.BaseFragment;

public class AddRecipeFragment extends BaseFragment {

    public AddRecipeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_recipe, container, false);
    }
}
