package com.example.cw.practice.rn.delegate;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.facebook.react.ReactActivityDelegate;

import javax.annotation.Nullable;

/**
 * Created by cw on 2017/5/31.
 */

public class MyReactActivityDelegate extends ReactActivityDelegate {

    public MyReactActivityDelegate(Activity activity, @Nullable String mainComponentName) {
        super(activity, mainComponentName);
    }

    public MyReactActivityDelegate(FragmentActivity fragmentActivity, @Nullable String mainComponentName) {
        super(fragmentActivity, mainComponentName);
    }

}
