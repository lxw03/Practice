package com.example.cw.practice.practice.shader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.cw.practice.R;

/**
 * Created by cw on 2017/4/7.
 */

public class RadarActivity extends AppCompatActivity implements View.OnClickListener{
    private Button start, pause;
    private RadarView mRadarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);
        initViews();
    }

    private void initViews() {
        start = (Button) findViewById(R.id.radar_start_btn);
        pause = (Button) findViewById(R.id.radar_pause_btn);
        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        mRadarView = (RadarView) findViewById(R.id.radar_view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.radar_start_btn:{
                mRadarView.startScan();
                break;
            }
            case R.id.radar_pause_btn:{
                mRadarView.stopScan();
                break;
            }
            default:
                break;
        }
    }
}
