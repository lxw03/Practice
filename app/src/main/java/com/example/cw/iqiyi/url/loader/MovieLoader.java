package com.example.cw.iqiyi.url.loader;

import com.example.cw.iqiyi.model.MovieSubjects;
import com.example.cw.iqiyi.url.RetrofitManager;
import com.example.cw.iqiyi.url.service.MovieService;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by cw on 2017/5/4.
 */

public class MovieLoader extends ObjectLoader {

    private MovieService movieService;

    public MovieLoader() {
        movieService = RetrofitManager.getInstance().create(MovieService.class);
    }

    public Observable<MovieSubjects> getMovie(String key, int num){
        return observe(movieService.getSocial(key, num))
                .map(new Func1<MovieSubjects, MovieSubjects>() {
                    @Override
                    public MovieSubjects call(MovieSubjects movieSubjects) {
                        return movieSubjects;
                    }
                });
    }
}
