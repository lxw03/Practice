package com.example.cw.practice;

import android.app.Application;
import android.content.Context;

/**
 * Created by chenwei on 17/2/8.
 */

public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    private MyApplication() {
    }

    public static Context getInstance(){
        if (instance == null){
            synchronized (MyApplication.class){
                if (instance == null){
                    instance = new MyApplication();
                }
            }
        }
        return instance;
    }
}
