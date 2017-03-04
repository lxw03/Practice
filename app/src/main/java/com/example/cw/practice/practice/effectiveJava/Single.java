package com.example.cw.practice.practice.effectiveJava;

/**
 * Created by cw on 2017/3/4.
 */

public class Single {

    private static Single mSingle;

    private Single() {
    }

    public static Single getInstance(){
        if (mSingle != null){
            synchronized (Single.class){
                if (mSingle != null){
                    mSingle = new Single();
                }
            }
        }
        return mSingle;
    }
}
