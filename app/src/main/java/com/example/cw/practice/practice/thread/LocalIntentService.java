package com.example.cw.practice.practice.thread;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by eengoo on 17/3/9.
 */
//HandlerThread继承了Thread，可以使用handler，不用自己prepare，loop
//IntentService是service的抽象类，由于是服务的原因，导致优先级比单纯的线程要高，所以IntentService适合执行一些高优先级的后台任务
//IntentService内部是通过消息的方式向HandlerThread请求执行任务，Handler中的Looper是顺序处理消息的，这就意味着IntentService也是顺序执行后台任务的

public class LocalIntentService extends IntentService {

    private static final String TAG = "LocalIntentService";

    public LocalIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getStringExtra("task_action");
        Log.d(TAG, "receive Task: " + action);
        SystemClock.sleep(3000);
        if ("task1".equals(action)){
            Log.d(TAG, "handle Task: " + action);
        }
    }

    //当onHandleIntent方法执行完毕后，IntentService会通过stopSelf(int startId)来尝试停止服务，会等待其它消息处理完再destroy
    //stopSelf会立即停止
    @Override
    public void onDestroy() {
        Log.d(TAG, "service onDestroy: ");
        super.onDestroy();
    }
}
