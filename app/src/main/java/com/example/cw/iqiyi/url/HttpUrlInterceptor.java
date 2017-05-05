package com.example.cw.iqiyi.url;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;


/**
 * Created by cw on 2017/5/5.
 */

public class HttpUrlInterceptor implements Interceptor {

    private static final String TAG = "HttpUrlInterceptor";
    private boolean show;

    public HttpUrlInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (show){
//            String body = chain.request().body().toString();
            String url = chain.request().url().toString();
//            String headers = chain.request().headers().toString();
//            Log.d(TAG, "intercept: " + body);
            Log.d(TAG, "intercept: " + url);
//            Log.d(TAG, "intercept: " + headers);
        }
        return chain.proceed(chain.request());
    }

    public static class Build {
        HttpUrlInterceptor mHttpUrlInterceptor;

        public Build() {
            mHttpUrlInterceptor = new HttpUrlInterceptor();
        }

        public Build showUrlLog(boolean show){
            mHttpUrlInterceptor.show = show;
            return this;
        }

        public HttpUrlInterceptor build(){
            return mHttpUrlInterceptor;
        }
    }
}
