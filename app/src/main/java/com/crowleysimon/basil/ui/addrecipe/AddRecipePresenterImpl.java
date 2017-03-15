package com.crowleysimon.basil.ui.addrecipe;

import com.schinizer.rxunfurl.RxUnfurl;

import okhttp3.OkHttpClient;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddRecipePresenterImpl implements AddRecipePresenter {

    private AddRecipeView view;

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void setView(AddRecipeView view) {
        this.view = view;
    }

    @Override
    public void saveRecipe() {
        String url = view.getUrl();
        if (url != null && !url.isEmpty()) {
            if (!url.startsWith("http")) {
                url = "http://" + url;
            }
            OkHttpClient okhttpClient = new OkHttpClient();

            RxUnfurl inst = new RxUnfurl.Builder()
                    .client(okhttpClient)
                    .scheduler(Schedulers.io())
                    .build();

            inst.generatePreview(url)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(previewData -> view.showSuccess(), throwable -> view.showUrlError());
        } else {
            view.showUrlIsRequired();
        }
    }
}
