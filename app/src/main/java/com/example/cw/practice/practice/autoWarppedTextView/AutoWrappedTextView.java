package com.example.cw.practice.practice.autoWarppedTextView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.example.cw.practice.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cw on 2017/4/18.
 */

public class AutoWrappedTextView extends View {

    private TextPaint mTextPaint;
    private int mTextColor;
    private int mTextSize;
    private int mLineHeight;
    private int mSingleTextWidth;

    private int mPaddingLeft, mPaddingRight, mPaddingTop, mPaddingBottom;

    private StringBuilder mStringBuilder;
    private char[] mTextCharArray;
    private List<String> mTextList;
    private Rect[] mSplitTextRect;

    public AutoWrappedTextView(Context context) {
        super(context);
    }

    public AutoWrappedTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    public AutoWrappedTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context, attrs);
    }

    public AutoWrappedTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoWrappedTextView);
        mTextColor = a.getColor(R.styleable.AutoWrappedTextView_textColor, Color.BLACK);
        mTextSize = a.getInteger(R.styleable.AutoWrappedTextView_textFont, 50);
        mLineHeight = a.getInteger(R.styleable.AutoWrappedTextView_lineHeight, 7);
        mPaddingLeft = a.getInteger(R.styleable.AutoWrappedTextView_paddingLeft, 0);
        mPaddingRight = a.getInteger(R.styleable.AutoWrappedTextView_paddingRight, 0);
        mPaddingTop = a.getInteger(R.styleable.AutoWrappedTextView_paddingTop, 0);
        mPaddingBottom = a.getInteger(R.styleable.AutoWrappedTextView_paddingBottom, 0);

        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        splitText(MeasureSpec.getMode(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText(canvas);
    }

    public void setText(String text){
        if (TextUtils.isEmpty(text)){
            return;
        }
        mTextCharArray = text.toCharArray();
        requestLayout();
    }

    private void splitText(int heightMode) {
        if (mTextCharArray == null){
            return;
        }
        mTextList = new ArrayList<>();
        mSingleTextWidth = getMeasuredWidth() - mPaddingLeft - mPaddingRight;
        int mCurrentTextWidth = 0;
        mTextList = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=0;i<mTextCharArray.length;i++){
            char charText = mTextCharArray[i];
            mCurrentTextWidth += getCharWidth(charText);
            if (mCurrentTextWidth >= mSingleTextWidth){
                mTextList.add(stringBuffer.toString());
                stringBuffer = new StringBuffer();
                mCurrentTextWidth = 0;
                i--;
            }else {
                stringBuffer.append(charText);
                if (i == mTextCharArray.length -1){
                    mTextList.add(stringBuffer.toString());
                }
            }
        }

        int textHeight = 0;
        mSplitTextRect = new Rect[mTextList.size()];
        for (int i=0; i<mTextList.size(); i++){
            Rect lineTextRect = new Rect();
            String lineText = mTextList.get(i);
            mTextPaint.getTextBounds(lineText, 0, lineText.length(), lineTextRect);
            if (heightMode == MeasureSpec.AT_MOST){
                textHeight += lineTextRect.height() + mLineHeight;
                if (i == mTextList.size() -1){
                    textHeight += mPaddingTop + mPaddingBottom;
                }
            }else {
                if (textHeight == 0){
                    textHeight = getMeasuredHeight();
                }
            }
            mSplitTextRect[i] = lineTextRect;
        }

        setMeasuredDimension(getMeasuredWidth(), textHeight);
    }

    private float getCharWidth(char text){
        float[] width = new float[1];
        mTextPaint.getTextWidths(new char[]{text}, 0, 1, width);
        return  width[0];
    }

    private void drawText(Canvas canvas) {
        if (mTextList == null || mTextList.size() ==0){
            return;
        }
        int marginTop = getTopTextMarginTop();
        for (int i=0; i<mTextList.size(); i++){
            String lineText = mTextList.get(i);
            canvas.drawText(lineText, mPaddingLeft, marginTop, mTextPaint);
            marginTop += (mSplitTextRect[i].height() + mLineHeight);
        }
    }

    private int getTopTextMarginTop() {
        return mSplitTextRect[0].height() / 2 + mPaddingTop + getFontSpace();
    }

    private int getFontSpace() {
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        return (fontMetrics.descent- fontMetrics.ascent) / 2 - fontMetrics.descent;
    }

}
