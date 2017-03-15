package com.crowleysimon.basil.util;

import android.support.annotation.NonNull;

import com.crowleysimon.basil.data.model.Recipe;
import com.schinizer.rxunfurl.model.PreviewData;

import java.util.Calendar;

public class RecipeUtil {

    public static Recipe createRecipeFromPreview(@NonNull PreviewData previewData) {
        Recipe recipe = new Recipe();
        recipe.setDescription(previewData.getDescription());
        if (previewData.getImages() != null && !previewData.getImages().isEmpty()) {
            recipe.setImageUrl(previewData.getImages().get(0).getSource());
        }
        recipe.setTitle(previewData.getTitle());
        recipe.setUrl(previewData.getUrl());
        Calendar calendar = Calendar.getInstance();
        recipe.setSavedDate(calendar.getTime());
        return recipe;
    }
}
