package com.example.cw.practice.practice.soundRecorder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Chronometer;

import com.example.cw.practice.R;

import java.io.File;

/**
 * Created by cw on 2017/3/23.
 */

public class RecordingFragment extends Fragment implements View.OnClickListener{

    private Chronometer mChronometer;
    private FloatingActionButton mButton;
    private boolean isRecording = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recording, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mChronometer = (Chronometer) view.findViewById(R.id.record_chronometer);
        mButton = (FloatingActionButton) view.findViewById(R.id.record_btn);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.record_btn:{
                if (isRecording){
                    mButton.setImageDrawable(getActivity().getDrawable(R.mipmap.ic_mic_white_36dp));
                    isRecording = false;
                    onPauseRecord();
                }else {
                    mButton.setImageDrawable(getActivity().getDrawable(R.mipmap.ic_media_stop));
                    isRecording = true;
                    onRecord();
                }
                break;
            }
            default:
                break;
        }
    }

    private void onPauseRecord() {
        mChronometer.stop();
        Intent intent = new Intent(getActivity(), RecordingService.class);
        getActivity().stopService(intent);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    private void onRecord() {
        File folder = new File(Environment.getExternalStorageDirectory() + "/SoundRecorder");
        if (!folder.exists()){
            folder.mkdir();
        }
        mChronometer.setBase(SystemClock.currentThreadTimeMillis());
        mChronometer.start();
        Intent intent = new Intent(getActivity(), RecordingService.class);
        getActivity().startService(intent);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
