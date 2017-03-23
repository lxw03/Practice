package com.example.cw.practice.practice.soundRecorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.cw.practice.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cw on 2017/3/23.
 */

public class SoundRecorderActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_recorder);
        initViews();
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.sound_recorder_viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.sound_recorder_tablayout);

        fragments.add(new RecordingFragment());
        fragments.add(new RecordesFragment());
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
