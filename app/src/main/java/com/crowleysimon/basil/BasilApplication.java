package com.crowleysimon.basil;

import android.app.Application;

import com.crowleysimon.basil.injection.ApplicationComponent;
import com.crowleysimon.basil.injection.ApplicationModule;
import com.crowleysimon.basil.injection.DaggerApplicationComponent;

public class BasilApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
