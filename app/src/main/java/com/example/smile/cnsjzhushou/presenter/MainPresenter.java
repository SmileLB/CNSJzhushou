package com.example.smile.cnsjzhushou.presenter;

import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.bean.requestbean.AppsUpdateBean;
import com.example.smile.cnsjzhushou.common.Constant;
import com.example.smile.cnsjzhushou.common.apkparset.AndroidApk;
import com.example.smile.cnsjzhushou.common.rx.RxHttpReponseCompat;
import com.example.smile.cnsjzhushou.common.rx.subscriber.ProgressSubcriber;
import com.example.smile.cnsjzhushou.common.util.ACache;
import com.example.smile.cnsjzhushou.common.util.AppUtils;
import com.example.smile.cnsjzhushou.common.util.JsonUtils;
import com.example.smile.cnsjzhushou.common.util.PermissionUtil;
import com.example.smile.cnsjzhushou.presenter.contract.MainContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static android.Manifest.permission.READ_PHONE_STATE;

/**
 * Created by LiBing
 * on 2017/7/22 0022
 * describe:
 */

public class MainPresenter extends BasePresenter<MainContract.IMainModel, MainContract.MainView> {

    @Inject
    public MainPresenter(MainContract.IMainModel model, MainContract.MainView view) {
        super(model, view);
    }

    public void requestPermisson() {
        PermissionUtil.requestPermisson(mContext, READ_PHONE_STATE)
                .doOnNext(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        if (!aBoolean) {
                            mView.requestPermissonFail();
                        }

                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        mView.requestPermissonSuccess();
                    }
                });
    }

    public void getAppUpdateInfo() {
        getIntalledApps()
                .flatMap(new Function<AppsUpdateBean, ObservableSource<List<AppInfo>>>() {
                    @Override
                    public ObservableSource<List<AppInfo>> apply(@NonNull AppsUpdateBean params) throws Exception {

                        return mModel.getUpdateApps(params)
                                .compose(RxHttpReponseCompat.<List<AppInfo>>compatResult());
                    }
                })
                .subscribe(new ProgressSubcriber<List<AppInfo>>(mContext, mView) {
                    @Override
                    public void onNext(List<AppInfo> appInfos) {

                        if (appInfos != null) {

                            ACache.get(mContext).put(Constant.APP_UPDATE_LIST, JsonUtils.toJson(appInfos));
                        }

                        mView.changeAppNeedUpdateCount(appInfos == null ? 0 : appInfos.size());

                    }
                });
    }

    private Observable<AppsUpdateBean> getIntalledApps() {

        return Observable.create(new ObservableOnSubscribe<AppsUpdateBean>() {
            @Override
            public void subscribe(ObservableEmitter<AppsUpdateBean> e) throws Exception {

                e.onNext(buildParams(AppUtils.getInstalledApps(mContext)));
                e.onComplete();
            }
        });
    }

    private AppsUpdateBean buildParams(List<AndroidApk> apks) {
        StringBuilder packageNameBuilder = new StringBuilder();
        StringBuilder versionCodeBuilder = new StringBuilder();

        for (AndroidApk apk : apks) {

            if (!apk.isSystem()) {

                packageNameBuilder.append(apk.getPackageName()).append(",");
                versionCodeBuilder.append(apk.getAppVersionCode()).append(",");
            }
        }
        AppsUpdateBean param = new AppsUpdateBean();
        param.setPackageName(packageNameBuilder.toString());
        param.setVersionCode(versionCodeBuilder.toString());

        return param;
    }

}
