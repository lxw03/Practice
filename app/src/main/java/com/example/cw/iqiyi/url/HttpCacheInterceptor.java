package com.example.cw.iqiyi.url;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cw on 2017/5/5.
 */

public class HttpCacheInterceptor implements Interceptor {

    //后端返回消息有"Cache-Control:max-age=60"等信息，浏览器这边据此进行缓存
    //okhttp通过设置拦截器可以自己控制 Cache-Control

    //但okhttp官方建议使用CacheControl来进行缓存控制

    //无缓存
    CacheControl noCacheControl = new CacheControl.Builder()
            .noCache().build();
    //限时缓存
    CacheControl ageControl = new CacheControl.Builder()
            .maxAge(60, TimeUnit.SECONDS).build();
    //只用缓存
    CacheControl onlyCache = new CacheControl.Builder()
            .onlyIfCached().build();

    CacheControl staleCache = new CacheControl.Builder()
            .maxStale(60*5, TimeUnit.SECONDS).build();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Request.Builder requestBuilder = oldRequest.newBuilder().cacheControl(ageControl);
        requestBuilder.method(oldRequest.method(), oldRequest.body());
        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }

    public static class Build {
        HttpCacheInterceptor mHttpCacheInterceptor;

        public Build() {
            mHttpCacheInterceptor = new HttpCacheInterceptor();
        }

        public HttpCacheInterceptor build(){
            return mHttpCacheInterceptor;
        }
    }
}
