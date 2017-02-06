package com.example.cw.practice.common.baserx;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * Created by cw on 2017/2/7.
 */



public class RxBus {

    private static RxBus instance;

    public static synchronized RxBus getInstance(){
        if (instance == null){
            instance = new RxBus();
        }
        return instance;
    }

    private RxBus() {

    }

    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<Object, List<Subject>>();


    /**
     * 订阅事件源
     * @param mObservable
     * @param mAction1
     * @return
     */
    public RxBus onEvent(Observable<?> mObservable, Action1<Object> mAction1){
        mObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(mAction1, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        return getInstance();
    }

    /**
     *这册事件源
     * @param tag
     * @param <T>
     * @return
     */
    public <T> Observable<T> register(@NonNull Object tag){
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList){
            subjectList = new ArrayList<Subject>();
            subjectMapper.put(tag, subjectList);
        }
        Subject<T, T> subject;
        subjectList.add(subject = PublishSubject.create());
        return subject;
    }

    /**
     *
     * 取消监听
     * @param tag
     * @param observable
     * @return
     */
    public RxBus unRegister(@NonNull Object tag, @NonNull Observable<?> observable){
        if (null == observable){
            return getInstance();
        }
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects){
            subjects.remove(observable);
            if (isEmpty(subjects)){
                subjectMapper.remove(tag);
            }
        }
        return getInstance();
    }

    /**
     *  触发事件
     *  @param tag
     * @param content
     */
    public void post(@NonNull Object tag, @NonNull Object content){
        List<Subject> subjectList = subjectMapper.get(tag);
        if (!isEmpty(subjectList)){
            for (Subject subject : subjectList){
                subject.onNext(content);
            }
        }
    }

    public static boolean isEmpty(Collection<Subject> collection) {
        return null == collection || collection.isEmpty();
    }
}
