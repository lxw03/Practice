package com.example.cw.practice.practice.gotoRNPages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.cw.practice.R;
import com.example.cw.practice.rn.MyMixedActivity;
import com.example.cw.practice.rn.MyNativeActivity;
import com.example.cw.practice.rn.MyPreLoadActivity;
import com.example.cw.practice.rn.MyReactActivity;
import com.example.cw.practice.rn.preload.ReactNativePreLoader;

/**
 * Created by cw on 2017/4/20.
 */

public class GoToRNPages extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "GoToReactActivity";
    private Button activity_btn, fragment_btn, preload_btn, preloadbegin_btn, native_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_rn_pages);
        activity_btn = (Button) findViewById(R.id.go_to_rn_activity_btn);
        fragment_btn = (Button) findViewById(R.id.go_to_rn_fragment_btn);
        preload_btn = (Button) findViewById(R.id.go_to_rn_preLoad_btn);
        preloadbegin_btn = (Button) findViewById(R.id.go_to_rn_preLoadBegin_btn);
        native_btn = (Button) findViewById(R.id.go_to_native_btn);
        activity_btn.setOnClickListener(this);
        fragment_btn.setOnClickListener(this);
        preloadbegin_btn.setOnClickListener(this);
        preload_btn.setOnClickListener(this);
        native_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.go_to_rn_activity_btn:{
                Intent intent = new Intent(GoToRNPages.this, MyReactActivity.class);
                Log.d(TAG, "onClick: " + System.currentTimeMillis());
                startActivity(intent);
                break;
                //react-native bundle --platform android --dev false --entry-file index.android.js --bundle-output app/src/main/assets/index.android.bundle --assets-dest app/src/main/res/
            }
            case R.id.go_to_rn_fragment_btn:{
                Intent intent = new Intent(GoToRNPages.this, MyMixedActivity.class);
                Log.d(TAG, "onClick: " + System.currentTimeMillis());
                startActivity(intent);
                break;
            }
            case R.id.go_to_rn_preLoad_btn:{
                Intent intent = new Intent(GoToRNPages.this, MyPreLoadActivity.class);
                Log.d(TAG, "onClick: " + System.currentTimeMillis());
                startActivity(intent);
                break;
            }
            case R.id.go_to_rn_preLoadBegin_btn:{
                ReactNativePreLoader.preLoad(GoToRNPages.this, "PreLoad");
                Log.d(TAG, "PreLoad: " + System.currentTimeMillis());
                break;
            }
            case R.id.go_to_native_btn:{
                Intent intent = new Intent(this, MyNativeActivity.class);
                Log.d(TAG, "onClick: " + System.currentTimeMillis());
                startActivity(intent);
            }
            default:
                break;
        }
    }
}
