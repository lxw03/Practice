package com.example.cw.iqiyi.presenter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.cw.iqiyi.model.MovieSubjects;
import com.example.cw.iqiyi.mvp.QYContract;
import com.example.cw.iqiyi.url.ApiConfig;
import com.example.cw.iqiyi.url.loader.MovieLoader;

import java.lang.ref.WeakReference;

import rx.functions.Action1;


/**
 * Created by cw on 2017/5/4.
 */

public class QYMainPresenter implements QYContract.IPresenter {

    private static final String TAG = "QYMainPresenter";
    private WeakReference<QYContract.IView> mViews;
    private MovieLoader mMovieLoader;
    public QYMainPresenter(QYContract.IView view) {
        mViews = new WeakReference<>(view);
    }

    @Override
    public void onCreate(Bundle args) {
        mMovieLoader = new MovieLoader();
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void initTabs() {
        final QYContract.IView view = mViews.get();
        view.showOrHideLoadingView(true);
        //请求数据
        mMovieLoader.getMovie(ApiConfig.BASE_KEY, 10)
                .subscribe(new Action1<MovieSubjects>() {
                    @Override
                    public void call(MovieSubjects movieSubjects) {
                        int size = movieSubjects.getNewslist().size();
                        for (int i=0;i<size;i++){
                            Log.d(TAG, "call: " + movieSubjects.getNewslist().get(i).getTitle());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d(TAG, "call: " + throwable);
                        view.showOrHideLoadingView(false);
                        view.showExceptionTips(true);
                    }
                });
//
    }

    @Override
    public void retryLoad() {
        initTabs();
    }
}
