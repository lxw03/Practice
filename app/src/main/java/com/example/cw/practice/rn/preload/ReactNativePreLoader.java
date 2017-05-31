package com.example.cw.practice.rn.preload;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactRootView;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by cw on 2017/5/31.
 */

public class ReactNativePreLoader {

    private static final String TAG = "ReactNativePreLoader";

    private static final Map<String, ReactRootView> CACHE = new HashMap<>();

    /**
     * 初始化ReactRootView，并添加到缓存中
     * @param activity
     * @param componentName
     */
    public static void preLoad(Activity activity, String componentName){
        if (CACHE.get(componentName) != null){
            return;
        }
        ReactRootView rootView = new ReactRootView(activity);
        rootView.startReactApplication(((ReactApplication)activity.getApplication()).getReactNativeHost().getReactInstanceManager(), componentName, null);
        CACHE.put(componentName, rootView);
    }

    /**
     * 从缓存中取出ReactRootView
     * @param componentName
     * @return
     */
    public static ReactRootView getReactRootView(String componentName){
        return CACHE.get(componentName);
    }

    /**
     * 从当前界面移除rootView
     * @param componentName
     */
    public static void detachRootView(String componentName){
        try {
            ReactRootView rootView = getReactRootView(componentName);
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null){
                parent.removeView(rootView);
            }
        }catch (Exception e){
            Log.d(TAG, "detachRootView: " + e.getMessage());
        }
    }
}
