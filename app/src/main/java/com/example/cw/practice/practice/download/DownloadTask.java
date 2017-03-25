package com.example.cw.practice.practice.download;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by cw on 2017/3/25.
 */

public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    private static final int TYPE_SUCCESS = 0;
    private static final int TYPE_FAILED = 1;
    private static final int TYPE_PAUSED = 2;
    private static final int TYPE_CANCELED = 3;

    private DownLoadListener mDownLoadListener;
    private boolean isPaused = false;
    private boolean isCanceled = false;
    private int lastProgress;

    public DownloadTask(DownLoadListener downLoadListener) {
        mDownLoadListener = downLoadListener;
    }

    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file= null;
        try {
            long downloadLength = 0; //记录下载的文件长度
            String downloadUrl = params[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            Log.d(TAG, "doInBackground: " + fileName);
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);
            if (file.exists()){
                downloadLength = file.length();
            }
            long contentLength = getContentLength(downloadUrl);
            if (contentLength ==0){
                return TYPE_FAILED;
            }else if (contentLength == downloadLength){
                return TYPE_SUCCESS;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE", "bytes="+ downloadLength + "-")//指定从哪个字节开始下载
                    .url(downloadUrl)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if (response != null){
                    is = response.body().byteStream();
                    savedFile = new RandomAccessFile(file, "rw");
                    savedFile.seek(downloadLength); //跳过已经下载的字节
                    byte[] b = new byte[1024];
                    int total = 0;
                    int len;
                    while ((len = is.read(b)) != -1){
                        if (isCanceled){
                            return TYPE_CANCELED;
                        }else if (isPaused){
                            return TYPE_PAUSED;
                        }else {
                            total += len;
                        }
                        savedFile.write(b,0,len);
                        //计算已下载的百分比
                        int progress = (int) ((total + downloadLength)*100/contentLength);
                        publishProgress(progress);
                    }
                    response.body().close();
                    return TYPE_SUCCESS;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (is != null){
                    is.close();
                }
                if (savedFile != null){
                    savedFile.close();
                }
                if (isCanceled && file !=null){
                    file.delete();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress){
            mDownLoadListener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer result) {
        switch (result){
            case TYPE_SUCCESS:
                mDownLoadListener.onSuccess();
                break;
            case TYPE_FAILED:
                mDownLoadListener.onFailed();
                break;
            case TYPE_PAUSED:
                mDownLoadListener.onPause();
                break;
            case TYPE_CANCELED:
                mDownLoadListener.onCanceled();
                break;
            default:
                break;
        }
    }

    private Long getContentLength(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response != null && response.isSuccessful()){
                long contentLength = response.body().contentLength();
                response.close();
                return contentLength;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public void pauseDownload(){
        isPaused = true;
    }

    public void cancelDownload(){
        isCanceled = true;
    }
}
