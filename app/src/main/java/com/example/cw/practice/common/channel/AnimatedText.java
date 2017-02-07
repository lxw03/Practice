package com.example.cw.practice.common.channel;

import android.animation.PointFEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.cw.practice.bean.Point;

/**
 * Created by chenwei on 17/2/7.
 */

public class AnimatedText extends TextView implements View.OnClickListener{

    private Point startPoint;
    private Point endPoint;

    private Point currentPoint;


    public AnimatedText(Context context, Point startPoint, Point endPoint){
        super(context);
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public void onClick(View view) {
        ValueAnimator animator = ValueAnimator.ofObject(new PointFEvaluator(), startPoint, endPoint);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentPoint = (Point) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(1000);
        animator.start();
    }
}
