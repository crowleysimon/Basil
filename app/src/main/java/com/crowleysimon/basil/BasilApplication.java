package com.crowleysimon.basil;

import android.app.Application;

import com.crowleysimon.basil.injection.ApplicationComponent;
import com.crowleysimon.basil.injection.ApplicationModule;
import com.crowleysimon.basil.injection.DaggerApplicationComponent;

import io.realm.Realm;

public class BasilApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
