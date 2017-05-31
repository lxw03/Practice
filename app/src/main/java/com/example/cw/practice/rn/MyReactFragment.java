package com.example.cw.practice.rn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.cw.practice.rn.base.BaseReactFragment;


/**
 * Created by cw on 2017/4/20.
 */

public class MyReactFragment extends BaseReactFragment {

    private static final String TAG = "GoToReactActivity";
    @Override
    public String getComponentName() {
        return "Practice";
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onCreateView: " + System.currentTimeMillis());
    }
}
