package com.example.smile.cnsjzhushou.di.module;

import android.app.ProgressDialog;

import com.example.smile.cnsjzhushou.data.AppInfoModel;
import com.example.smile.cnsjzhushou.data.http.ApiService;
import com.example.smile.cnsjzhushou.presenter.contract.AppInfoContract;
import com.example.smile.cnsjzhushou.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LiBing
 * on 2017/6/19 0019
 * describe:告诉dragger2初始化对象
 */
@Module
public class RemmendModule {

    private AppInfoContract.View mView;

    public RemmendModule(AppInfoContract.View view) {
        this.mView = view;
    }

    @Provides
    public AppInfoContract.View provideView(){
        return mView;
    }

    @Provides
    public AppInfoModel provideModel(ApiService service){
        return new AppInfoModel(service);
    }

    @Provides
    public ProgressDialog provideProgressDialog(AppInfoContract.View view){
        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }
}
