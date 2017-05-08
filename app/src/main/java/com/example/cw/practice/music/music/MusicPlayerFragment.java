package com.example.cw.practice.music.music;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cw.j2v8.J2V8Activity;
import com.example.cw.practice.R;

/**
 * Created by eengoo on 17/3/20.
 */

public class MusicPlayerFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton mAppCompatButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_j2v8, container, false);
        mAppCompatButton = (AppCompatButton) view.findViewById(R.id.j2v8_test_btn_simple);
        mAppCompatButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.j2v8_test_btn_simple:{
                Intent intent = new Intent(getActivity(), J2V8Activity.class);
                startActivity(intent);
                break;
            }
            default:
                break;
        }
    }
}
