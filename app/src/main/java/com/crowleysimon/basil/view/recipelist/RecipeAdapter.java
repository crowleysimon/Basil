package com.crowleysimon.basil.view.recipelist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crowleysimon.basil.R;
import com.crowleysimon.basil.data.model.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private List<Recipe> recipeList;

    public RecipeAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        final String title = recipeList.get(position).getTitle();
        holder.title.setText(title);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}
