package com.example.cw.practice.common.news;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cw.practice.R;
import com.example.cw.practice.common.news.common.NewsRecyclerView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by cw on 2017/2/11.
 */

public class NewsFragment extends Fragment{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsRecyclerView mRecyclerView;
    private ArrayList<String> integerList = new ArrayList<>();
    private MyAdapter mNewsAdapter;
    private Handler mHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.news_refreshLayout);
        mRecyclerView = (NewsRecyclerView) view.findViewById(R.id.news_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mNewsAdapter = new MyAdapter();

        mRecyclerView.setMyLoadListener(new NewsRecyclerView.MyLoadListener() {
            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (integerList.size() > 15){
                            mRecyclerView.setLoadMore(true);
                        }else {
                            int randomInt = new Random().nextInt(100);
                            integerList.add(String.valueOf(randomInt));
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
            integerList.add(String.valueOf(randomInt));
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

    public class MyAdapter extends RecyclerView.Adapter<NewsFragment.MyAdapter.MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_news_item, null, false);
            return new MyViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.txt.setText(integerList.get(position));
        }

        @Override
        public int getItemCount() {
            return integerList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView txt;

            public MyViewHolder(View itemView) {
                super(itemView);
                txt = (TextView) itemView.findViewById(R.id.fragment_news_text);

            }
        }
    }
}
