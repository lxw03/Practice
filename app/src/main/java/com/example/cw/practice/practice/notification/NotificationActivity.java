package com.example.cw.practice.practice.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.example.cw.practice.R;

/**
 * Created by chenwei on 17/2/22.
 */

public class NotificationActivity extends AppCompatActivity {

    private Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initViews();
    }

    private void initViews() {
        btn = (Button) findViewById(R.id.notification_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                Notification notification = builder
                        .setContentTitle("这是通知标题")
                        .setContentText("这是通知内容")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_add)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setColor(Color.parseColor("#EAA935"))
                        .build();
                manager.notify(1, notification);
            }
        });
    }
}
