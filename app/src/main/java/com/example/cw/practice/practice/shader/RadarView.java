package com.example.cw.practice.practice.shader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.example.cw.practice.R;

/**
 * Created by cw on 2017/4/7.
 */

public class RadarView extends View {

    private static final int DEFAULT_HEIGHT = 200;
    private static final int DEFAULT_WIDTH = 200;

    private static final int MSG_WHAT = 10001;
    private static final int POST_DELAY = 20;

    private static final String TAG = "RadarView";

    //雷达扫描开始 结束的颜色 背景色 线条色
    private int startColor = 0x0000ff00;
    private int endColor = 0xaa00ff00;
    private int radarBgColor = Color.BLACK;
    private int radarLineColor = Color.WHITE;
    private int radarCount = 4;
    private int radarRadius;

    private Paint mRadarPaint; //雷达线条画笔
    private Paint mBgPaint;  //雷达底色画笔
    private Shader radarShader;


    private Matrix matrix;

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context, attrs);

        mRadarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRadarPaint.setColor(radarLineColor);
        mRadarPaint.setStyle(Paint.Style.STROKE);
        mRadarPaint.setStrokeWidth(2);

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setColor(radarBgColor);
        mBgPaint.setStyle(Paint.Style.FILL);

        radarShader = new SweepGradient(0, 0, startColor, endColor);

        matrix = new Matrix();
    }

    private void initViews(Context context, AttributeSet attrs) {
        if (attrs != null){
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RadarView);
            startColor = a.getColor(R.styleable.RadarView_startColor, startColor);
            endColor = a.getColor(R.styleable.RadarView_endColor, endColor);
            radarBgColor = a.getColor(R.styleable.RadarView_bgColor, radarBgColor);
            radarLineColor = a.getColor(R.styleable.RadarView_lineColor, radarLineColor);
            radarCount = a.getInt(R.styleable.RadarView_circleCount, radarCount);
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureSize(1, DEFAULT_WIDTH, widthMeasureSpec);
        int height = measureSize(0, DEFAULT_HEIGHT, heightMeasureSpec);
        int measureSize = Math.min(width, height);
        setMeasuredDimension(measureSize, measureSize);
    }

    private int measureSize(int specType, int defaultSize, int measureSpec){
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else {
            result = defaultSize;
            if (specType == 1){
                result += (getPaddingLeft() + getPaddingRight());
            }else {
                result += (getPaddingTop() + getPaddingBottom());
            }
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radarRadius = Math.min(w/2, h/2);
    }

    private int rotateAngel = 0;//旋转的角度
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将画板移到屏幕的中心
        canvas.translate(radarRadius, radarRadius);
        mBgPaint.setShader(null);
        canvas.drawCircle(0,0,radarRadius, mBgPaint);//绘制底色

        for (int i=0; i<=radarCount; i++){
            canvas.drawCircle(0, 0, (float)(i*1.0/radarCount*radarRadius), mRadarPaint);
        }
        canvas.drawLine(-radarRadius, 0 , radarRadius, 0, mRadarPaint);
        canvas.drawLine(0, -radarRadius, 0, radarRadius, mRadarPaint);

//        canvas.rotate(rotateAngel);
        mBgPaint.setShader(radarShader);
        canvas.concat(matrix);
        canvas.drawCircle(0,0,radarRadius, mBgPaint);
    }

    //雷达旋转的方式是旋转canvas
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            rotateAngel += 3;
            invalidate();

            matrix.reset();
            matrix.preRotate(rotateAngel, 0, 0);
            handler.sendEmptyMessageDelayed(MSG_WHAT, POST_DELAY);
        }
    };

    public void startScan(){
        handler.removeMessages(MSG_WHAT);
        handler.sendEmptyMessage(MSG_WHAT);
    }

    public void stopScan(){
        handler.removeMessages(MSG_WHAT);
    }
}
