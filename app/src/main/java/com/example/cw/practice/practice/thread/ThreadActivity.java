package com.example.cw.practice.practice.thread;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.net.URL;

/**
 * Created by eengoo on 17/3/9.
 */

//新开一个线程
//new Thread extends Thread   本质上是实现runnable接口的子类
//实现runnable接口，实际中多使用，java单继承

public class ThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new MyAsyncTask("AsyncTask#1").execute();
//        new MyAsyncTask("AsyncTask#2").executeOnExecutor(AsyncTask.SERIAL_EXECUTOR); //3.0以上串行执行方法

        testIntentService();
    }

    private void testIntentService() {
        Intent service = new Intent(this, LocalIntentService.class);
        service.putExtra("task_action", "task1");
        startService(service);
        service.putExtra("task_action", "task2");
        startService(service);
        service.putExtra("task_action", "task3");
        startService(service);
    }


    //AsyncTask有2个线程池SerialExecutor和THREAD_POOL_EXECUTOR和一个Handler，其中线程池SerialExecutor用于任务的排队，THREAD_POOL_EXECUTOR用于真正地执行任务，handler用于将环境从线程池切换到主线程

    private static class MyAsyncTask extends AsyncTask<URL, Integer, Long>{

        private String name;

        public MyAsyncTask(String name) {
            this.name = name;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Long doInBackground(URL... params) {
//            publishProgress();
            return null;
        }
    }

}
