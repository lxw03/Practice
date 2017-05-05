package com.example.cw.iqiyi.url.service;

import com.example.cw.iqiyi.model.MovieSubject;
import com.example.cw.iqiyi.model.MovieSubjects;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by cw on 2017/5/4.
 */

public interface MovieService {

    @Headers("Cache-Control: public, max-age=")
    @GET("social")
    Observable<MovieSubjects> getSocial(@Query("key") String key, @Query("num") int num);

    @GET("tiyu")
    Observable<MovieSubjects> getTiyu(@Query("key") String key, @Query("num") int num);

    @FormUrlEncoded
    @POST("top")
    Observable<MovieSubject> postTop(@Query("start") int start, @Query("end") int end);
}
