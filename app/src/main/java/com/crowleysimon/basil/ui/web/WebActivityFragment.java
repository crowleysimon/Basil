package com.crowleysimon.basil.ui.web;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crowleysimon.basil.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class WebActivityFragment extends Fragment {

    public WebActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web, container, false);
    }
}