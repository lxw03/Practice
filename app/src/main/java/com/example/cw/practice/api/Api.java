package com.example.cw.practice.api;

import retrofit2.Retrofit;

/**
 * Created by cw on 2017/2/18.
 */

public class Api {

    //读超时长
    public static final int READ_TIME_OUT = 7676;
    //连接时长
    public static final int CONNECT_TIME_OUT = 7676;

    public Retrofit mRetrofit;
    public ApiService mApiService;

}
