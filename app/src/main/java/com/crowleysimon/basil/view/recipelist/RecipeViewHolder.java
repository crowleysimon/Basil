package com.crowleysimon.basil.view.recipelist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.crowleysimon.basil.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imageview_featured)
    ImageView featuredImage;

    @BindView(R.id.textview_title)
    TextView title;

    @BindView(R.id.textview_description)
    TextView description;

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    public RecipeViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
