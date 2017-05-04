package com.example.cw.iqiyi.base;

import android.os.Bundle;
import android.view.View;

/**
 * Created by cw on 2017/5/3.
 */

public interface IBasePresenter {

    void onCreate(Bundle args);

    void onCreateView(Bundle savedInstanceState);

    void onViewCreated(View view, Bundle savedInstanceState);

    void onResume();

    void onPause();

    void onDestroy();
}
