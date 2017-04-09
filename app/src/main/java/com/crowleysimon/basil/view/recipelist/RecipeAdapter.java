package com.crowleysimon.basil.view.recipelist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.crowleysimon.basil.R;
import com.crowleysimon.basil.data.model.Recipe;
import com.crowleysimon.basil.presentation.recipelist.RecipeListPresenter;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private List<Recipe> recipeList;

    private RecipeListPresenter presenter;

    public RecipeAdapter(List<Recipe> recipeList, @NonNull RecipeListPresenter presenter) {
        this.recipeList = recipeList;
        this.presenter = presenter;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
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
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}
