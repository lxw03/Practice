package com.example.cw.practice.practice.textureView;

import android.animation.ValueAnimator;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.example.cw.practice.R;

import java.io.IOException;

/**
 * Created by cw on 2017/4/28.
 */

public class TextureViewPractice extends AppCompatActivity implements TextureView.SurfaceTextureListener, View.OnClickListener{

    private static final String TAG = TextureViewPractice.class.getName();
    private TextureView mTextureView;
    private Camera mCamera;
    private Button mButton;
    private ValueAnimator mValueAnimator;
    private ViewStub mViewStub;
    private TextureView smallTextureView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_texture_view);
        mTextureView = (TextureView) findViewById(R.id.texture_view);
        mTextureView.setRotation(90.0f);
        mButton = (Button) findViewById(R.id.texture_btn);
        mButton.setOnClickListener(this);
        initAnimation();
    }

    private void initAnimation() {
        mValueAnimator = ValueAnimator.ofFloat(0,1);
        mValueAnimator.setDuration(1000);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
            }
        });
    }

    private void lazyLoadView(){
        mViewStub = (ViewStub) findViewById(R.id.texture_viewstub);
        View view = mViewStub.inflate();
        smallTextureView = (TextureView) view.findViewById(R.id.view_stub_texture);
        smallTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                mTextureView.setVisibility(View.INVISIBLE);
                if (mCamera != null){
                    mCamera = Camera.open();
                }
                try {
                    mCamera.setPreviewTexture(surface);
                    mCamera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                mCamera.startPreview();
                mCamera.release();
                return true;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mCamera = Camera.open();
        Camera.Size size = mCamera.getParameters().getPreviewSize();
        Log.d(TAG, "onSurfaceTextureAvailable: " + size.width + ":" + size.height);
        mTextureView.setLayoutParams(new ViewGroup.LayoutParams(mTextureView.getLayoutParams().width, mTextureView.getLayoutParams().width * size.width/size.height));
        try {
            mCamera.setPreviewTexture(surface);
            mCamera.startPreview();
        }catch (IOException e){
            Log.d(TAG, "onSurfaceTextureAvailable: " + e);
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        mCamera.stopPreview();
        mCamera.release();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.texture_btn: {
                lazyLoadView();
                break;
            }
            default:
                break;
        }
    }
}
