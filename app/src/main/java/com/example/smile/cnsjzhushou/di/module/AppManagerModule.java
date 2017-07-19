package com.example.smile.cnsjzhushou.di.module;


import android.app.Application;

import com.example.smile.cnsjzhushou.data.AppManagerModel;
import com.example.smile.cnsjzhushou.presenter.contract.AppManagerContract;

import dagger.Module;
import dagger.Provides;
import zlc.season.rxdownload2.RxDownload;


@Module()
public class AppManagerModule {

    private AppManagerContract.AppManagerView mView;

    public AppManagerModule(AppManagerContract.AppManagerView  view){
        this.mView = view;
    }

    @Provides
    public AppManagerContract.AppManagerView  provideView(){
        return mView;
    }

    @Provides
    public AppManagerContract.IAppManagerModel provideModel(Application application,RxDownload rxDownload){
        return  new AppManagerModel(application,rxDownload);
    }
}
