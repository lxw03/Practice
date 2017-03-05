package com.example.cw.practice.ui.news;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cw.practice.R;
import com.example.cw.practice.common.news.NewsRecyclerView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by cw on 2017/2/11.
 */

public class NewsFragment extends Fragment{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsRecyclerView mRecyclerView;
    private ArrayList<String> integerList = new ArrayList<>();
    private NewsAdapter mNewsAdapter;
    private Handler mHandler = new Handler();

    private String name;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null, false);
        Bundle bundle = this.getArguments();
        name = bundle.getString("name");
        initViews(view);
        return view;
    }

    private void initViews(final View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.news_refreshLayout);
        mRecyclerView = (NewsRecyclerView) view.findViewById(R.id.news_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mNewsAdapter = new NewsAdapter(integerList, getActivity());

        mRecyclerView.setMyLoadListener(new NewsRecyclerView.MyLoadListener() {
            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (integerList.size() > 1000){
                            mRecyclerView.setLoadMore(true);
                        }else {
                            for (int i=0;i<12;i++){
                                int randomInt = new Random().nextInt(100);
                                integerList.add(String.valueOf(name + randomInt));
                            }
                            mNewsAdapter.notifyDataSetChanged();
                            mRecyclerView.setLoadMore(false);
                        }
                    }
                }, 2000);
            }
        });

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.red), getResources().getColor(R.color.blue));
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        refresh();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        refresh();
    }

    private void getData(){
        integerList.clear();
        Random random = new Random();
        while (integerList.size()<12){
            int randomInt = random.nextInt(100);
            integerList.add(String.valueOf(name + randomInt));
        }
    }

    private void refresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
                mRecyclerView.setAdapter(mNewsAdapter);
                mNewsAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
