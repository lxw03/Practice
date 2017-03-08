package com.example.cw.practice.practice.imageCache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by eengoo on 17/3/8.
 */

public class BitmapUtil {

    //根据图片实际宽高进行压缩.
    protected Bitmap SampledBitmap(String path, int requiredWidth, int requiredHeight){
        //获得图片宽高并且将图片加载到内存中
        BitmapFactory.Options options = new BitmapFactory.Options();
        //测量宽高，但不需要分配内存
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        options.inSampleSize = calculateInSampleSize(options,requiredWidth,requiredHeight);
        //使用计算后的inSampleSize重新加载图片
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int requiredWidth, int requiredHeight){
        //原图片的宽高
        final int width = options.outWidth;
        final int height = options.outHeight;
        int inSampleSize = 1;
        if (width>requiredWidth || height>requiredHeight){
            final int widthRatio = Math.round((float)width / (float)requiredWidth);
            final int heightRatio = Math.round((float)height/ (float)requiredHeight);
            inSampleSize = Math.max(widthRatio,heightRatio);
        }
        return inSampleSize;
    }

    //下载图片到本地
    public static boolean downloadImgUrl(String urlStr, File file){
        boolean isOk = false;
        FileOutputStream fos = null;
        InputStream is = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = connection.getInputStream();
            fos = new FileOutputStream(file);
            byte[] buf = new byte[1024*4];
            int len = 0;
            while ((len = is.read(buf)) != -1){
                fos.write(buf, 0 , len);
            }
            isOk = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isOk;
    }
}
