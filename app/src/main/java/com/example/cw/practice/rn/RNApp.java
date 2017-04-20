package com.example.cw.practice.rn;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.shell.MainReactPackage;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cw on 2017/4/20.
 */

public class RNApp extends Application implements ReactApplication {

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return true;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage> asList(
                    new MainReactPackage()
            );
        }
    };

    //fragment 需要
    //native 调用js时需要ReactContext ，但fragment里无法获取ReactContext,只能获取Context

    private ReactContext mReactContext;

    public ReactContext getReactContext(){
        return mReactContext;
    }

    private final ReactInstanceManager.ReactInstanceEventListener mReactInstanceEventListener = new ReactInstanceManager.ReactInstanceEventListener() {
        @Override
        public void onReactContextInitialized(ReactContext context) {
            mReactContext = context;
        }
    };

    private void registerReactInstanceListener(){
        mReactNativeHost.getReactInstanceManager().addReactInstanceEventListener(mReactInstanceEventListener);
    }

    private void unRegisterReactInstanceListener(){
        mReactNativeHost.getReactInstanceManager().removeReactInstanceEventListener(mReactInstanceEventListener);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReactInstanceListener();
    }

}
