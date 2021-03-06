package com.example.smile.cnsjzhushou.presenter;

import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.bean.BaseBean;
import com.example.smile.cnsjzhushou.bean.PageBean;
import com.example.smile.cnsjzhushou.common.rx.RxHttpReponseCompat;
import com.example.smile.cnsjzhushou.common.rx.subscriber.ErrorHandlerSubscriber;
import com.example.smile.cnsjzhushou.common.rx.subscriber.ProgressSubcriber;
import com.example.smile.cnsjzhushou.data.AppInfoModel;
import com.example.smile.cnsjzhushou.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;


/**
 * Created by LiBing
 * on 2017/7/12 0012
 * describe:
 */

public class AppInfoPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppInfoView> {

    public static final int TOP_LIST = 1;
    public static final int GAME = 2;
    public static final int CATEGORY = 3;

    public static final int FEATURED = 0;
    public static final int TOPLIST = 1;
    public static final int NEWLIST = 2;
    public static final int HOT_APP_LIST = 3;

    @Inject
    public AppInfoPresenter(AppInfoModel model, AppInfoContract.AppInfoView view) {
        super(model, view);
    }

    public void requestData(int type, int page) {
        request(type, page, 0, 0);
    }

    public void requestCategoryApps(int categoryId, int page, int flagType) {
        request(CATEGORY, page, categoryId, flagType);
    }

    public void request(int type, int page, int categoryId, int flagType) {

        Observer subscriber = null;
        if (page == 0) {
            subscriber = new ProgressSubcriber<PageBean<AppInfo>>(mContext, mView) {
                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);
                }
            };
        } else {
            subscriber = new ErrorHandlerSubscriber<PageBean<AppInfo>>(mContext) {
                @Override
                public void onComplete() {
                    mView.onLoadMoreComplete();
                }

                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);
                }
            };
        }

        Observable observable = getObservable(type, page, categoryId, flagType);

        observable
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);
    }

    private Observable<BaseBean<PageBean<AppInfo>>> getObservable(int type, int page, int categoryId, int flagType) {
        switch (type) {
            case TOP_LIST:
                return mModel.topList(page);
            case GAME:
                return mModel.games(page);
            case CATEGORY:
                if (flagType == FEATURED) {
                    return mModel.getFeaturedAppsByCategory(categoryId, page);
                } else if (flagType == TOPLIST) {
                    return mModel.getTopListAppsByCategory(categoryId, page);
                } else if (flagType == NEWLIST) {
                    return mModel.getNewListAppsByCategory(categoryId, page);
                } else if (flagType == HOT_APP_LIST) {
                    return mModel.getHotApps(page);
                }
            default:
                return Observable.empty();
        }
    }


}
