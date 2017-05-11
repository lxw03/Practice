package com.example.cw.practice.practice.bazier;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.cw.practice.R;

/**
 * Created by cw on 2017/5/11.
 */

public class WaveBazier extends View implements View.OnClickListener{

    private static final int LINE_WIDTH = 5;
    private static final int LINE_COLOR = Color.BLACK;
    private static final int WAVE_COUNT = 4;
    private Paint mPaint;
    private Path mPath;
    private int waveLength;
    private int offset;
    private int screenWidth;
    private int screenHeight;
    private int waveCount;
    private int mCenterY;
    private ValueAnimator mValueAnimator;
    private int mYOffset;
    private int lineWidth;
    private int lineColor;

    public WaveBazier(Context context) {
        this(context, null);
    }

    public WaveBazier(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveBazier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null){
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WaveBazier);
            lineWidth = a.getInt(R.styleable.WaveBazier_line_width, LINE_WIDTH);
            lineColor = a.getColor(R.styleable.WaveBazier_line_color, LINE_COLOR);
            waveCount = a.getInt(R.styleable.WaveBazier_wave_count, WAVE_COUNT);
        }

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(lineWidth);
        mPaint.setColor(lineColor);
        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w;
        screenHeight = h;
        mCenterY = screenHeight/2;
        waveLength = screenWidth/waveCount;
        mYOffset = 50;
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
