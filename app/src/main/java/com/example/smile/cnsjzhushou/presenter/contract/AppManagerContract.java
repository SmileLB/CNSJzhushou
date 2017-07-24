package com.example.smile.cnsjzhushou.presenter.contract;

import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.common.apkparset.AndroidApk;
import com.example.smile.cnsjzhushou.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;


public interface AppManagerContract {

    interface AppManagerView extends BaseView {
        void showDownloading(List<DownloadRecord> downloadRecords);
        void showApps(List<AndroidApk> apps);
        void showUpdateApps(List<AppInfo> appInfos);
    }

    interface IAppManagerModel{
        Observable<List<DownloadRecord>> getDownloadRecord();
        RxDownload getRxDownload();
        Observable<List<AndroidApk>> getLocalApks();
        Observable<List<AndroidApk>> getInstalledApps();
    }
}
