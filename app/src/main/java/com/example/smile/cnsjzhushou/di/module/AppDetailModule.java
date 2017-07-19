package com.example.smile.cnsjzhushou.di.module;

import com.example.smile.cnsjzhushou.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LiBing
 * on 2017/7/15 0015
 * describe:
 */
@Module(includes = AppModelModule.class)
public class AppDetailModule {

    private AppInfoContract.AppDetailView mView;

    public AppDetailModule(AppInfoContract.AppDetailView view) {
        this.mView = view;
    }

    @Provides
    public AppInfoContract.AppDetailView provideView(){
        return mView;
    }

}
