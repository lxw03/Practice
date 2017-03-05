package com.example.cw.practice.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.cw.practice.R;

/**
 * Created by cw on 2017/3/5.
 */

public class MainMore extends Fragment {
    private ImageView personal_avatar;
    private RelativeLayout homepage_layout, team_layout, personal_layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.more_main,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        personal_avatar = (ImageView) view.findViewById(R.id.more_personal_avatar);
        personal_layout = (RelativeLayout) view.findViewById(R.id.more_personal);
        homepage_layout = (RelativeLayout) view.findViewById(R.id.more_homepage);
        team_layout = (RelativeLayout) view.findViewById(R.id.more_team);
    }
}
