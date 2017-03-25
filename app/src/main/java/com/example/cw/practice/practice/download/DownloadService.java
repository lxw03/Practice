package com.example.cw.practice.practice.download;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.example.cw.practice.R;

import java.io.File;

/**
 * Created by cw on 2017/3/25.
 */

public class DownloadService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private DownloadTask mDownloadTask;
    private DownLoadListener mDownLoadListener = new DownLoadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Downloading", progress));
        }

        @Override
        public void onSuccess() {
            mDownloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Success", -1));
            Toast.makeText(DownloadService.this, "download success", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailed() {
            mDownloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Failed", -1));
            Toast.makeText(DownloadService.this, "download failed", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPause() {
            mDownloadTask = null;
            Toast.makeText(DownloadService.this, "download paused", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCanceled() {
            mDownloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, "download canceled", Toast.LENGTH_LONG).show();
        }
    };

    private String downloadUrl;

    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder{
        public void startDownload(String url){
            if (mDownloadTask == null){
                downloadUrl = url;
                mDownloadTask = new DownloadTask(mDownLoadListener);
                mDownloadTask.execute(downloadUrl);
                startForeground(1,getNotification("Downloading...", 0));
                Toast.makeText(DownloadService.this, "Downloading...",Toast.LENGTH_LONG).show();
            }
        }
        public void pauseDownload(){
            if (mDownloadTask != null){
                mDownloadTask.pauseDownload();
            }
        }
        public void cancelDownload(){
            if (mDownloadTask != null){
                mDownloadTask.cancelDownload();
            }else {
                if (downloadUrl != null){
                    //取消下载文件时 要将文件删除 并且取消通知
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory +fileName);
                    if (file.exists()){
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownloadService.this, "Cancel Download", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private NotificationManager getNotificationManager(){
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, int progress){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Intent intent = new Intent(this, DownloadActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pendingIntent);
        builder.setContentTitle(title);
        if (progress>0){
            //当progress大于等于0显示现在进度
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }
}
