package com.example.cw.practice.ui.activity;

import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.example.cw.practice.R;
import com.example.cw.practice.common.channel.AllTabsAdapter;
import com.example.cw.practice.common.channel.ChoseTabsAdapter;
import com.example.cw.practice.common.channel.SpaceItemDecoration;
import com.example.cw.practice.common.eventBus.MessageEvent;

import org.greenrobot.eventbus.EventBus;

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

    private Toolbar mToolbar;

    private void initData() {

        allTabs.clear();
        choseTabs.clear();

        choseTabs.add("头条");
        choseTabs.add("科技");
        choseTabs.add("财经");
        choseTabs.add("军事");
        choseTabs.add("体育");

        allTabs.add("房产");
        allTabs.add("足球");
        allTabs.add("娱乐");
        allTabs.add("电影");
        allTabs.add("汽车");
        allTabs.add("笑话");
        allTabs.add("游戏");
        allTabs.add("时尚");
        allTabs.add("情感");
        allTabs.add("精选");
        allTabs.add("电台");
        allTabs.add("NBA");
        allTabs.add("数码");
        allTabs.add("移动");
        allTabs.add("彩票");
        allTabs.add("教育");
        allTabs.add("论坛");
        allTabs.add("旅游");
        allTabs.add("手机");
        allTabs.add("博客");
        allTabs.add("社会");
        allTabs.add("家居");
        allTabs.add("暴雪");
        allTabs.add("亲子");
        allTabs.add("CBA");
        allTabs.add("消息");

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.activity_channel);
        initToolbar();
        initViews();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.channel_toolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emit();
                finish();
            }
        });
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

    //点击view拿到当前view的坐标

    @Override
    public void allTabsItemClick(View view, int position) {
        allTabs_recyclerView.removeView(view);
        String item = allTabs.get(position);
        allTabs.remove(item);
        choseTabs.add(item);
        choseTabsAdapter.notifyDataSetChanged();
        emit();
    }

    @Override
    public void choseTabsItemClick(View view, int position) {
        choseTabs_recyclerView.removeView(view);
        String item = choseTabs.get(position);
        choseTabs.remove(item);
        allTabs.add(item);
        allTabsAdapter.notifyDataSetChanged();
    }

    private void tabsItemClickAnimation(View view, int position){
        final PathMeasure pathMeasure;
        final float[] mCurrentPosition = new float[2];
        int parentLoc[] = new int[2];
    }

    private void emit(){
        Log.d("EventBus", "choseTabs");
        EventBus.getDefault().post(new MessageEvent("choseTabs"));
    }
}
