package com.example.cw.practice.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cw on 2017/4/27.
 */

public abstract class LazyLoadFragment extends Fragment{

    private static final String TAG = "LzyLoadFragment";

    private boolean isInit = false;
    private boolean isLoad = false;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(setContentView(), container, false);
        isInit = true;
        isCanLoadData();
        return mView;
    }

    private void isCanLoadData(){
        if (!isInit){
            return;
        }
        if (getUserVisibleHint() && isResumed()){
            lazyLoad();
            isLoad = true;
        }else if (isLoad){
            stopLoad();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isLoad = false;
    }

    protected abstract void stopLoad();

    protected abstract void lazyLoad();

    protected abstract int setContentView();

    protected View getContentView(){
        return mView;
    }

    protected <T extends View> T findViewById(int id){
        return (T) getContentView().findViewById(id);
    }
}
