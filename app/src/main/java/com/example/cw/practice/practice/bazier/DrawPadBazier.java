package com.example.cw.practice.practice.bazier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by cw on 2017/5/11.
 */

public class DrawPadBazier extends View {

    private float mX;
    private float mY;
    private float offset = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    private Paint mPaint;
    private Path mPath;

    public DrawPadBazier(Context context) {
        this(context, null);
    }

    public DrawPadBazier(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawPadBazier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        mPath = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN : {
//                mPath.reset();
                mX = event.getX();
                mY = event.getY();
                mPath.moveTo(mX, mY);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                float x = event.getX();
                float y = event.getY();
                float preX = mX;
                float preY = mX;
                float dx = Math.abs(preX - x);
                float dy = Math.abs(preY - y);
                if (dx>offset && dy>offset){
                    float centerX = (preX + x)/2;
                    float centerY = (preY + y)/2;
                    mPath.quadTo(centerX, centerY, x, y);
//                    mPath.lineTo(x , y);
                    mX = x;
                    mY = y;
                }
                break;
            }
            default:
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }
}
