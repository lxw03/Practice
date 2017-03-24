package com.example.cw.practice.practice.soundRecorder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.cw.practice.R;
import com.example.cw.practice.util.DBHelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

/**
 * Created by cw on 2017/3/24.
 */

public class RecordingService extends Service {

    private String mFileName = null;
    private String mFilePath = null;

    private MediaRecorder mMediaRecorder;

    private DBHelper mDBHelper;

    private long mStartTimeMilles = 0;
    private long mElapsedMills = 0;
    private int mElapsedSeconds = 0;

    private OnTimeChangedListener mOnTimeChangedListener = null;
    private Timer mTimer;
    private TimerTask mTimerTask;

    private static final SimpleDateFormat mTimeFormat = new SimpleDateFormat("mm:ss", Locale.getDefault());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDBHelper = new DBHelper(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startRecord();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mMediaRecorder != null){
            stopRecord();
        }
        super.onDestroy();
    }

    private void startRecord(){
        setFileNameAndPath();

        mMediaRecorder = new MediaRecorder();
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mMediaRecorder.setOutputFile(mFilePath);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mMediaRecorder.setAudioChannels(1);
        try {
            mMediaRecorder.prepare();
            mMediaRecorder.start();
            mStartTimeMilles = System.currentTimeMillis();
            startTime();
        }catch (IOException e){
            Log.d(TAG, "startRecord: failed");
        }
    }

    private void stopRecord() {
        mMediaRecorder.stop();
        mElapsedMills = System.currentTimeMillis() - mStartTimeMilles;
        mMediaRecorder.release();
        Toast.makeText(this, mFilePath, Toast.LENGTH_LONG);
    }

    private void setFileNameAndPath(){
        int count =0;
        File f;
        do {
            count ++;
            mFileName = "sound_recording" +"#" +(mDBHelper.getCount() + count) +".mp4";
            mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            mFilePath += "/SoundRecorder/" + mFileName;
            f = new File(mFilePath);
        }while (f.exists() && !f.isDirectory());
    }

    private interface OnTimeChangedListener {
        void onTimeChanged(int seconds);
    }

    private void startTime(){
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mElapsedMills++;
                if (mOnTimeChangedListener != null){
                    mOnTimeChangedListener.onTimeChanged(mElapsedSeconds);
                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(1, createNotification());
                }
            }
        };
        mTimer.scheduleAtFixedRate(mTimerTask, 1000, 1000);
    }


    private Notification createNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        Notification notification = builder
                .setSmallIcon(R.mipmap.ic_media_play)
                .setContentTitle("正在录音")
                .setContentText(mTimeFormat.format(mElapsedMills * 1000))
                .setOngoing(true)
                .setContentIntent(PendingIntent.getActivities(getApplicationContext(), 0,
                        new Intent[]{new Intent(getApplicationContext(), SoundRecorderActivity.class)}, 0))
                .build();
        return notification;
    }
}
