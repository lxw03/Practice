package com.example.cw.practice.practice.windowManager;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by eengoo on 17/3/10.
 */

public class WindowManagerActivity extends AppCompatActivity {

    private Button mFloatingButton;
    private WindowManager.LayoutParams mLayoutParams;
    private WindowManager mWindowManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addOneButton();
    }

    //通过windowManager  添加window
    private void addOneButton() {
        mFloatingButton = new Button(this);
        mFloatingButton.setText("button");
        mLayoutParams = new WindowManager.LayoutParams(100, 100, 0, 0, PixelFormat.TRANSPARENT);
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 100;
        mLayoutParams.y = 300;
        //系统层级有很多，一般选用下面2种
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
//        mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        mWindowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.addView(mFloatingButton, mLayoutParams);

        mFloatingButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int rawX = (int) event.getRawX();
                int rawY = (int) event.getRawY();
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:{
                        mLayoutParams.x = rawX;
                        mLayoutParams.y = rawY;
                        mWindowManager.updateViewLayout(mFloatingButton, mLayoutParams);
                        break;
                    }
                    default:
                        break;
                }
                return false;
            }
        });
    }

    //window有三种类型：
    // 应用window， 层级范围1-99
    // 子window，   1000-1999
    // 系统window   2000-2999
    //windowManager : addView, updateView, removeView
    //每一个window都对应着一个view和ViewRootImpl,window 通过view和viewRootImpl来建立联系，window并不是实际存在的，view才是window存在的实体
}
