package com.example.cw.iqiyi.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.example.cw.iqiyi.fragment.QYMainFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cw on 2017/5/4.
 */

public class QYMainViewPagerAdapter extends FragmentStatePagerAdapter {

    private SparseArray<QYMainFragment> fragmentList;
    private List<String> titleList;

    public QYMainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public QYMainFragment getItem(int position) {
        if (null == fragmentList || position>=fragmentList.size()){
            return null;
        }else {
            return fragmentList.get(position);
        }
    }

    @Override
    public int getCount() {
        return null == fragmentList?0:fragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        QYMainFragment qyMainFragment = (QYMainFragment) super.instantiateItem(container, position);
        fragmentList.put(position, qyMainFragment);
        return qyMainFragment;
    }

    public void addTab(String title, QYMainFragment fragment, int index){
        if (fragmentList == null){
            fragmentList = new SparseArray<>();
        }
        fragmentList.put(index, fragment);
        if (titleList == null){
            titleList = new ArrayList<>();
        }
        titleList.add(index, title);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    public String getTitle(int position){
        if (position < titleList.size() && position>=0){
            return titleList.get(position);
        }
        return "";
    }

    public void release(){
        if (fragmentList != null){
            fragmentList.clear();
        }
        if (titleList != null){
            titleList.clear();
        }
    }
}
