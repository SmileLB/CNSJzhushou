package com.example.smile.cnsjzhushou.presenter;

import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.bean.PageBean;
import com.example.smile.cnsjzhushou.common.rx.RxHttpReponseCompat;
import com.example.smile.cnsjzhushou.common.rx.subscriber.ProgressSubcriber;
import com.example.smile.cnsjzhushou.data.RecommendModel;
import com.example.smile.cnsjzhushou.presenter.contract.RecommendContract;

import javax.inject.Inject;

/**
 * Created by LiBing
 * on 2017/6/19 0019
 * describe:
 */

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {

    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view) {
        super(model, view);
    }


    public void requestDatas() {

        mModel.getApps()
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressSubcriber<PageBean<AppInfo>>(mContext,mView) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        mView.showResult(appInfoPageBean.getDatas());
                    }
                });


//        mModel.getApps(new Callback<PageBean<AppInfo>>() {
//            @Override
//            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
//                if (response != null) {
//                    mView.showResult(response.body().getDatas());
//                } else {
//                    mView.showNoData();
//                }
//                mView.dismissLoading();
//            }
//
//            @Override
//            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
//                mView.showError(t.getMessage());
//                mView.dismissLoading();
//            }
//        });
    }
}
