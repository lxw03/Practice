package com.example.cw.practice.practice.QYViews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.cw.practice.R;

/**
 * Created by cw on 2017/4/24.
 */

public class QYViewsPractice extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qy_views);
    }

    private void test(){
        RecyclerView mRecyclerView = new RecyclerView(this);
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }
}
