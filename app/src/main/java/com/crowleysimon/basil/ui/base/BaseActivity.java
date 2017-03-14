package com.crowleysimon.basil.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.crowleysimon.basil.R;

public class BaseActivity extends AppCompatActivity {

    protected void setFragment(@NonNull Class fragmentClass) {
        Fragment fragment = findByTagOrCreate(fragmentClass);

        if (!fragment.isResumed()) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction t = fragmentManager.beginTransaction();
            t.replace(R.id.content, fragment, getTag(fragment));
            t.commit();
            fragmentManager.executePendingTransactions();
        }
    }

    @NonNull
    public static String getTag(@NonNull Fragment fragment) {
        return ((Object) fragment).getClass().getName();
    }

    @NonNull
    public static String getTag(@NonNull Class<? extends Fragment> fragmentClass) {
        return fragmentClass.getName();
    }

    @NonNull
    protected <T extends Fragment> T findByTagOrCreate(@NonNull Class<T> fragmentClass) {
        return findByTagOrCreate(fragmentClass, new Bundle());
    }

    @SuppressWarnings("unchecked")
    @NonNull
    protected <T extends Fragment> T findByTagOrCreate(@NonNull Class<T> fragmentClass, @NonNull Bundle bundle) {
        Fragment fragment = findByTag(fragmentClass);
        if (fragment == null) {
            fragment = Fragment.instantiate(this, fragmentClass.getName());
            fragment.setArguments(bundle);
        }
        return (T) fragment;
    }

    @Nullable
    protected <T extends Fragment> T findByTag(@NonNull Class<T> fragmentClass) {
        return findByTag(getTag(fragmentClass));
    }

    @SuppressWarnings("unchecked")
    @Nullable
    protected <T extends Fragment> T findByTag(@NonNull String tag) {
        return (T) getSupportFragmentManager().findFragmentByTag(tag);
    }
}
