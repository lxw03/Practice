package com.example.cw.practice.practice.QYViews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SeekBar;

import com.example.cw.practice.R;

/**
 * Created by cw on 2017/4/24.
 */

public class QYViewsPractice extends AppCompatActivity {
    private TabLayoutColorText mTabLayoutColorText;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qy_views);
        initViews();
    }

    private void initViews() {
        mTabLayoutColorText = (TabLayoutColorText) findViewById(R.id.tablayout_title);
        mSeekBar = (SeekBar) findViewById(R.id.tablayout_seekbar);
        mTabLayoutColorText.setDirection(0);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTabLayoutColorText.setProgress(progress/100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
