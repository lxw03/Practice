package com.example.cw.j2v8.kernal.container;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cw on 2017/5/10.
 */

public class HalContainerBase extends ViewGroup {

    public HalContainerBase(Context context) {
        super(context);
    }

    public static class LayoutParams extends ViewGroup.LayoutParams{
        private int x;
        private int y;

        public LayoutParams(){
            this(0,0,0,0);
        }
        public LayoutParams(int width, int height, int x, int y) {
            super(width, height);
            this.x = x;
            this.y = y;
        }
    }

    public interface onContainerLayout{
        void onLayout(ViewGroup viewGroup, int width, int height);
    }

    public onContainerLayout mLayoutListener;

    public void setContainerLayout(onContainerLayout onContainerLayout){
        mLayoutListener = onContainerLayout;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (null != mLayoutListener){
            mLayoutListener.onLayout(this, r-l, b-t);
        }
        int count = this.getChildCount();
        for (int i =0;i<count;i++){
            View child = this.getChildAt(i);
            if (child.getVisibility() != GONE){

            }
        }
    }
}
