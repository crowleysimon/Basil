package com.crowleysimon.basil.ui.recipes;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.crowleysimon.basil.R;
import com.crowleysimon.basil.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            setFragment(MainActivityFragment.class);
        }
    }
}
