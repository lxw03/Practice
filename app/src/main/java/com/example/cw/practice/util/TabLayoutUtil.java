package com.example.cw.practice.util;

import android.app.Application;
import android.support.design.widget.TabLayout;

import com.example.cw.practice.MyApplication;

/**
 * Created by chenwei on 17/2/8.
 */

public class TabLayoutUtil {

    public static int getScreenWidth(){
        return MyApplication.getInstance().getApplicationContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static void dynamicSetTabLayoutMode(TabLayout tabLayout){
        int count = tabLayout.getChildCount();
        if (count<=5){
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        }else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }
}
