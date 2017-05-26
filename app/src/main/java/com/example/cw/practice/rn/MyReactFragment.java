package com.example.cw.practice.rn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by cw on 2017/4/20.
 */

public class MyReactFragment extends BaseReactFragment{

    private static final String TAG = "GoToReactActivity";
    @Override
    public String getComponentName() {
        return "Practice";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: " + System.currentTimeMillis());
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
