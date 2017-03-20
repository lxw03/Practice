package com.example.cw.practice.music.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by eengoo on 17/3/20.
 */

public class MusicPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles;
    private Fragment[] fragments;

    public MusicPagerAdapter(FragmentManager fm, String[] mTitles, Fragment[] fragments) {
        super(fm);
        this.mTitles = mTitles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
}
