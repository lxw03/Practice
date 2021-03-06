package com.example.cw.practice.practice.QYViews;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.cw.practice.R;


/**
 * Created by cw on 2017/4/24.
 */

public class QYCircleView extends View {

    private static final int DEFAULT_COLOR = 0xff0bbe06;
    private static final float DEFAULT_PADDING_VERTICAL = 0;
    private static final float STROKE_WIDTH = 4;
    private static final long ANIMATION_DURATION = 2000;
    private static final int DEFAULT_SIZE = 40;
    private static final int DEFAULT_PADDING = 4;

    private Paint mCirclePaint;
    private RectF mCircleRectF;
    private Paint mTrianglePaint;
    private Path mTrianglePath;

    private int mTriangleColor;
    private int mCircleColor;
    private boolean autoAnimation = false;
    private boolean staticPlay = false;
    private float size;
    private float mPaddingVertical;

    private ValueAnimator mValueAnimator;
    private int mAngle = 0;

    private int lastHeight = -1;
    private static final String TAG = "CIRCLE_VIEW";


    public QYCircleView(Context context) {
        this(context, null);
    }

    public QYCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QYCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
//        if (attrs != null){
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.QYCircleView);
            mCircleColor = a.getColor(R.styleable.QYCircleView_rtc_color_circle, DEFAULT_COLOR);
            mTriangleColor = a.getColor(R.styleable.QYCircleView_rtc_color_triangle, DEFAULT_COLOR);
            autoAnimation = a.getBoolean(R.styleable.QYCircleView_rtc_auto_animation, false);
            staticPlay = a.getBoolean(R.styleable.QYCircleView_rtc_static_play, false);
            size = a.getDimensionPixelSize(R.styleable.QYCircleView_rtc_size, dip2px(DEFAULT_SIZE));
            mPaddingVertical = a.getDimensionPixelSize(R.styleable.QYCircleView_rtc_padding_vertical, dip2px(DEFAULT_PADDING_VERTICAL));
            a.recycle();
//        }
        initPaints();
        initAnimations();
        mCircleRectF = new RectF();
        //mCircleRectF.set是必须的
        mCircleRectF.set(0,0, size,size);
        mTrianglePath = new Path();
    }

    private void initPaints() {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(STROKE_WIDTH);

        mTrianglePaint = new Paint();
        mTrianglePaint.setAntiAlias(true);
        mTrianglePaint.setColor(mTriangleColor);
        mTrianglePaint.setStyle(Paint.Style.FILL);
    }

    private void initAnimations() {
        mValueAnimator = ValueAnimator.ofInt(0,720);
        mValueAnimator.setDuration(ANIMATION_DURATION);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAngle = (Integer) animation.getAnimatedValue();
                if (mCircleValueListener !=null){
                    mCircleValueListener.valueCallback((Float) animation.getAnimatedValue());
                }
                if (parentViewInvisible()){
                    reset();
                    return;
                }
                invalidate();
            }
        });
        if (autoAnimation) mValueAnimator.start();
    }

    private boolean parentViewInvisible() {
        return this.getParent() != null && ((View)this.getParent()).getVisibility() == INVISIBLE;
    }

    private void reset(){
        if (mValueAnimator != null){
            mValueAnimator.removeAllUpdateListeners();
            mValueAnimator.cancel();
            mValueAnimator = null;
        }
    }

    //wrap_content时 padding是无效的
    //onMeasure  和  onDraw时处理下
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getPaddingLeft() == 0 || getPaddingRight()==0 || getPaddingTop() ==0 || getPaddingBottom() ==0){
            setPadding(DEFAULT_PADDING, DEFAULT_PADDING,DEFAULT_PADDING,DEFAULT_PADDING);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension((int)size, (int)size);
        }else if (widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension((int)size, heightSize);
        }else if (heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize, (int)size);
        }
        mCircleRectF.set(getPaddingLeft(), getPaddingTop(), getWidth()-getPaddingRight(), getHeight()-getPaddingBottom());
        setTrianglePath();
    }



    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (size >0){
            if (autoAnimation && !parentViewInvisible() && mValueAnimator !=null && !mValueAnimator.isRunning()){
                mValueAnimator.start();
            }
            if (mAngle<360){
                canvas.drawArc(mCircleRectF, -90 , mAngle, false, mCirclePaint);
            }else {
                canvas.drawArc(mCircleRectF, mAngle-360-90, 720-mAngle, false, mCirclePaint);
            }
            if (mAngle<270){
                canvas.save();
                canvas.rotate(mAngle, mCircleRectF.centerX(), mCircleRectF.centerY());
                canvas.drawPath(mTrianglePath, mTrianglePaint);
                canvas.restore();
            }else {
                canvas.drawPath(mTrianglePath, mTrianglePaint);
            }
        }
    }

    //考虑了padding为什么会感觉view上几个点一闪一闪
    //// TODO: 2017/4/24
    private void setTrianglePath(){
        if (mCircleRectF != null){
            float dimen = ((mCircleRectF).width())/4;
            float mRadius = dimen *2;
            mTrianglePath.moveTo(mRadius - dimen/2 + getPaddingLeft(), mRadius - (float) (dimen*Math.sqrt(3)/2) + getPaddingTop());
            mTrianglePath.lineTo(mRadius + dimen  + getPaddingLeft(), mRadius + getPaddingTop());
            mTrianglePath.lineTo(mRadius - dimen/2 + getPaddingLeft(), mRadius + (float) (dimen*Math.sqrt(3)/2) + getPaddingTop());
            mTrianglePath.close();
        }
    }

    private CircleValueListener mCircleValueListener;

    public interface CircleValueListener {
        void valueCallback(float value);
    }

    public void setCircleValueListener(CircleValueListener circleValueListener){
        mCircleValueListener = circleValueListener;
    }

    public static int dip2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, @Nullable int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (autoAnimation){
            if (visibility == GONE || visibility == INVISIBLE){
                reset();
            }else {
                initAnimations();
            }
        }
    }
}
