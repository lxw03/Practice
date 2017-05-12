package com.example.cw.practice.practice.bazier;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.cw.practice.R;
import com.example.cw.practice.util.BitmapUtil;

/**
 * Created by cw on 2017/5/12.
 */

public class BazierLeafView extends View{

    private static final int DEFAULT_HEIGHT = 100;
    private Paint mPaint;
    private Path mPath;
    private Point screenPoint;
    private ValueAnimator rotateAnimation;
    private int rotateAngle;
    private int width;
    private int height;
    private PointF bazierPoint;
    private float bazierT;

    public BazierLeafView(Context context) {
        this(context, null);
    }

    public BazierLeafView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BazierLeafView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPath = new Path();
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        WindowUtil.getScreenSize(screenPoint);
//
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        if (heightMode == MeasureSpec.AT_MOST){
//            heightSize = DEFAULT_HEIGHT;
//        }
//        if (widthMode == MeasureSpec.AT_MOST){
//            widthSize = screenPoint.y;
//        }
//        setMeasuredDimension(heightSize, widthSize);
//    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPath.reset();
//        mPath.moveTo(0, height/2);

        bazierT = rotateAngle/360f;
        if (rotateAngle < 180f) {
            bazierPoint = BazierUtil.CalculateBezierPointForQuadratic(bazierT * 2, new PointF(0, height / 2), new PointF(width / 4, height / 2 + 100), new PointF(width / 2, height / 2));
        }else {
            bazierPoint = BazierUtil.CalculateBezierPointForQuadratic((bazierT -0.5f) *2, new PointF(width/2, height / 2), new PointF(width *3/ 4, height / 2 - 100), new PointF(width, height / 2));
        }
//        canvas.save();
//        canvas.rotate(rotateAngle);
        Bitmap bitmap = BitmapUtil.rotateBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_girl_normal), rotateAngle);
        canvas.drawBitmap(bitmap, bazierPoint.x, bazierPoint.y, mPaint);
//        canvas.restore();
    }

    private void initRotateAnimation(){
        if (rotateAnimation == null) {
            rotateAnimation = ValueAnimator.ofInt(0, 360);
        }
        rotateAnimation.setDuration(5000);
        rotateAnimation.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimation.addUpdateListener(value -> {
            rotateAngle = (int) value.getAnimatedValue();
            postInvalidate();
        });
        rotateAnimation.start();
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == View.VISIBLE){
            initRotateAnimation();
        }else {
            rotateAnimation.cancel();
            rotateAnimation = null;
        }
    }
}
