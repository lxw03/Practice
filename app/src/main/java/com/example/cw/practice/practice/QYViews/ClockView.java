package com.example.cw.practice.practice.QYViews;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.cw.practice.R;
import com.example.cw.practice.util.PixelUtil;

/**
 * Created by cw on 2017/4/25.
 */

public class ClockView extends View{

    private static final String TAG = "CLOCK";
    private static final int DEFAULT_SIZE = 400;
    private static final int STROKE_WIDTH =5;
    private static final int MINUTE_SIZE = 80;
    private static final int SECOND_SIZE = 20;

    private static final int DEFAULT_POINTER_WIDTH = 8;
    private static final int DEFAULT_POINTER_COLOR = Color.RED;

    //表盘
    private Paint mMinutePaint;
    private Paint mSecondPaint;
    private RectF mRectF;

    //表针
    private Paint mHourPointerPaint;
    private int mHourPointerWidth;
    private int mHourPointerColor;

    private Paint mMinutePointerPaint;
    private int mMinutePointerWidth;
    private int mMinutePointerColor;

    private Paint mSecondPointerPaint;
    private int mSecondPointerWidth;
    private int mSecondPointerColor;

    private RectF mHourRectF;
    private RectF mMinuteRectF;
    private RectF mSecondRectF;

    private Path mSecondPath;

    private ValueAnimator mSecondAnimator;

    private int mHourAngle;
    private int mMinuteAngle;
    private int mSecondAngle;


    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        if (attrs != null){
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ClockView);
            mHourPointerColor = a.getColor(R.styleable.ClockView_hourPointerColor, DEFAULT_POINTER_COLOR);
            mHourPointerWidth = a.getDimensionPixelSize(R.styleable.ClockView_hourPointerWidth, PixelUtil.dip2px(DEFAULT_POINTER_WIDTH));
            mMinutePointerColor = a.getColor(R.styleable.ClockView_minutePointerColor, DEFAULT_POINTER_COLOR);
            mMinutePointerWidth = a.getDimensionPixelSize(R.styleable.ClockView_minutePointerWidth, PixelUtil.dip2px(DEFAULT_POINTER_WIDTH));
            mSecondPointerColor = a.getColor(R.styleable.ClockView_secondPointerColor, DEFAULT_POINTER_COLOR);
            mSecondPointerWidth = a.getDimensionPixelSize(R.styleable.ClockView_secondPointerWidth, PixelUtil.dip2px(DEFAULT_POINTER_WIDTH));
        }
        initPaints();
        initRectF();
        initAnimations();
        initPaths();
    }

    private void initPaths() {
        mSecondPath = new Path();
    }

    private void initRectF() {
        mRectF = new RectF();
        mHourRectF = new RectF();
        mMinuteRectF = new RectF();
        mSecondRectF = new RectF();
    }

    private void initPaints() {
        mMinutePaint = new Paint();
        mMinutePaint.setAntiAlias(true);
        mMinutePaint.setStyle(Paint.Style.STROKE);
        mMinutePaint.setColor(Color.BLUE);
        mMinutePaint.setStrokeWidth(5);

        mSecondPaint = new Paint();
        mSecondPaint.setAntiAlias(true);
        mSecondPaint.setStyle(Paint.Style.STROKE);
        mSecondPaint.setColor(Color.BLACK);
        mSecondPaint.setStrokeWidth(5);

        mHourPointerPaint = new Paint();
        mHourPointerPaint.setAntiAlias(true);
        mHourPointerPaint.setStyle(Paint.Style.FILL);
        mHourPointerPaint.setColor(mHourPointerColor);

        mMinutePointerPaint = new Paint();
        mMinutePointerPaint.setAntiAlias(true);
        mMinutePointerPaint.setStyle(Paint.Style.FILL);
        mMinutePointerPaint.setColor(mMinutePointerColor);

        mSecondPointerPaint = new Paint();
        mSecondPointerPaint.setAntiAlias(true);
        mSecondPointerPaint.setStyle(Paint.Style.FILL);
        mSecondPointerPaint.setColor(mSecondPointerColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(DEFAULT_SIZE + STROKE_WIDTH*2, DEFAULT_SIZE+ STROKE_WIDTH*2);
        }else if (widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(DEFAULT_SIZE+ STROKE_WIDTH*2, heightSize+ STROKE_WIDTH*2);
        }else if (heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize+ STROKE_WIDTH*2, DEFAULT_SIZE+ STROKE_WIDTH*2);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        mRectF.set(getPaddingLeft(),getPaddingTop(),width-getPaddingRight(),height-getPaddingBottom());
        int radius = (int) (Math.min(mRectF.width(), mRectF.height())/2);
        canvas.drawCircle(mRectF.centerX(), mRectF.centerY(), radius, mMinutePaint);

        canvas.save();
        for (int i=0;i<=60;i++){
            if (i%5 ==0){
                canvas.drawLine(mRectF.width() + getPaddingLeft(), mRectF.height()/2+getPaddingTop(), mRectF.width()-MINUTE_SIZE+getPaddingLeft(), mRectF.height()/2+getPaddingTop(), mMinutePaint);
                canvas.rotate(6, mRectF.centerX(), mRectF.centerX());
            }else {
                canvas.drawLine(mRectF.width()+ getPaddingLeft(), mRectF.height()/2+getPaddingTop(), mRectF.width()-SECOND_SIZE+getPaddingLeft(), mRectF.height()/2+getPaddingTop(), mSecondPaint);
                canvas.rotate(6, mRectF.centerX(), mRectF.centerX());
            }
        }
        canvas.restore();

        int secondPointerLength = radius -80;

        float leftXDiff = (float) (Math.sin(Math.PI *mSecondAngle/180) * mSecondPointerWidth/2);
        float leftYDiff = (float) (Math.cos(Math.PI *mSecondAngle/180) * mSecondPointerWidth/2);
        float rightXDiff = (float) (Math.cos(Math.PI *mSecondAngle/180) * secondPointerLength);
        float rightYDiff = (float) (Math.sin(Math.PI *mSecondAngle/180) * secondPointerLength + leftYDiff);

        drawSecondPointerPath(canvas);
        mSecondPath.moveTo(mRectF.centerX() + leftXDiff, mRectF.centerY() - mSecondPointerWidth/2 + leftYDiff);
//        mSecondPath.lineTo(mRectF.centerX()  );

        mSecondRectF.set(mRectF.centerX() + leftXDiff, mRectF.centerY() - mSecondPointerWidth/2 + leftYDiff,
                mRectF.centerX() + rightXDiff, mRectF.centerY() + mSecondPointerWidth/2 +rightYDiff);
        canvas.drawRect(mSecondRectF, mSecondPaint);

    }

    private void initAnimations() {
        mSecondAnimator = ValueAnimator.ofInt(0, 360);
        mSecondAnimator.setDuration(60 * 1000);
        mSecondAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mSecondAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mSecondAngle = value;
                invalidate();
            }
        });
        mSecondAnimator.start();
    }

    private void calculateInitialAngles(){
        long stamps = System.currentTimeMillis();
    }

    private void drawSecondPointerPath(Canvas canvas){
    }
}
