package com.example.cw.practice;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.cw.practice.ui.fragment.MainMe;
import com.example.cw.practice.ui.fragment.MainMeizi;
import com.example.cw.practice.ui.fragment.MainMore;
import com.example.cw.practice.ui.fragment.MainNews;
import com.example.cw.practice.ui.fragment.MainVideo;
import com.example.cw.practice.util.StatusBarUtil;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String [] mTitles = {"新闻", "好友", "视频", "练习","更多"};
    private int[] mIconUnselectIds = {
            R.mipmap.ic_home_normal,R.mipmap.ic_friend_unselected,R.mipmap.ic_video_normal,R.mipmap.ic_care_normal, R.mipmap.ic_more_normal};
    private int[] mIconSelectIds = {
            R.mipmap.ic_home_selected,R.mipmap.ic_friend_selected, R.mipmap.ic_video_selected,R.mipmap.ic_care_selected,R.mipmap.ic_more_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private MainNews mainNewsFragment;
    private MainMeizi mainMeiziFragment;
    private MainVideo mainVideoFragment;
    private MainMe mainMeFragment;
    private MainMore mainMoreFragment;
    private static int tabLayoutHeight;

    private CommonTabLayout commonTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setStatusBarColor(this, getColor(R.color.main_color));
        initViews(savedInstanceState);
    }

    private void initFragments(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null){
            mainMeFragment = (MainMe) getSupportFragmentManager().findFragmentByTag("mainMeFragment");
            mainNewsFragment = (MainNews) getSupportFragmentManager().findFragmentByTag("mainNewsFragment");
            mainMeiziFragment = (MainMeizi) getSupportFragmentManager().findFragmentByTag("mainMeiziFragment");
            mainVideoFragment = (MainVideo) getSupportFragmentManager().findFragmentByTag("mainVideoFragment");
            mainMoreFragment = (MainMore) getSupportFragmentManager().findFragmentByTag("mainMoreFragment");
            currentTabPosition = savedInstanceState.getInt("current_tab_position");
        }else {
            mainNewsFragment = new MainNews();
            mainMeiziFragment = new MainMeizi();
            mainVideoFragment = new MainVideo();
            mainMeFragment = new MainMe();
            mainMoreFragment = new MainMore();

            transaction.add(R.id.fl_body, mainNewsFragment, "mainNewsFragment");
            transaction.add(R.id.fl_body, mainMeiziFragment, "mainMeiziFragment");
            transaction.add(R.id.fl_body, mainVideoFragment, "mainVideoFragment");
            transaction.add(R.id.fl_body, mainMeFragment, "mainMeFragment");
            transaction.add(R.id.fl_body, mainMoreFragment, "mainMoreFragment");
        }
        transaction.commit();
        changeFragment(currentTabPosition);
        commonTabLayout.setCurrentTab(currentTabPosition);
    }

    private void initViews(Bundle savedInstanceState) {
        commonTabLayout = (CommonTabLayout) findViewById(R.id.commonTabLayout);
        initTabs();
        initFragments(savedInstanceState);
    }

    private void initTabs() {
        for (int i=0; i<mTitles.length; i++){
            final int finalI = i;
            mTabEntities.add(new CustomTabEntity() {
                @Override
                public String getTabTitle() {
                    return mTitles[finalI];
                }

                @Override
                public int getTabSelectedIcon() {
                    return mIconSelectIds[finalI];
                }

                @Override
                public int getTabUnselectedIcon() {
                    return mIconUnselectIds[finalI];
                }
            });
        }
        commonTabLayout.setTabData(mTabEntities);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                changeFragment(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void changeFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position){
            case 0:
                transaction.hide(mainMeiziFragment);
                transaction.hide(mainVideoFragment);
                transaction.hide(mainMeFragment);
                transaction.hide(mainMoreFragment);
                transaction.show(mainNewsFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                transaction.hide(mainVideoFragment);
                transaction.hide(mainMeFragment);
                transaction.hide(mainNewsFragment);
                transaction.hide(mainMoreFragment);
                transaction.show(mainMeiziFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 2:
                transaction.hide(mainMeFragment);
                transaction.hide(mainNewsFragment);
                transaction.hide(mainMeiziFragment);
                transaction.hide(mainMoreFragment);
                transaction.show(mainVideoFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 3:
                transaction.hide(mainNewsFragment);
                transaction.hide(mainMeiziFragment);
                transaction.hide(mainVideoFragment);
                transaction.hide(mainMoreFragment);
                transaction.show(mainMeFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 4:
                transaction.hide(mainNewsFragment);
                transaction.hide(mainMeiziFragment);
                transaction.hide(mainVideoFragment);
                transaction.hide(mainMeFragment);
                transaction.show(mainMoreFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }
}
