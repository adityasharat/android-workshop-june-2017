package edu.nie.expensemanager.browse;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author nilesh.tiwari
 */

public class VerticalSpace extends RecyclerView.ItemDecoration {

    private final int bottom;

    public VerticalSpace(int bottom) {
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = bottom;
    }
}
