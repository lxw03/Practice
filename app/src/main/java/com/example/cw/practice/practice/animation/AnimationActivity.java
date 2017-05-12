package com.example.cw.practice.practice.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cw.practice.R;



/**
 * Created by chenwei on 17/2/21.
 */

public class AnimationActivity extends AppCompatActivity {

    private Button mButton, buttonWidthAnimation;
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initView();
        
        initButtonWidthAnimation();
    }

    private void initButtonWidthAnimation() {

        buttonWidthAnimation = (Button) findViewById(R.id.animation_btn2);
        buttonWidthAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofInt(buttonWidthAnimation, "width", 1500).setDuration(2000).start();
            }
        });
    }


    // 属性动画生效条件
    //  1. set get方法来实现， Button控件setWidth源码当中并没有直接操作button的width，所以写一个包装类,间接提供set get方法（如果不满足，会crash）
    //  2. 同时set get对属性做的改变必须能通过某种方法反映出来，比如会带来UI的变化（如果不满足，没动画效果，但不会crash）

    private static class ViewWrapper{
        private View viewTarget;

        public ViewWrapper(View viewTarget) {
            this.viewTarget = viewTarget;
        }

        public void setWidth(int width){
            viewTarget.getLayoutParams().width = width;
            viewTarget.requestLayout();
        }

        public int getWidth(){
            return viewTarget.getLayoutParams().width;
        }
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.animation_btn);
        mTextView = (TextView) findViewById(R.id.animation_txt);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator moveIn = ObjectAnimator.ofFloat(mTextView, "translationX", -500f, 0f);
                ObjectAnimator rotate = ObjectAnimator.ofFloat(mTextView, "rotation", 0f, 360f);
                ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(mTextView, "alpha", 1f, 0f, 1f);
//                mTextView.animate().alpha(0f);
//                mTextView.animate()返回一个ViewPropertyAnimator对象，拿到这个对象就可以调用这个对象的各种方法来实现动画
//                mTextView.animate()
//                        .x(500)
//                        .y(500)
//                        .setDuration(5000)
//                        .setInterpolator(new LinearInterpolator());

                AnimatorSet animSet = new AnimatorSet();
                animSet.play(fadeInOut).with(rotate).after(moveIn);
                animSet.setDuration(5000);
                animSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                animSet.start();
//                after(Animator anim) 将现有动画插入到传入的动画之后执行
//                after(long delay) 将现有动画延迟指定毫秒后执行
//                before(Animator anim) 将现有动画插入到传入的动画之前执行
//                with(Animator anim) 将现有动画和传入的动画同时执行
            }
        });

    }
}
