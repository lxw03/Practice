package com.example.cw.practice.util;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

import com.example.cw.practice.MyApplication;

/**
 * Created by cw on 2017/5/12.
 */

public class WindowUtil {

    public static WindowManager wm = (WindowManager) MyApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);

    public static void getScreenSize(Point point){
        wm.getDefaultDisplay().getSize(point);
    }


}
