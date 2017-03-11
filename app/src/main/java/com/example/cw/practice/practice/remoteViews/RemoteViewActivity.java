package com.example.cw.practice.practice.remoteViews;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.cw.practice.R;
import com.example.cw.practice.practice.danmaku.DanmakuActivity;
import com.example.cw.practice.practice.faceTest.FaceTestActivity;

/**
 * Created by eengoo on 17/3/11.
 */
//remoteView 跨进程更新界面，使用场景：通知栏和桌面小部件
//Notification 和 AppWidgetProvider运行在系统的SystemServer进程中，需要跨进程更新界面

public class RemoteViewActivity extends AppCompatActivity {

    private Button remoteViewsNotification, remoteViewsWidget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remoteviews);

        initViews();
    }

    private void initViews() {
        remoteViewsNotification = (Button) findViewById(R.id.remoteViewsNotification);
        remoteViewsNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());

                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.remote_notification);
                remoteViews.setTextViewText(R.id.remote_text, "remoteText");
                remoteViews.setImageViewResource(R.id.remote_img, R.mipmap.ic_care_normal);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, new Intent(getApplicationContext(), DanmakuActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
                remoteViews.setOnClickPendingIntent(R.id.remote_open,pendingIntent);

                Notification notification = builder
//                        .setContentTitle("这是标题")
//                        .setContentText("这是内容")
                        .setWhen(System.currentTimeMillis())
//                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_girl_selected))
                        .setSmallIcon(R.mipmap.ic_girl_normal)//好像这个是必须的
                        .setCustomContentView(remoteViews)
                        .setContentIntent(pendingIntent)
                        .build();
                mManager.notify(1, notification);
            }
        });
    }
}
