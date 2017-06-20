package com.example.smile.cnsjzhushou.presenter;

import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.bean.PageBean;
import com.example.smile.cnsjzhushou.data.RecommendModel;
import com.example.smile.cnsjzhushou.presenter.contract.RecommendContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LiBing
 * on 2017/6/19 0019
 * describe:
 */

public class RecommendPresenter implements RecommendContract.Presenter {

    public RecommendContract.View mView;

    public RecommendModel mModel;

    public RecommendPresenter(RecommendContract.View view,RecommendModel model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void requestDatas() {
        mView.showLoading();
        mModel.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                if (response != null) {
                    mView.showResult(response.body().getDatas());
                } else {
                    mView.showNoData();
                }
                mView.dismissLoading();
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
                mView.showError(t.getMessage());
                mView.dismissLoading();
            }
        });
    }
}
