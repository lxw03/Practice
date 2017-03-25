package com.example.cw.practice.practice.download;

/**
 * Created by cw on 2017/3/25.
 */

public interface DownLoadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPause();

    void onCanceled();
}
