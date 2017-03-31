package edu.nie.expensemanager.browse;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by nilesh.tiwari on 31-03-2017.
 */

public class VerticalSpace extends RecyclerView.ItemDecoration {

    private final int mVerticalSpaceHeight;

    public VerticalSpace(int mVerticalSpaceHeight) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = mVerticalSpaceHeight;
    }
}
