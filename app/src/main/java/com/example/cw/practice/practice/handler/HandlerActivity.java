package com.example.cw.practice.practice.handler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by eengoo on 17/3/9.
 */
//handler主要是为了解决在子线程中无法访问UI的矛盾   -- why?   android的UI控件不是线程安全的

//handler 创建的时候会采取当前线程的Looper来构造消息循环队列
//messageQueue 单链表 消息循环  enqueueMessage  next
//looper Looper会以无限循环的形式去检查是否有新消息
//ThreadLocal 线程内部的数据存储类
//              当某些数据是以线程为作用域并且不同的线程具有不同的数据副本的时候，可以考虑采用ThreadLocal
//              可以再不同的线程中互不干扰地存储并提供数据，通过ThreadLocal可以轻松获取每个线程的Looper
//UI Thread ActivityThread被创建时就会初始化Looper，主线程中可以默认使用Handler


public class HandlerActivity extends AppCompatActivity {

    private static final String TAG = "HandlerActivity";

    private ThreadLocal<Boolean> mBooleanThreadLocal = new ThreadLocal<Boolean>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBooleanThreadLocal.set(true);
        //3个线程中ThreadLocal存储的值是不同的
        Log.d(TAG, "run: " + mBooleanThreadLocal.get());

        new Thread("Thread#1") {
            @Override
            public void run() {
                mBooleanThreadLocal.set(true);
                Log.d(TAG, "run: " + mBooleanThreadLocal.get());
            }
        }.start();

        new Thread("Thread#2") {
            @Override
            public void run() {
                Log.d(TAG, "run: " + mBooleanThreadLocal.get());
//                Looper.prepare();
//                Handler threadHandler = new Handler();
//                Looper.loop();
                //子线程手动开启Looper，在所有的事情完成后应该调用quit方法来终止消息循环，否则这个线程就会一直处于等待的状态
//                Looper.myLooper().quit();

                //如果Message的next方法返回了新消息，Looper就会处理这条消息msg.target.dispatchMessage(msg),这样handler发送的消息最终交给他的dispatchMessage处理
                //但Handler的dispatchMessage是在创建Handler时所使用的Looper中执行的，这样就成功地将代码逻辑切到指定的线程中去
            }
        }.start();
    }
}
