package com.example.cw.j2v8.kernal.common;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cw on 2017/5/10.
 */

public class HalCommonParams extends ViewGroup.LayoutParams {

    private int x;
    private int y;

    public HalCommonParams(){
        this(0,0,0,0);
    }

    public HalCommonParams(int width, int height, int x, int y){
        super(width, height);
        this.x = x;
        this.y = y;
    }

    public boolean addView(View view, int w, int h, int l, int t){
        if (null == view){
            return false;
        }
        view.setLayoutParams(new HalCommonParams(x, h,l,t));
        return true;
    }
}
