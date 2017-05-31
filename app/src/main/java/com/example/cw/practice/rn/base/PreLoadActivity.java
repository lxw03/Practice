package com.example.cw.practice.rn.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.cw.practice.rn.delegate.PreLoadReactDelegate;
import com.facebook.react.ReactActivity;

/**
 * Created by cw on 2017/5/31.
 */

public class PreLoadActivity extends ReactActivity {

    private PreLoadReactDelegate mPreLoadReactDelegate;

    protected PreLoadActivity() {
        mPreLoadReactDelegate = createPreLoadReactDelegate();
    }

    private PreLoadReactDelegate createPreLoadReactDelegate() {
        return new PreLoadReactDelegate(this,getMainComponentName());
    }

    /**
     * 子类重写，返回RN对应的界面组件名称
     * @return
     */
    protected @Nullable
    String getMainComponentName() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreLoadReactDelegate.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPreLoadReactDelegate.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPreLoadReactDelegate.onPause();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPreLoadReactDelegate.onDestroy();
    }

    @Override
    public void onNewIntent(Intent intent) {
        if(!mPreLoadReactDelegate.onNewIntent(intent)) {
            super.onNewIntent(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPreLoadReactDelegate.onActivityResult(requestCode,resultCode,data);
    }

}
