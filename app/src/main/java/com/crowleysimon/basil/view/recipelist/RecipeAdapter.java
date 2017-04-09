package com.crowleysimon.basil.view.recipelist;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.crowleysimon.basil.R;
import com.crowleysimon.basil.data.model.Recipe;
import com.crowleysimon.basil.presentation.recipelist.RecipeListPresenter;
import com.crowleysimon.basil.util.CustomTabsUtil;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    @Nullable
    private List<Recipe> recipeList;

    @NonNull
    private RecipeListPresenter presenter;

    @Nullable
    private CustomTabsUtil customTabsUtil;

    public RecipeAdapter(@Nullable List<Recipe> recipeList, @NonNull RecipeListPresenter presenter, @Nullable CustomTabsUtil customTabsUtil) {
        this.recipeList = recipeList;
        this.presenter = presenter;
        this.customTabsUtil = customTabsUtil;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        if (recipeList != null) {
            Context context = holder.itemView.getContext();
            holder.title.setText(recipeList.get(position).getTitle());
            holder.description.setText(recipeList.get(position).getDescription());
            holder.ratingBar.setRating(recipeList.get(position).getRating());

            if (recipeList.get(position).getImageUrl() != null) {
                Glide.with(context)
                        .load(recipeList.get(position).getImageUrl())
                        .centerCrop()
                        .crossFade()
                        .into(holder.featuredImage);
            } else {
                Glide.clear(holder.featuredImage);
            }

            holder.itemView.setOnClickListener(v -> presenter.onRecipeClick(recipeList.get(position).getUrl()));

            if (customTabsUtil != null && customTabsUtil.getSession() != null) {
                customTabsUtil.getSession().mayLaunchUrl(Uri.parse(recipeList.get(position).getUrl()), null, null);
            }
        }
    }

    @Override
    public int getItemCount() {
        return recipeList != null ? recipeList.size() : 0;
    }
}
