package com.example.cw.practice.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by cw on 2017/2/18.
 */

public class Api {
    private static ApiService SERVICE;

    /**
     * 请求超时
     */
    private static final int DEAFULT_TIMEOUT = 10*1000;

    public static ApiService getDefault(){
        if (SERVICE == null){
            //手动创建一个http并设置超时
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.connectTimeout(DEAFULT_TIMEOUT, TimeUnit.MILLISECONDS);

            //对所有请求添加请求头
            httpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();

                    HttpUrl.Builder authorizedUrlBuilder = request.url()
                            .newBuilder()
                            //添加统一参数，如token
                            .addQueryParameter("key1", "value1")
                            .addQueryParameter("key2", "value2");

                    Request newRequest = request.newBuilder()
                            //对所有请求添加请求头
                            .header("mobileFlag", "abc").addHeader("type", "4")
                            .method(request.method(), request.body())
                            .url(authorizedUrlBuilder.build())
                            .build();

                    return chain.proceed(newRequest);
                }
            });

            SERVICE = new Retrofit.Builder()
                    .client(httpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(com.example.cw.practice.api.Url.BASE_URL)
                    .build().create(ApiService.class);
        }
        return SERVICE;
    }
}
