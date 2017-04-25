package com.example.cw.practice.practice.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.cw.practice.R;

/**
 * Created by eengoo on 17/3/12.
 */

//自定义view须知
//    1. 让View支持wrap_content，如果不在onMeasure中对wrap_content做特殊处理，在布局中使用wrap_content时就无法达到预期的效果
//    2. 如果有必要，让View支持padding
//    3. 尽量不要在View中使用Handler，View内部本身就提供了post系列的方法
//    4. View中有线程、动画需要及时停止， onDetachedFromWindow
//    5. View带有滑动嵌套情形时，需要处理好滑动冲突

public class CircleView extends View {
    private static final int color = Color.RED;
    private Paint mPaint;
    private int mColor;

    public CircleView(Context context) {
        super(context);
        initViews();
    }

    private void initViews() {
        //在Values目录下创建自定义属性xml
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取自定义属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.QYCircleView);
        mColor = a.getColor(R.styleable.CircleView_circle_color, color);
        a.recycle();

        initViews();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200,200);
        }else if (widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200, heightSpecSize);
        }else if (heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize, 200);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft -paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        canvas.drawCircle(paddingLeft+width/2, paddingTop+height/2, Math.min(width,height), mPaint);
    }

    //注意点
    //1. 继承子View和ViewGroup的组件，padding是默认无法生效的，需要自己处理
//    2. 对于直接继承View的控件，如果不对wrap_content做特殊处理，那么使用wrap_content就相当于match_parent
}
