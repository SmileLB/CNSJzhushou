package com.example.smile.cnsjzhushou.di.module;

import android.app.ProgressDialog;

import com.example.smile.cnsjzhushou.data.RecommendModel;
import com.example.smile.cnsjzhushou.data.http.ApiService;
import com.example.smile.cnsjzhushou.presenter.contract.RecommendContract;
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

    private RecommendContract.View mView;

    public RemmendModule(RecommendContract.View view) {
        this.mView = view;
    }

    @Provides
    public RecommendContract.View provideView(){
        return mView;
    }

    @Provides
    public RecommendModel provideModel(ApiService service){
        return new RecommendModel(service);
    }

    @Provides
    public ProgressDialog provideProgressDialog(RecommendContract.View view){
        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }
}
