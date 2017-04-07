package com.example.cw.practice.practice.MovieGuide;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cw on 2017/3/28.
 */

@Module
public class AppModule {
    private Context mContext;

    AppModule(Application application){
        mContext = application;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return mContext;
    }

    @Provides
    @Singleton
    public Resources provideResources(){
        return mContext.getResources();
    }
}
