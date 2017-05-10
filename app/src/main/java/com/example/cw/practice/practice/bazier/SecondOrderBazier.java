package com.example.cw.practice.practice.bazier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by cw on 2017/5/9.
 */

public class SecondOrderBazier extends View {

    private Paint mAuxiliaryPaint;
    private Paint mAuxiliaryTextPaint;
    private float mAuxiliaryX;
    private float mAuxiliaryY;

    private Paint mBazierPaint;
    private float mStartX;
    private float mStartY;
    private float mEndX;
    private float mEndY;
    private Path mPath;


    public SecondOrderBazier(Context context) {
        this(context, null);
    }

    public SecondOrderBazier(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecondOrderBazier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        mAuxiliaryX = w/2;
        mAuxiliaryY = h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(mStartX, mStartY);
        canvas.drawPoint(mAuxiliaryX, mAuxiliaryY, mAuxiliaryPaint);
        //3个点
        canvas.drawText("辅助点", mAuxiliaryX, mAuxiliaryY, mAuxiliaryTextPaint);
        canvas.drawText("起始点", mStartX, mStartY, mAuxiliaryTextPaint);
        canvas.drawText("结束点", mEndX, mEndY, mAuxiliaryTextPaint);
        //辅助点-结束点  辅助点-起始点
        canvas.drawLine(mAuxiliaryX, mAuxiliaryY, mStartX, mStartY, mAuxiliaryPaint);
        canvas.drawLine(mAuxiliaryX, mAuxiliaryY, mEndX, mEndY, mAuxiliaryPaint);
        //二阶贝塞尔
        mPath.quadTo(mAuxiliaryX, mAuxiliaryY, mEndX, mEndY);
        canvas.drawPath(mPath, mBazierPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:{
                mAuxiliaryX = event.getX();
                mAuxiliaryY = event.getY();
                invalidate();
                break;
            }
            default:
                break;
        }
        return true;
    }
}
