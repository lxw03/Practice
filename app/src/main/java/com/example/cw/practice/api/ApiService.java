package com.example.cw.practice.api;

import com.example.cw.practice.bean.HttpResult;
import com.example.cw.practice.bean.Subject;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by cw on 2017/2/18.
 */

public interface ApiService {

    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
