package com.example.cw.practice.practice.QYViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.example.cw.practice.R;


/**
 * Created by cw on 2017/4/25.
 */

public class StrokeTextView extends AppCompatTextView {

    private static final String TAG = "StrokeTextView";
    private static final int DEFAULT_STROKE_COLOR = Color.BLACK;
    private static final int DEFAULT_STROKE_WIDTH = 5;
    private int mColor;
    private int mStrokeWidth;
    private Paint mPaint;
    private RectF mRectF;
    private int default_width = 100;
    private int default_height = 100;
    private Rect mRect;

    public StrokeTextView(Context context) {
        this(context, null);
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StrokeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        if (attrs != null){
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StrokeTextView);
            mColor = a.getColor(R.styleable.StrokeTextView_stroke_color, DEFAULT_STROKE_COLOR);
            mStrokeWidth = a.getInteger(R.styleable.StrokeTextView_stroke_width, DEFAULT_STROKE_WIDTH);
            a.recycle();
            mRectF = new RectF();
            mPaint = new Paint();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(mColor);
            mPaint.setStrokeWidth(mStrokeWidth);
            mPaint.setAntiAlias(true);
            mRect = new Rect();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        int height = MeasureSpec.getSize(heightMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
//            setMeasuredDimension(default_width,default_height);
//        }else if (widthMode == MeasureSpec.AT_MOST){
//            setMeasuredDimension(default_width, height);
//        }else if (heightMode == MeasureSpec.AT_MOST){
//            setMeasuredDimension(width, default_height);
//        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getPaint().getTextBounds(getText().toString(), 0, getText().toString().length(), mRect);
        int width = mRect.width() + getPaddingLeft() + getPaddingRight();
        int height = mRect.height() + getPaddingTop() + getPaddingBottom() + getFontSpace();
//        mRectF.set(0,0,width,height);
        mRectF.set(0,0,width,height);
        Log.d(TAG, "onMeasure: " + width);
        Log.d(TAG, "onMeasure: " + height);
        Log.d(TAG, "onMeasure: " + getMeasuredWidth());
        Log.d(TAG, "onMeasure: " + getMeasuredHeight());
        Log.d(TAG, "onMeasure: " + getFontSpace());
        canvas.drawRect(mRectF, mPaint);
    }

    private int getFontSpace() {
        Paint.FontMetricsInt fontMetrics = getPaint().getFontMetricsInt();
        Log.d(TAG, "getFontSpace: " + fontMetrics.toString());
        return (fontMetrics.descent- fontMetrics.ascent) / 2 - fontMetrics.descent;
    }
}
