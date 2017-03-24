package com.example.cw.practice.practice.soundRecorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import com.example.cw.practice.R;

/**
 * Created by cw on 2017/3/23.
 */

public class RecordingFragment extends Fragment {

    private Chronometer mChronometer;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recording, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mChronometer = (Chronometer) view.findViewById(R.id.record_chronometer);
//        mChronometer.start();
    }
}
