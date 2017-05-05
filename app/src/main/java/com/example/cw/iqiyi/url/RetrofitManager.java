package com.example.cw.iqiyi.url;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cw on 2017/5/4.
 */

public class RetrofitManager {

    private Retrofit mRetrofit;

    private RetrofitManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(ApiConfig.DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS);
        builder.readTimeout(ApiConfig.DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS);
        //添加公共参数拦截器
        HttpCommonInterceptor httpCommonInterceptor = new HttpCommonInterceptor.Builder()
//                .addHeaderParams("token", "1111")
                .build();
//        builder.addInterceptor(httpCommonInterceptor);
        HttpUrlInterceptor httpUrlInterceptor = new HttpUrlInterceptor.Build()
                .showUrlLog(true)
                .build();
        builder.addInterceptor(httpUrlInterceptor);

        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(ApiConfig.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static class SingletonHolder{
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    public static RetrofitManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取对应的service
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }

}
