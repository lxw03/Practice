package com.example.cw.practice.util;

import android.content.res.Resources;

/**
 * Created by cw on 2017/2/7.
 */

public class PixelUtil {

    public static int dip2px(float dpValue) {

        final float scale = Resources.getSystem().getDisplayMetrics().density;

        return (int) (dpValue * scale +0.5f);

    }
}
