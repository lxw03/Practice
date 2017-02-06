package com.example.cw.practice.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cw.practice.R;

/**
 * Created by chenwei on 17/2/6.
 */

public class MainNews extends Fragment {

    private String[] mTabs = {"tab1", "tab2", "tab3", "tab4", "tab5"};
    private Fragment[] mFragments = {new MainMeizi(), new MainVideo(), new MainMeizi(), new MainVideo(), new MainMe()};

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_main, container, false);
        initTabs(view);
        return view;
    }

    private void initTabs(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.vp_news);
        mTabLayout = (TabLayout) view.findViewById(R.id.tablayout_news);
        for (int i=0; i<mTabs.length; i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(mTabs[i]));
        }
        mTabLayout.setupWithViewPager(mViewPager, true);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mTabs.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTabs[position];
            }
        });
    }
}
