package com.example.smile.cnsjzhushou.di;

import android.app.ProgressDialog;

import com.example.smile.cnsjzhushou.data.RecommendModel;
import com.example.smile.cnsjzhushou.presenter.RecommendPresenter;
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
    public RecommendContract.Presenter providePresenter(RecommendContract.View view,RecommendModel model){
        return new RecommendPresenter(view,model);
    }

    @Provides
    public RecommendContract.View provideView(){
        return mView;
    }

    @Provides
    public RecommendModel provideModel(){
        return new RecommendModel();
    }

    @Provides
    public ProgressDialog provideProgressDialog(RecommendContract.View view){
        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }
}
