package com.example.cw.practice.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cw.practice.R;
import com.example.cw.practice.practice.animation.AnimationActivity;
import com.example.cw.practice.practice.animation.TypeEvaluatorActivity;
import com.example.cw.practice.practice.notification.NotificationActivity;
import com.example.cw.practice.practice.snackbar.SnackbarActivity;
import com.example.cw.practice.practice.statusBar.StatusBarActivity;

/**
 * Created by chenwei on 17/2/6.
 */

public class MainMe extends Fragment{
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_main, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        btn1 = (Button) view.findViewById(R.id.me_btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AnimationActivity.class);
                startActivity(intent);
            }
        });

        btn2 = (Button) view.findViewById(R.id.me_btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TypeEvaluatorActivity.class);
                startActivity(intent);
            }
        });

        btn3 = (Button) view.findViewById(R.id.me_btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NotificationActivity.class);
                startActivity(intent);
            }
        });
        btn4 = (Button) view.findViewById(R.id.me_btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StatusBarActivity.class);
                startActivity(intent);
            }
        });
        btn5 = (Button) view.findViewById(R.id.me_btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SnackbarActivity.class);
                startActivity(intent);
            }
        });
    }

}
