package com.crowleysimon.basil.view.addrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.crowleysimon.basil.BasilApplication;
import com.crowleysimon.basil.R;
import com.crowleysimon.basil.presentation.addrecipe.AddRecipePresenter;
import com.crowleysimon.basil.view.base.BaseFragment;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.schinizer.rxunfurl.RxUnfurl;
import com.schinizer.rxunfurl.model.PreviewData;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import okhttp3.OkHttpClient;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class AddRecipeFragment extends BaseFragment implements AddRecipeView {

    @Inject
    AddRecipePresenter presenter;

    @Inject
    OkHttpClient okHttpClient;

    @BindView(R.id.input_layout_enter_url)
    TextInputLayout inputLayoutEnterUrl;

    @BindView(R.id.edit_text_enter_url)
    TextInputEditText editTextEnterUrl;

    @BindView(R.id.input_layout_title)
    TextInputLayout inputLayoutTitle;

    @BindView(R.id.edittext_title)
    TextInputEditText titleView;

    @BindView(R.id.input_layout_description)
    TextInputLayout inputLayoutDescription;

    @BindView(R.id.edittext_description)
    TextInputEditText descriptionView;

    @BindView(R.id.button_generate_recipe)
    Button generateRecipeButton;

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    @Nullable
    private String url;

    private RxUnfurl inst;

    private PreviewData previewData;

    public AddRecipeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BasilApplication) getActivity().getApplication()).getComponent().inject(this);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            url = intent.getStringExtra(Intent.EXTRA_TEXT);
        }
        inst = new RxUnfurl.Builder()
                .client(okHttpClient)
                .scheduler(Schedulers.io())
                .build();
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
        if (url != null) {
            editTextEnterUrl.setText(url);
        }
    }

    @OnTextChanged(R.id.edit_text_enter_url)
    public void onTextChanged(CharSequence charSequence) {
        if (charSequence != null && charSequence.length() != 0 && this.isResumed()) {
            presenter.isWebUrl(charSequence.toString());
        } else {
            clearErrorState();
        }
    }

    @OnClick(R.id.button_generate_recipe)
    public void onGenerateClick() {
        presenter.generateRecipe();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        if (editTextEnterUrl.getText().toString().length() > 0) {
            presenter.isWebUrl(editTextEnterUrl.getText().toString());
        }
    }

    @Override
    public void showUrlError() {
        inputLayoutEnterUrl.setErrorEnabled(true);
        inputLayoutEnterUrl.setError(getString(R.string.url_error));
    }

    @Override
    public void showSuccess() {
        Snackbar.make(getActivity().getWindow().getDecorView(), R.string.recipe_saved_success, Snackbar.LENGTH_LONG).show();
        getActivity().finish();
    }

    @Override
    public void showStorageError() {
        Snackbar.make(getActivity().getWindow().getDecorView(), R.string.recipe_saved_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void clearErrorState() {
        inputLayoutEnterUrl.setErrorEnabled(false);
    }

    @Override
    public void isUrlFormatted(boolean formatted) {
        generateRecipeButton.setEnabled(formatted);
        titleView.setEnabled(formatted);
        descriptionView.setEnabled(formatted);
        ratingBar.setEnabled(formatted);
    }

    @Override
    public String getUrl() {
        return editTextEnterUrl.getText().toString();
    }

    @Override
    public void saveRecipe() {
        presenter.saveRecipe();
    }

    @Override
    public String getTitle() {
        return titleView.getText().toString();
    }

    @Override
    public String getDescription() {
        return descriptionView.getText().toString();
    }

    @Override
    public PreviewData getData() {
        return previewData;
    }

    @Override
    public int getRating() {
        return (int) ratingBar.getRating();
    }

    @Override
    public void getRecipeFromUrl(@NonNull String url) {
        inst.generatePreview(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(previewData1 -> {
                    previewData = previewData1;
                    titleView.setText(previewData1.getTitle());
                    descriptionView.setText(previewData1.getDescription());
                    isUrlFormatted(true);
                }, throwable -> {
                    isUrlFormatted(false);
                    showUrlError();
                });
    }
}
