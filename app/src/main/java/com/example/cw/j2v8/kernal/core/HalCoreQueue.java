package com.example.cw.j2v8.kernal.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by cw on 2017/5/10.
 */

public class HalCoreQueue implements HalCoreHandler {

    private boolean shouldThreadExist = false;
    private BlockingQueue<Runnable> runningQueue;

    public HalCoreQueue() {
        runningQueue = new LinkedBlockingDeque<>();
    }

    public boolean addRunnable(Runnable runnable){
        if (runnable == null){
            return false;
        }
        return runningQueue.add(runnable);
    }

    public void runOnce(){
        try {
            Runnable runnable = runningQueue.take();
            runnable.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runOnceWithTimeOut(long delay){
        try {
            Runnable runnable = runningQueue.poll(delay, TimeUnit.MILLISECONDS);
            runnable.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean getShouldThreadExist(){
        return shouldThreadExist;
    }

    @Override
    public void post(Runnable runnable) {

    }
}
