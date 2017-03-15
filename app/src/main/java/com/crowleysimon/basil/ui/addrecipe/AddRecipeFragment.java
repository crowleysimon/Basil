package com.crowleysimon.basil.ui.addrecipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.crowleysimon.basil.BasilApplication;
import com.crowleysimon.basil.R;
import com.crowleysimon.basil.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddRecipeFragment extends BaseFragment implements AddRecipeView {

    @Inject
    AddRecipePresenter presenter;

    @BindView(R.id.edit_text_enter_url)
    EditText editTextEnterUrl;

    @BindView(R.id.input_layout_enter_url)
    TextInputLayout inputLayoutEnterUrl;

    public AddRecipeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BasilApplication) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_recipe, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);
    }

    @Override
    public void showUrlIsRequired() {
        inputLayoutEnterUrl.setErrorEnabled(true);
        inputLayoutEnterUrl.setError("A Url is required");
    }

    @Override
    public void showUrlError() {
        inputLayoutEnterUrl.setErrorEnabled(true);
        inputLayoutEnterUrl.setError("There was an error saving this url");
    }

    @Override
    public void showSuccess() {
        inputLayoutEnterUrl.setErrorEnabled(false);
        Snackbar.make(getActivity().getWindow().getDecorView(), "Recipe Successfully Saved", Snackbar.LENGTH_LONG).show();
        getActivity().finish();
    }

    @Override
    public String getUrl() {
        return editTextEnterUrl.getText().toString();
    }
}
