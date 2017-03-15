package com.crowleysimon.basil.view.addrecipe;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.crowleysimon.basil.BasilApplication;
import com.crowleysimon.basil.R;
import com.crowleysimon.basil.presentation.addrecipe.AddRecipePresenter;
import com.crowleysimon.basil.view.base.BaseActivity;

import javax.inject.Inject;

public class AddRecipeActivity extends BaseActivity {

    @Inject
    AddRecipePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BasilApplication) getApplication()).getComponent().inject(this);
        setContentView(R.layout.common_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null) {
            setFragment(AddRecipeFragment.class);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_recipe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_done) {
            presenter.saveRecipe();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
