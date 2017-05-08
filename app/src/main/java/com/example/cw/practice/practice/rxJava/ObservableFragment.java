package com.example.cw.practice.practice.rxJava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cw.practice.R;

import rx.Observable;

/**
 * Created by cw on 2017/5/8.
 */

public class ObservableFragment extends Fragment {

    private AppCompatEditText mAppCompatEditText;
    private Observable stringObservable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rx_observable, container, false);
        mAppCompatEditText = (AppCompatEditText) view.findViewById(R.id.rx_observable_et);
        createObservable();
        return view;
    }

    private void createObservable() {
         stringObservable = Observable.create(e -> mAppCompatEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                e.onNext(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        }));
    }

    public Observable<String> getEditTextObservable(){
        return stringObservable;
    }
}
