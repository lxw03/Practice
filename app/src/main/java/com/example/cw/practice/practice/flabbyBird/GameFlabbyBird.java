package com.example.cw.practice.practice.flabbyBird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by cw on 2017/5/2.
 */

public class GameFlabbyBird extends SurfaceView implements SurfaceHolder.Callback, Runnable{

    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;
    private Thread mThread;
    private boolean isRunning;

    public GameFlabbyBird(Context context) {
        this(context, null);
    }

    public GameFlabbyBird(Context context, AttributeSet attrs) {
        super(context, attrs);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        setZOrderOnTop(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        mSurfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
        this.setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = true;
        mThread = new Thread(this);
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning){
            long start = System.currentTimeMillis();
            draw();
            long end = System.currentTimeMillis();
            if (end - start<50){
                try {
                    Thread.sleep(50-(end-start));
                    //mThread.sleep...
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void draw(){
        try {
            mCanvas = mSurfaceHolder.lockCanvas();
            if (mCanvas != null){
                // TODO: 2017/5/2
            }
        }catch (Exception e){

        }finally {
            if (mCanvas != null){
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
