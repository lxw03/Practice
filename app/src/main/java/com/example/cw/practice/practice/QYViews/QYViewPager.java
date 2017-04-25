package com.example.cw.practice.practice.QYViews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by cw on 2017/4/25.
 */

public class QYViewPager extends ViewPager {

    private boolean canHorizontalScroll = true;

    public QYViewPager(Context context) {
        super(context);
    }

    public QYViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void setCanScroll(boolean canScroll){
        canHorizontalScroll = canScroll;
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return canHorizontalScroll && super.onInterceptHoverEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return canHorizontalScroll && super.onTouchEvent(ev);
    }
}
