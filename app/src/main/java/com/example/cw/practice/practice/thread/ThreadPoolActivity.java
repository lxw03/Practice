package com.example.cw.practice.practice.thread;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by eengoo on 17/3/9.
 */

//android中的线程池源自Java中的Executor，Executor是一个接口，真正的线程池的实现是ThreadPoolExecutor

    //线程池好处： 避免频繁创建销毁线程的性能开销
//               能有效控制线程池的最大并发数，避免大量线程互相抢占系统资源导致的阻塞
//               能对线程进行简单的管理，定时执行、指定间隔循环执行
public class ThreadPoolActivity extends AppCompatActivity {

//    ThreadPoolExecutor: corePoolSize, maximumPoolSize, keepAliveTime, allowCoreThreadTimeOut, unit, workQueue, threadFactory
    //Android 常见的4类具有不同功能的线程池
//    Executors:
//      FixedThreadPool, 只有固定大小的核心线程，空闲时不会被回收,任务队列没有大小限制
//      CachedThreadPool,   只有非核心线程，Integer.MAX_VALUE, 空闲时长60s
//      ScheduledThreadPool, 核心线程数量固定，非核心没有限制，非核心线程闲置立即被回收，用于执行定时任务或固定周期的任务
//      SingleThreadExecutor: 只有一个核心线程,统一所有的外界任务到一个线程，不需要处理线程同步的问题


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Runnable command = new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
            }
        };

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        fixedThreadPool.execute(command);

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(command);

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);
        scheduledThreadPool.schedule(command, 2000, TimeUnit.MILLISECONDS);
        scheduledThreadPool.scheduleWithFixedDelay(command, 10, 1000, TimeUnit.MILLISECONDS);

        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        singleThreadPool.execute(command);
    }
}
