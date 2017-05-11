package com.example.cw.j2v8.kernal.common;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.lang.ref.WeakReference;

/**
 * Created by cw on 2017/5/10.
 */

public class HalCommon {

    static WeakReference<Context> context = null;

    static Context obtainContext(){
        if (null == context){
            return null;
        }
        return context.get();
    }

    static Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    public static boolean postOnMainThread(Runnable runnable){
        if (null == runnable || null == mainThreadHandler){
            return false;
        }
        return mainThreadHandler.post(runnable);
    }
}
