package com.example.cw.practice.rn;

import android.os.Bundle;
import android.util.Log;

import com.example.cw.practice.rn.base.PreLoadActivity;

import javax.annotation.Nullable;

/**
 * Created by cw on 2017/5/31.
 */

public class MyPreLoadActivity extends PreLoadActivity {

    private static final String TAG = "GoToReactActivity";

    @Nullable
    @Override
    protected String getMainComponentName() {
        return "PreLoad";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + System.currentTimeMillis());
    }
}
