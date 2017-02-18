package com.example.cw.practice.api;

import android.support.annotation.Nullable;

import com.example.cw.practice.base.ActivityLifeCycleEvent;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * Created by cw on 2017/2/18.
 */

public class RxHelper {

    public <T> Observable.Transformer<T,T> bindUtilEvent(@Nullable final ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifeCycleSubject){
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                Observable<ActivityLifeCycleEvent> compareLifeCycleObservable = lifeCycleSubject.takeFirst(new Func1<ActivityLifeCycleEvent, Boolean>() {
                    @Override
                    public Boolean call(ActivityLifeCycleEvent activityLifeCycleEvent) {
                        return activityLifeCycleEvent.equals(event);
                    }
                });
                return tObservable.takeUntil(compareLifeCycleObservable);
            }
        };
    }
}
