package com.example.cw.practice.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by cw on 2017/5/12.
 */

public class BitmapUtil {

    public static Bitmap rotateBitmap(Bitmap bitmap, float angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0,0,bitmap.getWidth(), bitmap.getHeight(),matrix,true);
    }
}
