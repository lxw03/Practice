package com.example.cw.practice.practice.rxJava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cw.practice.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by cw on 2017/5/8.
 */

public class ObserverFragment extends Fragment {

    private AppCompatTextView mAppCompatTextView;
    private static final String TAG = "ObserverFragment";

    @Override
    public void onStart() {
        super.onStart();
        ObservableFragment fragment = (ObservableFragment) getFragmentManager().findFragmentByTag("ObservableFragment");
        if (fragment != null){
            fragment.getEditTextObservable().subscribe(s -> mAppCompatTextView.setText(s));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rx_observer, container, false);
        mAppCompatTextView = (AppCompatTextView) view.findViewById(R.id.rx_observer_tv);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void test() {

        //map
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            e.onNext(1);
            e.onNext(2);
            e.onNext(3);
        }).map(integer -> integer.toString()).subscribe(s -> Log.d(TAG, s));

        //flatMap
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            e.onNext(1);
            e.onNext(2);
            e.onNext(3);
        }).flatMap((Function<Integer, ObservableSource<?>>) integer -> {
            //flatMap将每个事件转换成多个事件  结果无序
            final List<String> list = new ArrayList<>();
            for (int i=0; i<3; i++){
                list.add("I am value " + integer);
            }
            return Observable.fromIterable(list).delay(20, TimeUnit.MILLISECONDS);
        }).subscribe(s -> Log.d(TAG, "accept: " +s));

        //concatMap
        Observable.create((ObservableOnSubscribe<Integer>) e ->{
            e.onNext(1);
            e.onNext(2);
            e.onNext(3);
        }).concatMap((Function<Integer, ObservableSource<?>>) integer -> {
            //类似flatMap，有序
            final List<String> list = new ArrayList<>();
            for (int i=0; i<3; i++){
                list.add("I am Value " + integer);
            }
            return Observable.fromIterable(list).delay(20, TimeUnit.MILLISECONDS);
        }).subscribe(s -> Log.d(TAG, "test: "+ s));

        //zip
        //按顺序组合Observable，只发射与发射数据项最少的那个Observable一样多的数据
        Observable<Integer> observable1 = Observable.create((ObservableOnSubscribe<Integer>) e -> {
            e.onNext(1);
            e.onNext(2);
            e.onNext(3);
        }).subscribeOn(Schedulers.io());
        Observable<String> observable2 = Observable.create((ObservableOnSubscribe<String>) e -> {
            e.onNext("A");
            e.onNext("B");
            e.onNext("C");
            e.onNext("D");
        }).subscribeOn(Schedulers.io());
        // 上下游在同一个线程  同步订阅
        // 上下游不在同一个线程  异步订阅
        Observable.zip(observable1, observable2, (integer, s) -> integer + s)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.d(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        //BackPressure  上下游流速不均衡时如何处理
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            for (int i=0; ;i++){
                e.onNext(i);
            }
        }).subscribeOn(Schedulers.io())
                .filter(integer -> integer%10 ==0)
                //.sample(2000, TimeUnit.MILLISECONDS) //sample操作符
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> Log.d(TAG, "test: "+ integer));


        //Observable - Observer  vs   Flowable - Subscriber
        //Flowable 响应式拉取,request当作是下游处理事件的能力，这样上游根据下游的能力发送事件，
        //就不会出现Observable-Observer发出一堆事件导致OOM的情况
        //上下游不在同一个线程，Flowable里默认有一个大小128的水缸
        Flowable<Integer> flowable = Flowable.create(e->{
            e.onNext(1);
            e.onNext(2);
            e.onNext(3);
        }, BackpressureStrategy.ERROR);
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
//                s.request(1);
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);


        Flowable.interval(1, TimeUnit.MICROSECONDS)
                .onBackpressureDrop()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
