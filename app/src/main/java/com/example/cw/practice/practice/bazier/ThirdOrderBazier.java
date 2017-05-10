package com.example.cw.practice.practice.bazier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by cw on 2017/5/10.
 */

public class ThirdOrderBazier extends View {

    private static final String TAG = "ThirdOrderBazier";

    private Paint mAuxiliaryPaint;
    private Paint mAuxiliaryTextPaint;
    private float mAuxiliaryX;
    private float mAuxiliaryY;
    private float mAuxiliaryXX;
    private float mAuxiliaryYY;
    private int mLeftAuxiliaryId =0;
    private int mRightAuxiliaryId =1;
    private boolean isSecondPointer = true;

    private Paint mBazierPaint;
    private float mStartX;
    private float mStartY;
    private float mEndX;
    private float mEndY;
    private Path mPath;

    public ThirdOrderBazier(Context context) {
        this(context, null);
    }

    public ThirdOrderBazier(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThirdOrderBazier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mBazierPaint = new Paint();
        mBazierPaint.setAntiAlias(true);
        mBazierPaint.setStyle(Paint.Style.STROKE);
        mBazierPaint.setStrokeWidth(8);

        mAuxiliaryPaint = new Paint();
        mAuxiliaryPaint.setAntiAlias(true);
        mAuxiliaryPaint.setStyle(Paint.Style.STROKE);
        mAuxiliaryPaint.setStrokeWidth(4);

        mAuxiliaryTextPaint = new Paint();
        mAuxiliaryTextPaint.setAntiAlias(true);
        mAuxiliaryTextPaint.setTextSize(20);

        mPath = new Path();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStartX = w/4;
        mStartY = h/2-200;
        mEndX = w/4*3;
        mEndY = h/2-200;

        mAuxiliaryX = w/2-200;
        mAuxiliaryY = h/2;
        mAuxiliaryXX = w/2 +200;
        mAuxiliaryYY = h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(mStartX, mStartY);
        canvas.drawPoint(mAuxiliaryX, mAuxiliaryY, mAuxiliaryPaint);
        //3个点
        canvas.drawText("辅助点1", mAuxiliaryX, mAuxiliaryY, mAuxiliaryTextPaint);
        canvas.drawText("辅助点2", mAuxiliaryXX, mAuxiliaryYY, mAuxiliaryTextPaint);
        canvas.drawText("起始点", mStartX, mStartY, mAuxiliaryTextPaint);
        canvas.drawText("结束点", mEndX, mEndY, mAuxiliaryTextPaint);
        //辅助点-结束点  辅助点-起始点
        canvas.drawLine(mAuxiliaryX, mAuxiliaryY, mStartX, mStartY, mAuxiliaryPaint);
        canvas.drawLine(mAuxiliaryX, mAuxiliaryY, mAuxiliaryXX, mAuxiliaryYY, mAuxiliaryPaint);
        canvas.drawLine(mAuxiliaryXX, mAuxiliaryYY, mEndX, mEndY, mAuxiliaryPaint);
        //三阶贝塞尔
        mPath.cubicTo(mAuxiliaryX, mAuxiliaryY, mAuxiliaryXX, mAuxiliaryYY, mEndX, mEndY);
        canvas.drawPath(mPath, mBazierPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        MotionEventCompat.getActionMasked(event) is designed to work with multiple pointers

        switch (MotionEventCompat.getActionMasked(event)) {
            case MotionEvent.ACTION_POINTER_DOWN:{
                isSecondPointer = true;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                mAuxiliaryX = event.getX(0);
                mAuxiliaryY = event.getY(0);
                if (isSecondPointer && event.getPointerCount()>1){
                    mAuxiliaryYY = event.getY(1);
                    mAuxiliaryXX = event.getX(1);
                }
                invalidate();
                break;
            }
            case MotionEvent.ACTION_POINTER_UP:{
                isSecondPointer = false;
                break;
            }
            default:
                break;
        }
        return true;
    }
}
