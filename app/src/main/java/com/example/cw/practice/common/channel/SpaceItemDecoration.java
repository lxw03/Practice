package com.example.cw.practice.common.channel;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by chenwei on 17/2/7.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    int mSpace;

    public SpaceItemDecoration(int mSpace) {
        this.mSpace = mSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
        outRect.top = 0;

    }
}
