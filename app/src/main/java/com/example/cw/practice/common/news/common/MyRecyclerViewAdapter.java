package com.example.cw.practice.common.news.common;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cw on 2017/2/11.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int NORMAL_VIEW_TYPE = 1;
    private static final int FOOTER_VIEW_TYPE = 2;

    private RecyclerView.Adapter mAdapter;
    private View footerView;

    public MyRecyclerViewAdapter(RecyclerView.Adapter adapter) {
         this.mAdapter = adapter;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount()-1){
            return  FOOTER_VIEW_TYPE;
        }else {
            return NORMAL_VIEW_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FOOTER_VIEW_TYPE){
            return new RecyclerView.ViewHolder(footerView){

            };
        }else {
            return mAdapter.createViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == getItemCount()-1){
            return;
        }else {
            mAdapter.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + 1;
    }

    public void addFooterView(View footerView){
        this.footerView = footerView;
    }
}
