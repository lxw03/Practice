package com.example.cw.j2v8;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import com.example.cw.practice.R;

/**
 * Created by cw on 2017/5/8.
 */

public class J2V8Activity extends AppCompatActivity {
    private static final String TAG = "J2V8Activity";

    private AppCompatTextView mAppCompatTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_j2v8);
        mAppCompatTextView = (AppCompatTextView) findViewById(R.id.j2v8_tv);


        V8 v8 = V8.createV8Runtime();
        V8Object v8Object1 = new V8Object(v8).add("name", "cw");
        V8Object v8Object2 = new V8Object(v8).add("name", "xhy");
        V8Array v8Array = new V8Array(v8).push(v8Object1).push(v8Object2);

        v8Object1.release();
        v8Object2.release();
        v8Array.release();
    }
}
