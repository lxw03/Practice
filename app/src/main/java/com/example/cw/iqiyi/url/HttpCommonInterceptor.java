package com.example.cw.iqiyi.url;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cw on 2017/5/4.
 */

//向请求头添加公共参数

public class HttpCommonInterceptor implements Interceptor{
    private Map<String, String> mHeaderParamMap = new HashMap<>();

    public HttpCommonInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Request.Builder requestBuilder = oldRequest.newBuilder();
        requestBuilder.method(oldRequest.method(), oldRequest.body());
        if (mHeaderParamMap.size() >0){
            for (Map.Entry<String,String> params: mHeaderParamMap.entrySet()){
                requestBuilder.header(params.getKey(), params.getValue());
            }
        }
        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }

    public static class Builder{
        HttpCommonInterceptor mHttpCommonInterceptor;

        public Builder() {
            mHttpCommonInterceptor = new HttpCommonInterceptor();
        }

        public Builder addHeaderParams(String key, String value){
            mHttpCommonInterceptor.mHeaderParamMap.put(key, value);
            return this;
        }

        public HttpCommonInterceptor build(){
            return mHttpCommonInterceptor;
        }
    }
}
