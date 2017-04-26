package com.example.cw.practice.practice.danmaku;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.example.cw.practice.R;
import com.example.cw.practice.util.PixelUtil;
import com.example.cw.practice.util.StatusBarUtil;

import java.util.Random;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * Created by cw on 2017/2/26.
 */

public class DanmakuActivity extends AppCompatActivity {

    private VideoView mVideoView;
    private EditText mEditText;
    private Button mSend;
    private LinearLayout mOperationLayout;

    private boolean showDanmuku;
    private DanmakuView danmakuView;
    private DanmakuContext danmakuContext;

    private BaseDanmakuParser parse = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damaku);
        initViews();
    }

    private void initViews() {
        mVideoView = (VideoView) findViewById(R.id.danmaku_videoview);
        mVideoView.setVideoPath("http://vhotwsh.video.qq.com/flv/170/153/7L52ZRYXXok.mp4");
        mVideoView.start();
        danmakuView = (DanmakuView) findViewById(R.id.damaku);
        danmakuView.enableDanmakuDrawingCache(true);
        danmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                showDanmuku = true;
                danmakuView.start();
                generateSomeDanmaku();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        danmakuContext = DanmakuContext.create();
        danmakuView.prepare(parse, danmakuContext);

        mOperationLayout = (LinearLayout) findViewById(R.id.damaku_operation);
        mEditText = (EditText) findViewById(R.id.danmaku_edittext);
        mSend = (Button) findViewById(R.id.danmaku_send);

        danmakuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOperationLayout.getVisibility() == View.GONE){
                    mOperationLayout.setVisibility(View.VISIBLE);
                }else {
                    mOperationLayout.setVisibility(View.GONE);
                }
            }
        });
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(mEditText.getText().toString())){
                    addDanmaku(mEditText.getText().toString(), true);
                    mEditText.setText("");
                }
            }
        });

        //键盘弹出时丢失焦点，从而退出沉浸式，因而加入监听
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == View.SYSTEM_UI_FLAG_VISIBLE){
                    onWindowFocusChanged(true);
                }
            }
        });
    }

    private void generateSomeDanmaku() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (showDanmuku){
                    int time = new Random().nextInt(300);
                    String content = time + time + "";
                    addDanmaku(content, false);
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    //添加弹幕
    private void addDanmaku(String content, boolean withBorder) {
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = content;
        danmaku.padding = 5;
        danmaku.textSize = PixelUtil.dip2px(20);
        danmaku.textColor = Color.WHITE;
        danmaku.setTime(danmakuView.getCurrentTime());
        if (withBorder){
            danmaku.borderColor = Color.GREEN;
        }
        danmakuView.addDanmaku(danmaku);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            StatusBarUtil.setStatusBarImmerse(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (danmakuView != null && danmakuView.isPrepared()){
            danmakuView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (danmakuView != null && danmakuView.isPrepared() && danmakuView.isPaused()){
            danmakuView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showDanmuku = false;
        if (danmakuView !=null){
            danmakuView.release();
            danmakuView = null;
        }
    }
}
