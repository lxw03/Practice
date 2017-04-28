package com.example.cw.practice.practice.QYViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by cw on 2017/4/28.
 */

public class TabLayoutColorText extends AppCompatTextView {

    private static final String TAG = "TabLayoutColorText";
    private static final int DIRECTION_LEFT =0;
    private static final int DIRECTION_RIGHT =1;
    private static final int DEAD_COLOR = Color.BLACK;
    private static final int LIGHT_COLOR = Color.RED;
    private float progress;
    private Paint mPaint;
    private int deadColor = DEAD_COLOR;
    private int lightColor = LIGHT_COLOR;
    private int mDirection;
    private int mTextWidth;
    private int mRealWidth;


    public TabLayoutColorText(Context context) {
        this(context, null);
    }

    public TabLayoutColorText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabLayoutColorText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inits();
    }

    private void inits() {
        mPaint = getPaint();
    }

    public void setProgress(float progress){
        this.progress = progress;
    }

    public void setDirection(int direction){
        mDirection = direction;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mTextWidth = getMeasuredWidth();
        mPaint.measureText(getText().toString());
        //默认居中
        mRealWidth = (int) mPaint.measureText(getText().toString());
        super.onDraw(canvas);
        if (mDirection == DIRECTION_LEFT){
            drawChangeLeft(canvas, progress);
            drawOriginRight(canvas, progress);
        }else {
            drawOriginLeft(canvas, progress);
            drawChangeRight(canvas, progress);
        }
    }

    private void drawChangeRight(Canvas canvas, float progress) {
        drawText(canvas, deadColor,(int) ((1-progress)*mTextWidth + (mTextWidth-mRealWidth)/2) , mRealWidth + (mTextWidth-mRealWidth)/2);
    }

    private void drawOriginLeft(Canvas canvas, float progress) {
        drawText(canvas, lightColor, (mTextWidth-mRealWidth)/2, (int) ((1-progress)*mTextWidth + (mTextWidth-mRealWidth)/2));
    }

    private void drawOriginRight(Canvas canvas, float progress) {
        drawText(canvas, lightColor, (int) (mRealWidth*progress + (mTextWidth - mRealWidth)/2), mRealWidth + (mTextWidth-mRealWidth)/2);
    }

    private void drawChangeLeft(Canvas canvas, float progress) {
        drawText(canvas, deadColor, (mTextWidth - mRealWidth)/2, (int) (mRealWidth*progress + (mTextWidth - mRealWidth)/2));
    }

    protected void drawText(Canvas canvas, int color, int startX, int endX){
        mPaint.setColor(color);
        canvas.save();
        canvas.clipRect(startX ,0,endX,getMeasuredHeight());
        canvas.drawText(getText().toString(), (mTextWidth-mRealWidth)/2,getBaseline(), mPaint);
        canvas.restore();
    }
}
