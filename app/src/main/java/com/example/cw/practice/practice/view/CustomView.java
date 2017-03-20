package com.example.cw.practice.practice.view;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;

/**
 * Created by eengoo on 17/3/12.
 */

//MeasureSpec = SpecMode + SpecSize 打包成一个int值来避免过多的内存对象分配
//UNSPECIFIED AT_MOST EXACTLY
//对于顶级的DecorView，其MeasureSpec由窗口的尺寸和自身的LayoutParams决定，对于普通View，有父容器的MeasureSpec和自身的LayoutParams决定

    //onMeasure方法中拿到的测量宽高可能不是准确的，比较好的习惯是在onLayout方法中获取View的测量宽高或者最终宽高
    //View的Measure过程和Activity的生命周期不是同步执行的，因此无法确保onResume时View已经测量完毕了，解决方法
    // 1. onWindowFocusHasChanged   2.通过post将一个Runnable投递到消息队列的尾部，等到Looper调用此Runnable的时候，View也已经初始化好了
    // 3.ViewTreeObserver  4. view.measure

public class CustomView extends AppCompatActivity{

    private View view;

    @Override
    protected void onStart() {
        super.onStart();


        view.post(new Runnable() {
            @Override
            public void run() {
                int width = view.getMeasuredWidth();
                int height = view.getMeasuredHeight();
            }
        });

        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = view.getMeasuredWidth();
                int height = view.getMeasuredHeight();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus){
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();
        }
    }

    private void viewMeasure(){
        //match_parent  此时无法知道parentSize多大
        //exactly
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.EXACTLY);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.EXACTLY);
        view.measure(widthMeasureSpec, heightMeasureSpec);
        //wrap_content
        //view的尺寸使用30位的二进制表示，最大为2^30-1 == (1<<30) -1
        int widthMeasureSpec_wrap_content = View.MeasureSpec.makeMeasureSpec( (1<<30) -1, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec_wrap_content = View.MeasureSpec.makeMeasureSpec( (1<<30) -1, View.MeasureSpec.AT_MOST);
        view.measure(widthMeasureSpec_wrap_content,heightMeasureSpec_wrap_content);
    }

    private void test(){
        //最小滑动距离
        ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();
    }


    //layout方法确定view本身的位置，onLayout方法确定所有子View的位置
}
