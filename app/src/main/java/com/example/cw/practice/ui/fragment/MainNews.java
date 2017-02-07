package com.example.cw.practice.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cw.practice.R;
import com.example.cw.practice.common.eventBus.MessageEvent;
import com.example.cw.practice.ui.activity.ChannelActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by chenwei on 17/2/6.
 */

public class MainNews extends Fragment{

    private ArrayList<String> mTabs = new ArrayList<String>();

    private Fragment[] mFragments = {new MainMeizi(), new MainVideo(), new MainMeizi(), new MainVideo(), new MainMe()};

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageView iv_add_tabs;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_main, container, false);
        initTabs(view);
        return view;
    }

    private void initTabs(View view) {
        mTabs.add("头条");
        mTabs.add("科技");
        mTabs.add("财经");
        mTabs.add("军事");
        mTabs.add("体育");
        mViewPager = (ViewPager) view.findViewById(R.id.vp_news);
        mTabLayout = (TabLayout) view.findViewById(R.id.tablayout_news);
        for (int i=0; i<mTabs.size(); i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(mTabs.get(i)));
        }
        mTabLayout.setupWithViewPager(mViewPager, true);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTabs.get(position);
            }
        });

        iv_add_tabs = (ImageView) view.findViewById(R.id.iv_add_tabs);
        iv_add_tabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ChannelActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event){
        Log.d("111111", event.toString());
        if (event.equals("choseTabs")){
            mTabs = ChannelActivity.choseTabs;
        }
    }

}
