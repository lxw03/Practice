package com.example.cw.practice.rn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.cw.practice.R;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

/**
 * Created by cw on 2017/4/20.
 */

public class MyMixedActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler{

    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mixed_react_and_native);

        mReactInstanceManager = ((RNApp)getApplication()).getReactNativeHost().getReactInstanceManager();

        MyReactFragment myReactFragment = new MyReactFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.rn_fragment_container, myReactFragment).commit();

    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mReactInstanceManager != null){
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mReactInstanceManager != null){
            mReactInstanceManager.onHostResume(this, this);
        }
    }
}
