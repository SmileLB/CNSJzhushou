package com.example.smile.cnsjzhushou.di.module;

import com.example.smile.cnsjzhushou.data.AppInfoModel;
import com.example.smile.cnsjzhushou.data.http.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModelModule {

    @Provides
    public AppInfoModel privodeModel(ApiService apiService) {

        return new AppInfoModel(apiService);
    }
}
