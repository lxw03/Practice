package com.example.cw.practice.practice.faceTest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import com.example.cw.practice.R;

import java.io.IOException;
import java.util.List;

/**
 * Created by eengoo on 17/3/7.
 */

//surfaceView和View最本质的区别在于，surfaceView是在一个新起的单独线程中可以重新绘制画面而View必须在UI线程中更新画面.
//使用surfaceView 由于是在新的线程中更新画面所以不会阻塞UI线程，但也带来了另外一个问题，就是事件同步
public class FaceTestActivity extends AppCompatActivity implements SurfaceHolder.Callback, Camera.FaceDetectionListener{

    private android.hardware.Camera mCamera;
    private SurfaceView mAfterView, mBeforeView;
    private SurfaceHolder mAfterHolder, mBeforeHolder;
    private Paint mPaint;
    private int mViewWidth, mViewHeight;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facetest);
        initPaint();
        setSurfaceViews();
    }

    private void initPaint(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);
    }

    private void setSurfaceViews(){
        //为了不产生相机拉伸，设置布局宽高比
        Display defaultDisplay = getWindow().getWindowManager().getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        mViewWidth = width;
        int height = defaultDisplay.getHeight();
        mViewHeight = height;
//        int height = width * 4/3;
        findViewById(R.id.face_container).setLayoutParams(new FrameLayout.LayoutParams(width,height));

        mAfterView = (SurfaceView) findViewById(R.id.after_view);
        mBeforeView = (SurfaceView) findViewById(R.id.before_view);

        mAfterHolder = mAfterView.getHolder();
        mBeforeHolder = mBeforeView.getHolder();

        //让这个surfaceView处于上层
        mBeforeView.setZOrderOnTop(true);
//        mBeforeView.setZOrderMediaOverlay(true);
        //背景透明，以便显示虾面的内容
        mBeforeHolder.setFormat(PixelFormat.TRANSPARENT);

        mAfterHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mCamera = android.hardware.Camera.open();
        try {
            mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(0, info);
        mCamera.setDisplayOrientation(info.orientation);

        int maxNumDetectedFaces = mCamera.getParameters().getMaxNumDetectedFaces();
        //支持人脸识别
        if (maxNumDetectedFaces > 0){
            mCamera.setFaceDetectionListener(this);
            mCamera.startPreview();
            mCamera.startFaceDetection();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters parameters = mCamera.getParameters();
        List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();//获得相机预览所支持的大小
        Camera.Size size = previewSizes.get(0);
        //打印previewSizes，最后setParmeters会检查
//        for (int i=0; i<previewSizes.size(); i++){
//            Camera.Size size = previewSizes.get(i);
//            Log.d("111111", String.valueOf(size.width));
//            Log.d("111111", String.valueOf(size.height));
//        }
        parameters.setPreviewSize(size.width, size.height);
        mCamera.setParameters(parameters);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCamera.stopFaceDetection();
        mCamera.stopPreview();
        mCamera.release();
    }

    @Override
    public void onFaceDetection(Camera.Face[] faces, Camera camera) {
//        Log.d("111111", String.valueOf(faces.length));

        //锁定surface并拿到canvas
        Canvas canvas = mBeforeHolder.lockCanvas();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);//清除上次的绘制
        if (faces.length < 1){
            mBeforeHolder.unlockCanvasAndPost(canvas);
            return;
        }
        Matrix matrix = new Matrix();
        prepareMatrix(matrix, false, 90, mViewWidth, mViewHeight);

        for (int i=0; i<faces.length; i++){
            RectF rect = new RectF(faces[i].rect);
            matrix.mapRect(rect);
            canvas.drawRect(rect,mPaint);
        }

        //更新canvas并解锁
        mBeforeHolder.unlockCanvasAndPost(canvas);
    }

    //要考虑6.0以上的运行时权限

    public void prepareMatrix(Matrix matrix, boolean isMirror, int displayOrientation, int viewWidth, int viewHeight){
        matrix.setScale(isMirror? -1:1, 1);
        matrix.postRotate(displayOrientation);
        matrix.postScale(viewWidth/ 2000f, viewHeight/2000f);
        matrix.postTranslate(viewWidth/ 2f, viewHeight/2f);
    }

    @Override
    public void onBackPressed() {
        //返回前要停止识别人脸
        mCamera.stopFaceDetection();
        super.onBackPressed();
    }
}
