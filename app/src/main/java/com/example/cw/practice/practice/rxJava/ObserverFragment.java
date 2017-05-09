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

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;



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
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).map(integer -> integer.toString()).subscribe(s -> Log.d(TAG, s));
    }
}
