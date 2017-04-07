package com.example.cw.practice.practice.shader;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by cw on 2017/4/7.
 */

public class LinearGradientText extends TextView {

    private Paint mPaint;
    private Shader mShader;
    private Matrix mMatrix;
    private int mVx;

    public LinearGradientText(Context context) {
        this(context, null);
    }

    public LinearGradientText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearGradientText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        mPaint = getPaint();//获取控件本身的paint
        mMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mShader = new LinearGradient(-2*w,0,-w,0, new int[]{getCurrentTextColor(), Color.RED, Color.BLUE, Color.GREEN, getCurrentTextColor()}, null, Shader.TileMode.CLAMP);
        mPaint.setShader(mShader);
        initAnimator(w);
    }

    private void initAnimator(int w) {
        ValueAnimator animator = ValueAnimator.ofInt(0, 3*w);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mVx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(-1);
        animator.setDuration(2000);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mMatrix.reset();
        mMatrix.preTranslate(mVx,0);
        mShader.setLocalMatrix(mMatrix);
    }
}
