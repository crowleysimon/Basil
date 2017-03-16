package com.crowleysimon.basil.data.repository;

import android.support.annotation.NonNull;

import com.crowleysimon.basil.data.model.Recipe;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;

public class RecipeRepositoryImpl implements RecipeRepository {

    private Realm realm;

    public RecipeRepositoryImpl(@NonNull Realm realm) {
        this.realm = realm;
    }

    @Override
    public boolean storeRecipe(@NonNull Recipe recipe) {
        realm.executeTransaction(realm1 -> realm1.copyToRealm(recipe));
        return true;
    }

    @Override
    public List<Recipe> getRecipes() {
        RealmQuery<Recipe> query = realm.where(Recipe.class);
        return query.findAll();
    }
}
