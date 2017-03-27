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
import android.widget.EditText;
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
import okhttp3.OkHttpClient;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class AddRecipeFragment extends BaseFragment implements AddRecipeView {

    @Inject
    AddRecipePresenter presenter;

    @Inject
    OkHttpClient okHttpClient;

    @BindView(R.id.edit_text_enter_url)
    TextInputEditText editTextEnterUrl;

    @BindView(R.id.input_layout_enter_url)
    TextInputLayout inputLayoutEnterUrl;

    @BindView(R.id.editText)
    EditText titleTextView;

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

        Subscription subscription = RxTextView.textChanges(editTextEnterUrl)
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(charSequence -> {
                    Log.w("1", charSequence + "");
                    return charSequence != null && charSequence.length() != 0;
                })
                .filter(charSequence -> {
                    Log.w("2", charSequence + "");
                    return presenter.isWebUrl(charSequence.toString());
                })
                .flatMap(textViewTextChangeEvent -> {
                    Log.w("3", textViewTextChangeEvent + "");
                    return inst.generatePreview(textViewTextChangeEvent.toString()).onErrorResumeNext(throwable -> null);
                })
                .subscribe(previewData1 -> {
                    previewData = previewData1;
                    titleTextView.setText(previewData.getTitle());
                }, throwable -> Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show());
    }

    @Override
    public void onResume() {
        super.onResume();
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
        Snackbar.make(getActivity().getWindow().getDecorView(), "Recipe Successfully Saved", Snackbar.LENGTH_LONG).show();
        getActivity().finish();
    }

    @Override
    public void showStorageError() {
        Snackbar.make(getActivity().getWindow().getDecorView(), "Error Saving Recipe", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void clearErrorState() {
        inputLayoutEnterUrl.setErrorEnabled(false);
    }

    @Override
    public String getUrl() {
        return editTextEnterUrl.getText().toString();
    }

    @Override
    public void saveRecipe() {
        presenter.generateRecipe(previewData);
    }

    @Override
    public void getRecipeFromUrl(@NonNull String url) {

    }
}
