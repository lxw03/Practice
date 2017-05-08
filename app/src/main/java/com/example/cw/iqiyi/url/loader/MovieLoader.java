package com.example.cw.iqiyi.url.loader;

import com.example.cw.iqiyi.url.RetrofitManager;
import com.example.cw.iqiyi.url.service.MovieService;

/**
 * Created by cw on 2017/5/4.
 */

public class MovieLoader extends ObjectLoader {

    private MovieService movieService;

    public MovieLoader() {
        movieService = RetrofitManager.getInstance().create(MovieService.class);
    }

}
