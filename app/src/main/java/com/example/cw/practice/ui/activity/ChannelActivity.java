package com.example.cw.practice.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.example.cw.practice.R;
import com.example.cw.practice.common.channel.AllTabsAdapter;
import com.example.cw.practice.common.channel.ChoseTabsAdapter;
import com.example.cw.practice.common.channel.SpaceItemDecoration;

import java.util.ArrayList;

/**
 * Created by chenwei on 17/2/7.
 */

public class ChannelActivity extends AppCompatActivity implements AllTabsAdapter.onAllTabsListener, ChoseTabsAdapter.OnChoseTabsListener{

    public static ArrayList<String> allTabs = new ArrayList<String>();
    public static ArrayList<String> choseTabs = new ArrayList<String>();

    private RecyclerView choseTabs_recyclerView, allTabs_recyclerView;

    private AllTabsAdapter allTabsAdapter;
    private ChoseTabsAdapter choseTabsAdapter;
    private ItemTouchHelper itemTouchHelper;

    private void initData() {

        allTabs.clear();
        choseTabs.clear();

        choseTabs.add("头条");
        choseTabs.add("科技");
        choseTabs.add("热点");
        choseTabs.add("政务");
        choseTabs.add("移动互联");
        choseTabs.add("军事");
        choseTabs.add("历史");
        choseTabs.add("社会");
        choseTabs.add("财经");
        choseTabs.add("娱乐");


        allTabs.add("体育");
        allTabs.add("时尚");
        allTabs.add("房产");
        allTabs.add("论坛");
        allTabs.add("博客");
        allTabs.add("健康");
        allTabs.add("轻松一刻");
        allTabs.add("直播");
        allTabs.add("段子");
        allTabs.add("彩票");
        allTabs.add("直播");
        allTabs.add("段子");
        allTabs.add("彩票");
        allTabs.add("直播");
        allTabs.add("段子");
        allTabs.add("彩票");
        allTabs.add("轻松一刻");
        allTabs.add("直播");
        allTabs.add("段子");
        allTabs.add("彩票");
        allTabs.add("直播");
        allTabs.add("段子");
        allTabs.add("彩票");
        allTabs.add("直播");
        allTabs.add("段子");
        allTabs.add("彩票");

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.activity_channel);
        initViews();
    }

    private void initViews() {
        choseTabs_recyclerView = (RecyclerView) findViewById(R.id.choseTabs_recyclerView);
        allTabs_recyclerView = (RecyclerView) findViewById(R.id.allTabs_recyclerView);
        choseTabsAdapter = new ChoseTabsAdapter(this);
        allTabsAdapter = new AllTabsAdapter(this);
        choseTabs_recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        allTabs_recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        choseTabs_recyclerView.setAdapter(choseTabsAdapter);
        allTabs_recyclerView.setAdapter(allTabsAdapter);
        choseTabs_recyclerView.addItemDecoration(new SpaceItemDecoration(15));
        allTabs_recyclerView.addItemDecoration(new SpaceItemDecoration(15));

        allTabsAdapter.setListener(this);
        choseTabsAdapter.setListener(this);
    }

    @Override
    public void allTabsItemClick(View view, int position) {
        allTabs_recyclerView.removeView(view);
        String item = allTabs.get(position);
        allTabs.remove(item);
        choseTabs.add(item);
        choseTabsAdapter.notifyDataSetChanged();
    }

    @Override
    public void choseTabsItemClick(View view, int position) {
        choseTabs_recyclerView.removeView(view);
        String item = choseTabs.get(position);
        choseTabs.remove(item);
        allTabs.add(item);
        allTabsAdapter.notifyDataSetChanged();
    }
}
