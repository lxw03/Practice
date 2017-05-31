package com.example.cw.practice.rn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.cw.practice.R;

/**
 * Created by cw on 2017/5/31.
 */

public class MyNativeActivity extends AppCompatActivity {

    private static final String TAG = "GoToReactActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_test);
        Log.d(TAG, "onCreate: " + System.currentTimeMillis());
    }
}
