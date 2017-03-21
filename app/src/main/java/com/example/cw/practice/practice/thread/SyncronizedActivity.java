package com.example.cw.practice.practice.thread;

import android.os.SystemClock;

/**
 * Created by cw on 2017/3/21.
 */

public class SyncronizedActivity {

    //方案1
    private static boolean stopRequested;

    private static synchronized void requestStop(){
        stopRequested = true;
    }

    private static synchronized boolean stopRequested(){
        return stopRequested;
    }

    //方案2
    private static volatile boolean isStopRequested;

    public static void main(String[] args){

        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i =0;
                while (!stopRequested()){
                    i++;
                }
            }
        });
        backgroundThread.start();
        SystemClock.sleep(2000);
        requestStop();
    }
}
