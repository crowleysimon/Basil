package com.crowleysimon.basil.view.recipelist;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.crowleysimon.basil.R;
import com.crowleysimon.basil.view.base.BaseActivity;

public class RecipeListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            setFragment(RecipeListFragment.class);
        }
    }
}
