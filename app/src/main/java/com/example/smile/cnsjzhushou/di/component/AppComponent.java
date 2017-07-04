package com.example.smile.cnsjzhushou.di.component;

import com.example.smile.cnsjzhushou.data.http.ApiService;
import com.example.smile.cnsjzhushou.di.module.AppModule;
import com.example.smile.cnsjzhushou.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LiBing
 * on 2017/6/28 0028
 * describe:
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    ApiService getApiService();
}
