package com.example.smile.cnsjzhushou.presenter;

import com.example.smile.cnsjzhushou.bean.IndexBean;
import com.example.smile.cnsjzhushou.common.rx.RxHttpReponseCompat;
import com.example.smile.cnsjzhushou.common.rx.subscriber.ProgressSubcriber;
import com.example.smile.cnsjzhushou.data.AppInfoModel;
import com.example.smile.cnsjzhushou.presenter.contract.AppInfoContract;

import javax.inject.Inject;

/**
 * Created by LiBing
 * on 2017/6/19 0019
 * describe:
 */

public class RecommendPresenter extends BasePresenter<AppInfoModel, AppInfoContract.View> {

    @Inject
    public RecommendPresenter(AppInfoModel model, AppInfoContract.View view) {
        super(model, view);
    }

    public void requestDatas() {
        mModel.index().compose(RxHttpReponseCompat.<IndexBean>compatResult())
                .subscribe(new ProgressSubcriber<IndexBean>(mContext,mView) {
                    @Override
                    public void onNext(IndexBean indexBean) {

                        mView.showResult(indexBean);
                    }
                });

//        RxPermissions rxPermissions = new RxPermissions((Activity) mContext);
//        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
//                .flatMap(new Func1<Boolean, Observable<PageBean<AppInfo>>>() {
//                    @Override
//                    public Observable<PageBean<AppInfo>> call(Boolean aBoolean) {
//                        if (aBoolean) {
//                            return mModel.getApps().compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult());
//                        } else {
//                            //mView.onRequestPermissonError();
//                            return Observable.empty();
//                        }
//
//                    }
//                })
//                .subscribe(new ProgressSubcriber<PageBean<AppInfo>>(mContext, mView) {
//                    @Override
//                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                        mView.showResult(appInfoPageBean.getDatas());
//                    }
//                });


//        mModel.getApps()
//                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
//                .subscribe(new ProgressSubcriber<PageBean<AppInfo>>(mContext, mView) {
//                    @Override
//                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                        mView.showResult(appInfoPageBean.getDatas());
//                    }
//                });


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
