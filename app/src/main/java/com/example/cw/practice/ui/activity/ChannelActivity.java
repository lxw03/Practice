package com.example.cw.practice.ui.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Path;
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
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import com.example.cw.practice.R;
import com.example.cw.practice.common.channel.AllTabsAdapter;
import com.example.cw.practice.common.channel.ChoseTabsAdapter;
import com.example.cw.practice.common.channel.SpaceItemDecoration;
import com.example.cw.practice.common.eventBus.MessageEvent;
import com.example.cw.practice.util.PixelUtil;
import com.example.cw.practice.util.SharedPreferenceUtil;

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
    private LinearLayout mLinearLayout;
    private boolean duringAnimation = false;

    private void initData() {

        allTabs.clear();
        choseTabs.clear();

        choseTabs = getSavedTabs();

        allTabs.add("头条");
        allTabs.add("科技");
        allTabs.add("财经");
        allTabs.add("军事");
        allTabs.add("体育");
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
        for (int i =0; i<choseTabs.size(); i++){
            allTabs.remove(choseTabs.get(i));
        }

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

        mLinearLayout = (LinearLayout) findViewById(R.id.channel_linearLayout);
    }


    @Override
    public void allTabsItemClick(View view, int position) {
//        allTabs_recyclerView.removeView(view);
//        String item = allTabs.get(position);
//        allTabs.remove(item);
//        choseTabs.add(item);
//        choseTabsAdapter.notifyDataSetChanged();
//        allTabsAdapter.notifyDataSetChanged();
//        emit();
        if (!duringAnimation){
            tabsItemClickAnimation(view, position);
        }
    }

    @Override
    public void choseTabsItemClick(View view, int position) {
        if (!duringAnimation && !choseTabs.get(position).equals("头条")){
            choseTabs_recyclerView.removeView(view);
            String item = choseTabs.get(position);
            choseTabs.remove(item);
            allTabs.add(item);
            allTabsAdapter.notifyDataSetChanged();
        }
    }


    /**
     * 点击更多频道的动画
     *
     * @param view
     * @param position
     */
    private void tabsItemClickAnimation(final View view, final int position){
        final PathMeasure mPathMeasure;
        final float[] mCurrentPosition = new float[2];
        int parentLoc[] = new int[2];
        mLinearLayout.getLocationInWindow(parentLoc);
        int startLoc[] = new int[2];
        view.getLocationInWindow(startLoc);

        final View startView = view;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(view.getWidth(), view.getHeight());
        allTabs_recyclerView.removeView(view);
        mLinearLayout.addView(startView, params);

        final View endView;
        float toX, toY;
        int endLoc[] = new int[2];
        int i = choseTabs.size();

        if (i == 0) {
            toX = view.getWidth();
            toY = view.getHeight();
        } else if (i % 4 == 0) {
            endView = choseTabs_recyclerView.getChildAt(i - 4);
            endView.getLocationInWindow(endLoc);
            toX = endLoc[0] - parentLoc[0] - PixelUtil.dip2px(this, 10);
            toY = endLoc[1] + view.getHeight() - parentLoc[1] + PixelUtil.dip2px(this, 10);
        } else {
            endView = choseTabs_recyclerView.getChildAt(i - 1);
            endView.getLocationInWindow(endLoc);
            toX = endLoc[0] + view.getWidth() - parentLoc[0];
            toY = endLoc[1] - parentLoc[1];
        }

        float startX = startLoc[0] - parentLoc[0];
        float startY = startLoc[1] - parentLoc[1];

        Path mPath = new Path();
        mPath.moveTo(startX, startY);
        // TODO: 2017/2/7  10dp好像有问题
        mPath.lineTo(toX + PixelUtil.dip2px(this, 10), toY);
        mPathMeasure = new PathMeasure(mPath, false);

        ValueAnimator animator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        animator.setDuration(500);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (Float) valueAnimator.getAnimatedValue();
                mPathMeasure.getPosTan(value, mCurrentPosition, null);
                startView.setX(mCurrentPosition[0]);
                startView.setY(mCurrentPosition[1]);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                duringAnimation = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                duringAnimation = false;
                //记得动画完成后把之前加入的view取消掉
                mLinearLayout.removeView(startView);
                String item = allTabs.get(position);
                allTabs.remove(item);
                choseTabs.add(item);
                choseTabsAdapter.notifyDataSetChanged();
                allTabsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    //// TODO: 2017/2/7 eventbus
    private void emit(){
        Log.d("EventBus", "choseTabs");
        EventBus.getDefault().post(new MessageEvent(choseTabs));
        saveChoseTabs();
    }

    private void saveChoseTabs(){
        String tabs = "";
        for (int i=0; i<choseTabs.size()-1; i++){
            tabs = tabs + choseTabs.get(i) + ",";
        }
        tabs = tabs + choseTabs.get(choseTabs.size()-1);
        SharedPreferenceUtil.setItem("channels", this, "choseTabs", tabs);
    }

    private ArrayList<String> getSavedTabs(){
        ArrayList<String> tabs = new ArrayList<String>();
        String value = SharedPreferenceUtil.getItem("channels", this, "choseTabs");
        if (value == ""){
            tabs.add("头条");
            tabs.add("科技");
            tabs.add("财经");
            tabs.add("军事");
            tabs.add("体育");
        }else {
            String [] arr = value.split(",");
            for (int i=0; i<arr.length;i++){
                tabs.add(arr[i]);
            }
        }
        return  tabs;
    }
}
