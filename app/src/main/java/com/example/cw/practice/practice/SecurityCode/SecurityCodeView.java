package com.example.cw.practice.practice.SecurityCode;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cw.practice.R;

/**
 * Created by cw on 2017/3/28.
 */

public class SecurityCodeView extends RelativeLayout{

    private EditText editText;
    private TextView[] textViews;
    private StringBuffer stringBuffer = new StringBuffer();
    private int count = 4;
    private String inputContent;
    private InputCompleteListener inputCompleteListener;
    private Handler mHandler;
    private static final int CLEAR_TEXTVIEWS =1;

    public SecurityCodeView(Context context) {
        this(context, null);
    }

    public SecurityCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecurityCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        textViews = new TextView[4];
        View.inflate(context, R.layout.view_security_code, this);
        editText = (EditText) findViewById(R.id.item_edittext);
        textViews[0] = (TextView) findViewById(R.id.item_code_iv1);
        textViews[1] = (TextView) findViewById(R.id.item_code_iv2);
        textViews[2] = (TextView) findViewById(R.id.item_code_iv3);
        textViews[3] = (TextView) findViewById(R.id.item_code_iv4);

        editText.setCursorVisible(false);
        setListener();

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case CLEAR_TEXTVIEWS:{
                        clearTextViews();
                        break;
                    }
                    default:
                        break;
                }
            }
        };
    }

    private void setListener() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")){
                    if (stringBuffer.length()>3){
                        editText.setText("");
                        return;
                    }else {
                        stringBuffer.append(s);
                        editText.setText("");
                        count = stringBuffer.length();
                        inputContent = stringBuffer.toString();
                        if (stringBuffer.length() == 4){
                            if (inputCompleteListener != null){
                                inputCompleteListener.inputComplete();
                                Message message = new Message();
                                message.what = CLEAR_TEXTVIEWS;
                                mHandler.sendMessageDelayed(message,1000);
                            }
                        }
                    }

                    for (int i=0; i<stringBuffer.length(); i++){
                        textViews[i].setText(String.valueOf(inputContent.charAt(i)));
                        textViews[i].setBackground(getResources().getDrawable(R.drawable.security_code_input));
                    }
                }
            }
        });

        editText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return false;
            }
        });
    }

    private void clearTextViews(){
        for (int i=0;i<4;i++){
            textViews[i].setText("");
            textViews[i].setBackground(getResources().getDrawable(R.drawable.security_code_noinput));
        }
    }

    public void setInputCompleteListener(InputCompleteListener inputCompleteListener){
        this.inputCompleteListener = inputCompleteListener;
    }

    public interface InputCompleteListener{
        void inputComplete();

        void deleteContent(boolean isDelete);
    }
}
