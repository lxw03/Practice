package com.example.cw.iqiyi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.cw.iqiyi.adapter.QYMainViewPagerAdapter;
import com.example.cw.iqiyi.base.BaseActivity;
import com.example.cw.iqiyi.mvp.QYContract;
import com.example.cw.iqiyi.presenter.QYMainPresenter;
import com.example.cw.practice.R;

public class QYMainActivity extends BaseActivity implements QYContract.IView{

    private View mLoadingView;
    private View mExceptionView;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private QYMainViewPagerAdapter mViewPagerAdapter;
    private QYContract.IPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qymain);
        initViews();
        initPresenter(savedInstanceState);
        mPresenter.initTabs();
    }

    private void initPresenter(Bundle args) {
        if (mPresenter == null){
            setPresenter(new QYMainPresenter(this));
        }
        mPresenter.onCreate(args);
    }

    private void initViews() {
        mLoadingView = findViewById(R.id.qymain_loading);
        mExceptionView = findViewById(R.id.qymain_load_data_exception);
        mTabLayout = (TabLayout) findViewById(R.id.qymain_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.qymain_viewpager);
        mViewPagerAdapter = new QYMainViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager,false);
    }

    @Override
    public void setPresenter(QYContract.IPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showOrHideLoadingView(boolean show) {
        mLoadingView.setVisibility(show?View.VISIBLE:View.INVISIBLE);
    }

    @Override
    public void showExceptionTips(boolean netWork) {
        mExceptionView.setVisibility(netWork?View.VISIBLE:View.INVISIBLE);
    }

    @Override
    public boolean isFinished() {
        return this.isFinishing();
    }
}
