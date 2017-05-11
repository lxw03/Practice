package com.example.cw.j2v8.kernal.common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cw on 2017/5/10.
 */

public class HalCommonBatcher {

    private List<Runnable> runnablePool = null;

    public HalCommonBatcher() {
        runnablePool = new LinkedList<>();
    }

    public boolean add(Runnable runnable){
        if (null == runnable || null == runnablePool){
            return false;
        }
        return runnablePool.add(runnable);
    }

    public boolean batchPost(){
        final List<Runnable> pool = runnablePool;
        runnablePool = null;
        return HalCommon.postOnMainThread(() -> {
            Iterator<Runnable> iterator = pool.iterator();
            while (iterator.hasNext()){
                Runnable runnable = iterator.next();
                if (null != runnable){
                    runnable.run();
                }
            }
        });
    }

    public static ThreadLocal<HalCommonBatcher> threadBatcher = new ThreadLocal<>();

    public static boolean currentThreadAdded(Runnable runnable){
        if (null == runnable){
            return false;
        }
        HalCommonBatcher batcher = threadBatcher.get();
        if (null != batcher){
            batcher.add(runnable);
        }
        return false;
    }

    public static boolean currentThreadPostBatcher(){
        HalCommonBatcher batcher = threadBatcher.get();
        if (null != batcher){
            return batcher.batchPost();
        }
        return false;
    }

}
