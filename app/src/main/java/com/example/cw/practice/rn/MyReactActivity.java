package com.example.cw.practice.rn;

import com.facebook.react.ReactActivity;

import javax.annotation.Nullable;

/**
 * Created by cw on 2017/4/20.
 */

public class MyReactActivity extends ReactActivity{

    //react-native bundle --platform android --dev false --entry-file index.android.js --bundle-output app/src/main/assets/index.android.bundle --assets-dest app/src/main/res/


    @Nullable
    @Override
    protected String getMainComponentName() {
        return "Practice";
    }
}
