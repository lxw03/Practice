package com.example.cw.practice.practice.imageCache;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.File;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by eengoo on 17/3/8.
 */

public class BitmapLruCache {

    //图片缓存的核心对象
    private LruCache<String, Bitmap> lruCache;
    private Context context;
    //任务队列
    private LinkedList<Runnable> taskQueue = new LinkedList<>();
    //线程池
    private ExecutorService threadPool;
    private Semaphore semaphoreThreadPool;
    private Semaphore semaphoreThreadPoolHandler = new Semaphore(0);
    //UI线程
    private Handler uiHandler;
    //后台线程
    private Thread backThread;
    private Handler backThreadHandler;
    //线程数量
    private static final int THREAD_POOL_COUNT = 3;

    private void setLruCache(){
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory/8;
        lruCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
        threadPool = Executors.newFixedThreadPool(THREAD_POOL_COUNT);
        semaphoreThreadPool = new Semaphore(THREAD_POOL_COUNT);
    }

    private void backgroundThread(){
        //后台轮询线程
        backThread = new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                backThreadHandler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        //线程池取出一个任务进行执行
                        threadPool.execute(taskQueue.removeLast());
                        try {
                            semaphoreThreadPool.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                semaphoreThreadPoolHandler.release();
                Looper.loop();
            }
        };
        backThread.start();
    }

    public void loadImage(final ImageView imageView, String loadUrl, int defaultImage){
        imageView.setTag(loadUrl);
        imageView.setImageResource(defaultImage);
        if (uiHandler == null){
            uiHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    //todo
                    Bitmap bitmap = (Bitmap) msg.obj;
                    imageView.setImageBitmap(bitmap);
                }
            };
        }
        //根据path在缓存中获取bitmap
        Bitmap bitmap = getBitmapFromLruCache(loadUrl);
        if (bitmap!= null){
            refreshBitmap(bitmap);
        }else {

        }
    }

    private Bitmap getBitmapFromLruCache(String key){
        return lruCache.get(key);
    }

    //如果缓存在的话，我们需要刷新bitmap，代表这个图片已经加载完成了
    private void refreshBitmap(Bitmap bitmap){
        Message message = Message.obtain();
        message.obj = bitmap;
        uiHandler.sendMessage(message);
    }

    //图片不存在，就需要把他添加到lurCache中，以便之后快捷加载
    private void addTask(Runnable runnable){
        taskQueue.add(runnable);
        if (backThreadHandler == null){
            try {
                semaphoreThreadPoolHandler.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        backThreadHandler.sendEmptyMessage(0x250);
    }

    private Runnable buildTask(){
        return new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = null;
                //// TODO: 17/3/8
//                File file = getD
            }
        };
    }
}
