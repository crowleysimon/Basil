package com.crowleysimon.basil.injection;

import com.crowleysimon.basil.BasilApplication;
import com.crowleysimon.basil.view.addrecipe.AddRecipeActivity;
import com.crowleysimon.basil.view.addrecipe.AddRecipeFragment;
import com.crowleysimon.basil.view.recipelist.RecipeListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(BasilApplication target);
    void inject(RecipeListActivity target);
    void inject(AddRecipeActivity target);
    void inject(AddRecipeFragment target);
}
