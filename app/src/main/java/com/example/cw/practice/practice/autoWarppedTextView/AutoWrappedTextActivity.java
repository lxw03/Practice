package com.example.cw.practice.practice.autoWarppedTextView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.cw.practice.R;

/**
 * Created by cw on 2017/4/18.
 */

public class AutoWrappedTextActivity extends AppCompatActivity {

    private AutoWrappedTextView mAutoWrappedTextView;
    private final String text = "密码：jokG5456KL542356jsjdherGHSEEEjjssssiie呵呵嗒呵呵嗒is";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_wrapped_text);
        mAutoWrappedTextView = (AutoWrappedTextView) findViewById(R.id.auto_wrapped_text);
        mAutoWrappedTextView.setText(text);
    }
}
