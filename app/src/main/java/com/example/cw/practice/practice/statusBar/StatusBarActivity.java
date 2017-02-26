package com.example.cw.practice.practice.statusBar;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.example.cw.practice.R;
import com.example.cw.practice.util.StatusBarUtil;

/**
 * Created by cw on 2017/2/26.
 */

public class StatusBarActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_statusbar);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            StatusBarUtil.setStatusBarImmerse(this);
        }
    }
}
