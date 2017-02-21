package com.example.cw.practice.api;

import com.example.cw.practice.bean.HttpResult;
import com.example.cw.practice.bean.NewsSummary;
import com.example.cw.practice.bean.Subject;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by cw on 2017/2/18.
 */

public interface ApiService {

    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);

    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<Map<String, List<NewsSummary>>> getNewsList(
            @Header("Cache-Control") String cacheControl,
            @Path("type") String type, @Path("id") String id,
            @Path("startPage") int startPage);
}
