package com.crowleysimon.basil.injection;

import com.crowleysimon.basil.BasilApplication;
import com.crowleysimon.basil.ui.addrecipe.AddRecipeActivity;
import com.crowleysimon.basil.ui.addrecipe.AddRecipeFragment;
import com.crowleysimon.basil.ui.recipes.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(BasilApplication target);
    void inject(MainActivity target);
    void inject(AddRecipeActivity target);
    void inject(AddRecipeFragment target);
}
