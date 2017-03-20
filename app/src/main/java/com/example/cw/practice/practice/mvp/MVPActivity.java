package com.example.cw.practice.practice.mvp;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by eengoo on 17/3/17.
 */

//1. 根据项目需求，写一个XXView接口，然后让对应的Activity/Fragment实现这个接口，View层基本搞定.
//2. 编写Model层，主要就是网络数据请求或者其它什么耗时操作，最后一定要调用Presenter层定义的接口，回调给Presenter通知View层更新数据
//3. 编写Presenter层，Presenter层需要持有View层和Model层的引用，并且实现Presenter层定义的回调接口，在回调接口中调用View层的代码进行界面更新，
//    最重要的事有一个调用通过Model层的方法，在此方法中，调用Model层请求数据。

public class MVPActivity extends AppCompatActivity {

}
