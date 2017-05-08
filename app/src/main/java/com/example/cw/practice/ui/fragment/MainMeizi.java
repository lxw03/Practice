package com.example.cw.practice.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.cw.practice.R;
import com.example.cw.practice.music.adapter.MusicPagerAdapter;
import com.example.cw.practice.music.ui.LocalFilesFragment;
import com.example.cw.practice.music.music.MusicPlayerFragment;
import com.example.cw.practice.music.ui.PlayListFragment;
import com.example.cw.practice.music.ui.SettingsFragment;

import java.util.ArrayList;

/**
 * Created by chenwei on 17/2/6.
 */

public class MainMeizi extends Fragment implements RadioGroup.OnCheckedChangeListener{

    private static final String[] mTitles = {"go to J2V8 Pages","MusicPlayer","LocalFiles","Settings"};

    private ViewPager musicViewPager;
    private ArrayList<RadioButton> radioButtons = new ArrayList<>();
    private RadioButton radio_button_play_list,radio_button_music,radio_button_local_files,radio_button_settings;
    private RadioGroup radio_group;
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meizi_main, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        radio_button_play_list = (RadioButton) view.findViewById(R.id.radio_button_play_list);
        radioButtons.add(radio_button_play_list);
        radio_button_music = (RadioButton) view.findViewById(R.id.radio_button_music);
        radioButtons.add(radio_button_music);
        radio_button_local_files = (RadioButton) view.findViewById(R.id.radio_button_local_files);
        radioButtons.add(radio_button_local_files);
        radio_button_settings = (RadioButton) view.findViewById(R.id.radio_button_settings);
        radioButtons.add(radio_button_settings);

        radio_group = (RadioGroup) view.findViewById(R.id.radio_group);
        radio_group.setOnCheckedChangeListener(this);

        Fragment[] fragments = new Fragment[4];
        fragments[1] = new PlayListFragment();
        fragments[0] = new MusicPlayerFragment();
        fragments[2] = new LocalFilesFragment();
        fragments[3] = new SettingsFragment();

        MusicPagerAdapter adapter = new MusicPagerAdapter(getChildFragmentManager(), mTitles, fragments);
        musicViewPager = (ViewPager) view.findViewById(R.id.music_vp);
        musicViewPager.setAdapter(adapter);
        musicViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radioButtons.get(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        toolbar = (Toolbar) view.findViewById(R.id.music_toolbar);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        int index = 0;
        switch (checkedId){
            case R.id.radio_button_play_list:
                index = 0;
                break;
            case R.id.radio_button_music:
                index = 1;
                break;
            case R.id.radio_button_local_files:
                index =2;
                break;
            case R.id.radio_button_settings:
                index =3;
                break;
            default:
                break;
        }
        musicViewPager.setCurrentItem(index);
        toolbar.setTitle(mTitles[index]);
    }
}
