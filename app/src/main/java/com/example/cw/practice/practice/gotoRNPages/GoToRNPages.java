package com.example.cw.practice.practice.gotoRNPages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.cw.practice.R;
import com.example.cw.practice.rn.MyReactActivity;

/**
 * Created by cw on 2017/4/20.
 */

public class GoToRNPages extends AppCompatActivity implements View.OnClickListener{

    private Button activity_btn, fragment_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_rn_pages);
        activity_btn = (Button) findViewById(R.id.go_to_rn_activity_btn);
        fragment_btn = (Button) findViewById(R.id.go_to_rn_fragment_btn);
        activity_btn.setOnClickListener(this);
        fragment_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.go_to_rn_activity_btn:{
                Intent intent = new Intent(GoToRNPages.this, MyReactActivity.class);
                startActivity(intent);
                //react-native bundle --platform android --dev false --entry-file index.android.js --bundle-output app/src/main/assets/index.android.bundle --assets-dest app/src/main/res/
            }
            default:
                break;
        }
    }
}
