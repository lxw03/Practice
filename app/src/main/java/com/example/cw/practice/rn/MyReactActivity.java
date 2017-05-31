package com.example.cw.practice.rn;

import android.os.Bundle;
import android.util.Log;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;

import javax.annotation.Nullable;

/**
 * Created by cw on 2017/4/20.
 */

public class MyReactActivity extends ReactActivity{

    //react-native bundle --platform android --dev false --entry-file index.android.js --bundle-output app/src/main/assets/index.android.bundle --assets-dest app/src/main/res/

    //react-native unbundle --platform android --dev false --entry-file index.android.js --bundle-output app/src/main/assets/js-modules --assets-dest app/src/main/res/


    private static final String TAG = "GoToReactActivity";

    @Nullable
    @Override
    protected String getMainComponentName() {
        return "Practice";
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return super.createReactActivityDelegate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + System.currentTimeMillis());
    }
}
