package com.example.smile.cnsjzhushou.di.module;

import com.example.smile.cnsjzhushou.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LiBing
 * on 2017/7/12 0012
 * describe:
 */
@Module(includes = AppModelModule.class)
public class AppInfoModule {

    private AppInfoContract.AppInfoView mView;

    public AppInfoModule(AppInfoContract.AppInfoView view) {
        this.mView = view;
    }

    @Provides
    public AppInfoContract.AppInfoView provideView(){
        return mView;
    }


}
