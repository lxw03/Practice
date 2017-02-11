package com.example.cw.practice.common.news;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by cw on 2017/2/11.
 */

public class NewsRecyclerView extends RecyclerView {

    private boolean isLoadMore = false;
    private View footerView;
    private TextView loadText;
    private MyLoadListener mMyLoadListener;
    private MyRecyclerViewAdapter mMyRecyclerViewAdapter;

    public interface MyLoadListener {
        void onLoadMore();
    }

    public void setMyLoadListener(MyLoadListener myLoadListener){
        this.mMyLoadListener = myLoadListener;
    }

    public NewsRecyclerView(Context context) {
        super(context);
        init();
    }

    public NewsRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NewsRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        this.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //停止滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    //获取最后一个完全显示的item的position
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    //判断在底部并且不在 加载 状态
                    if ((lastVisibleItem == totalItemCount -1) && !isLoadMore){
                        isLoadMore = true;
                        loadText.setText("正在加载");
                        footerView.setVisibility(VISIBLE);
                        mMyLoadListener.onLoadMore();
                    }

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
    }

    @Override
    public void setAdapter(Adapter adapter) {
        LinearLayout footerLayout = new LinearLayout(getContext());
        footerLayout.setGravity(Gravity.CENTER);
        footerLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 160));

        loadText = new TextView(getContext());
        footerLayout.addView(loadText);
        footerView = footerLayout;
        footerView.setVisibility(GONE);

        mMyRecyclerViewAdapter = new MyRecyclerViewAdapter(adapter);
        mMyRecyclerViewAdapter.addFooterView(footerView);

        super.setAdapter(mMyRecyclerViewAdapter);
    }

    public void setLoadMore(boolean complete){
        if (complete){
            loadText.setText("已经全部加载完毕了...");
        }else {
            footerView.setVisibility(GONE);
        }
        isLoadMore = false;
    }
}
