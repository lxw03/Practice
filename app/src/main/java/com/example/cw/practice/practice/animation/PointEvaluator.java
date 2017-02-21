package com.example.cw.practice.practice.animation;

import android.animation.TypeEvaluator;

/**
 * Created by chenwei on 17/2/21.
 */

//那么TypeEvaluator的作用到底是什么呢？简单来说，就是告诉动画系统如何从初始值过度到结束值
public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPint = (Point) endValue;
        float x = startPoint.getX() + fraction*(endPint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction*(endPint.getY() - startPoint.getY());
        Point point = new Point(x, y);
        return point;
    }
}
