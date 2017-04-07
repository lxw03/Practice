package com.example.cw.practice.practice.SecurityCode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cw.practice.R;

/**
 * Created by cw on 2017/3/28.
 */

public class SecurityCodeActivity extends AppCompatActivity implements SecurityCodeView.InputCompleteListener{

    private SecurityCodeView securityCodeView;
    private TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_code);
        initViews();
    }

    private void initViews() {
        securityCodeView = (SecurityCodeView) findViewById(R.id.security_code_view);
        securityCodeView.setInputCompleteListener(this);
        text = (TextView) findViewById(R.id.security_code_textview);
    }

    @Override
    public void inputComplete() {
        Toast.makeText(this, "输入完毕", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteContent(boolean isDelete) {

    }
}
