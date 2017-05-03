package com.example.cw.practice.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cw.iqiyi.activity.WelcomeActivity;
import com.example.cw.practice.R;

/**
 * Created by chenwei on 17/2/6.
 */

public class MainVideo extends Fragment {

    private Button mButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_main, container, false);
        mButton = (Button) view.findViewById(R.id.video_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WelcomeActivity.class));
            }
        });
        return view;
    }
}
