package com.example.cw.iqiyi.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.cw.iqiyi.base.BaseActivity;
import com.example.cw.practice.R;
import com.example.cw.practice.util.PackageManagerUtil;

/**
 * Created by cw on 2017/5/3.
 */

public class WelcomeActivity extends BaseActivity {

    private ImageView welcome_logo;
    private Animation mAnimation;
    private AppCompatTextView welcome_version;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initViews();
    }

    private void initViews() {
        welcome_logo = (ImageView) findViewById(R.id.welcome_logo);
        welcome_version = (AppCompatTextView) findViewById(R.id.welcome_version);
        welcome_version.setText(PackageManagerUtil.getAppVersion(this));
        welcome_version.setTextColor(Color.GRAY);
    }

    @Override
    public void onResume() {
        super.onResume();
        startAnimation(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                continueToLaunch();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void continueToLaunch() {
        startActivity(new Intent(this, QYMainActivity.class));
        finish();
    }

    private void startAnimation(Animation.AnimationListener listener) {
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.welcome_logo);
        mAnimation.setFillEnabled(true);
        mAnimation.setFillAfter(true);
        mAnimation.setAnimationListener(listener);
        welcome_logo.startAnimation(mAnimation);
    }
}
