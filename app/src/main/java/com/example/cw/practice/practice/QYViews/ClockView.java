package com.example.cw.practice.practice.QYViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cw on 2017/4/25.
 */

public class ClockView extends View{

    private static final String TAG = "CLOCK";
    private static final int DEFAULT_SIZE = 400;
    private static final int STROKE_WIDTH =5;
    private static final int MINUTE_SIZE = 40;
    private static final int SECOND_SIZE = 20;

    private Paint mMinutePaint;
    private Paint mSecondPaint;
    private RectF mRectF;

    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        initPaints();
        initRectF();
    }

    private void initRectF() {
        mRectF = new RectF();
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
                canvas.drawLine(mRectF.width(), mRectF.height()/2, mRectF.width()-MINUTE_SIZE, mRectF.height()/2, mMinutePaint);
                canvas.rotate(6, mRectF.centerX(), mRectF.centerX());
            }else {
                canvas.drawLine(mRectF.width(), mRectF.height()/2, mRectF.width()-SECOND_SIZE, mRectF.height()/2, mSecondPaint);
                canvas.rotate(6, mRectF.centerX(), mRectF.centerX());
            }
        }
        canvas.restore();
    }
}
