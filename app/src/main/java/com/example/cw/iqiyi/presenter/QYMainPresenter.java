package com.example.cw.iqiyi.presenter;

import android.os.Bundle;
import android.view.View;

import com.example.cw.iqiyi.model.MovieSubjects;
import com.example.cw.iqiyi.mvp.QYContract;
import com.example.cw.iqiyi.url.ApiConfig;
import com.example.cw.iqiyi.url.RetrofitManager;
import com.example.cw.iqiyi.url.service.MovieService;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by cw on 2017/5/4.
 */

public class QYMainPresenter implements QYContract.IPresenter {

    private static final String TAG = "QYMainPresenter";
    protected MovieService mMovieService;
    private WeakReference<QYContract.IView> mViews;
    public QYMainPresenter(QYContract.IView view) {
        mViews = new WeakReference<>(view);
    }

    @Override
    public void onCreate(Bundle args) {
        mMovieService = RetrofitManager.getInstance().create(MovieService.class);
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
        mMovieService.getSocial(ApiConfig.BASE_KEY, 10)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieSubjects>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MovieSubjects movieSubjects) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void retryLoad() {
        initTabs();
    }
}
