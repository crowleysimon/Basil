package com.crowleysimon.basil.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.crowleysimon.basil.R;

public class SectionItemDecoration extends RecyclerView.ItemDecoration {


    public SectionItemDecoration() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final int itemPosition = parent.getChildAdapterPosition(view);
        if (itemPosition == RecyclerView.NO_POSITION) {
            return;
        }

        outRect.right = (int) view.getContext().getResources().getDimension(R.dimen.keyline_8);
        outRect.left = (int) view.getContext().getResources().getDimension(R.dimen.keyline_8);
    }
}
