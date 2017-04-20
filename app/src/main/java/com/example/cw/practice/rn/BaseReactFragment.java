package com.example.cw.practice.rn;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;

/**
 * Created by cw on 2017/4/20.
 */

public abstract class BaseReactFragment extends Fragment {

    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    //this method gives the registered Class
    public abstract String getComponentName();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mReactRootView = new ReactRootView(context);
        mReactInstanceManager = ((RNApp)getActivity().getApplication()).getReactNativeHost().getReactInstanceManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //todo ?
        super.onCreate(savedInstanceState);
        return mReactRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mReactRootView.startReactApplication(mReactInstanceManager, getComponentName());
    }
}
