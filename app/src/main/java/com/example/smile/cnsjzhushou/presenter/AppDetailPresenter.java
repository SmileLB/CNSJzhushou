package com.example.smile.cnsjzhushou.presenter;

import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.common.rx.RxHttpReponseCompat;
import com.example.smile.cnsjzhushou.common.rx.subscriber.ProgressSubcriber;
import com.example.smile.cnsjzhushou.data.AppInfoModel;
import com.example.smile.cnsjzhushou.presenter.contract.AppInfoContract;

import javax.inject.Inject;

/**
 * Created by LiBing
 * on 2017/7/15 0015
 * describe:
 */

public class AppDetailPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppDetailView> {

    @Inject
    public AppDetailPresenter(AppInfoModel model, AppInfoContract.AppDetailView view) {
        super(model, view);
    }

    public void getAppDetail(int id) {
        mModel.getAppDetail(id).compose(RxHttpReponseCompat.<AppInfo>compatResult())
                .subscribe(new ProgressSubcriber<AppInfo>(mContext, mView) {
                    @Override
                    public void onNext(AppInfo appInfo) {

                        mView.showAppDetail(appInfo);
                    }
                });
    }
}
