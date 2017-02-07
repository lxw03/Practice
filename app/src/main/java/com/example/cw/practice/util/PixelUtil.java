package com.example.cw.practice.util;

import android.content.Context;

/**
 * Created by cw on 2017/2/7.
 */

public class PixelUtil {

    public static int dip2px(Context context, float dpValue) {

        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dpValue * scale +0.5f);

    }
}
