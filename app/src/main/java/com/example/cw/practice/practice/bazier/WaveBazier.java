package com.example.cw.practice.practice.bazier;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by cw on 2017/5/11.
 */

public class WaveBazier extends View implements View.OnClickListener{

    private Paint mPaint;
    private Path mPath;
    private int waveLength;
    private int offset;
    private int screenWidth;
    private int screenHeight;
    private int waveCount = 4;
    private int mCenterY;
    private ValueAnimator mValueAnimator;
    private int mYOffset;

    public WaveBazier(Context context) {
        this(context, null);
    }

    public WaveBazier(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveBazier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w;
        screenHeight = h;
        mCenterY = screenHeight/2;
        waveLength = screenWidth/waveCount;
        mYOffset = waveLength/4;
        initAnimation();
        mValueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(-waveLength+offset, mCenterY);
        for (int i=0; i<waveCount+1; i++){
            mPath.quadTo(-waveLength*3/4 + waveLength*i + offset, mCenterY + mYOffset, -waveLength/2 + waveLength*i + offset, mCenterY);
            mPath.quadTo(-waveLength/4 + waveLength*i + offset, mCenterY -mYOffset, waveLength*i + offset, mCenterY);
        }
        mPath.lineTo(screenWidth, screenHeight);
        mPath.lineTo(0,screenHeight);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    private void initAnimation(){
        if (mValueAnimator == null){
            mValueAnimator = ValueAnimator.ofInt(0, waveLength);
        }
        mValueAnimator.setDuration(1000);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(animation -> {
            offset = (int) animation.getAnimatedValue();
            //赋值后不能忘记重新绘制
            postInvalidate();
        });
    }

    @Override
    public void onClick(View v) {
        if (mValueAnimator.isRunning()){
            mValueAnimator.cancel();
        }
        mValueAnimator.start();
    }
}
