package com.example.cw.practice.practice.rxJava;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.cw.practice.R;

/**
 * Created by chenwei on 17/2/27.
 */

public class RxJavaPractice extends AppCompatActivity {


    // RxJava练习
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        Paint a = new Paint();
        a.measureText("asdadsasd");
//        a.getTextBounds("asdasdads");
    }
}
