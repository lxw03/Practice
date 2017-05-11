package com.example.cw.j2v8.kernal.executor;

import com.eclipsesource.v8.ReferenceHandler;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Value;
import com.example.cw.j2v8.kernal.core.HalCoreMaintain;

/**
 * Created by cw on 2017/5/11.
 */

public class HalExecutor {

    private HalCoreMaintain mHalCoreMaintain;

    private V8 context;

    public HalExecutor(HalCoreMaintain halCoreMaintain) {
        mHalCoreMaintain = halCoreMaintain;
    }

    private boolean init(){
        if (null == context){
            context = V8.createV8Runtime();
        }
        if (null != context){

            context.addReferenceHandler(new ReferenceHandler() {
                @Override
                public void v8HandleCreated(V8Value v8Value) {

                }

                @Override
                public void v8HandleDisposed(V8Value v8Value) {

                }
            });
        }
        return false;
    }
}
