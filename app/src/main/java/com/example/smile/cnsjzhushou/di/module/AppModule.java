package com.example.smile.cnsjzhushou.di.module;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LiBing
 * on 2017/6/28 0028
 * describe:
 */
@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Application providerApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    public Gson providerGson(){
        return new Gson();
    }
}
