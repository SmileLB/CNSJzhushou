package com.example.smile.cnsjzhushou.presenter;

import com.example.smile.cnsjzhushou.common.apkparset.AndroidApk;
import com.example.smile.cnsjzhushou.common.rx.RxSchedulers;
import com.example.smile.cnsjzhushou.common.rx.subscriber.ProgressSubcriber;
import com.example.smile.cnsjzhushou.presenter.contract.AppManagerContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;


public class AppManagerPresenter extends BasePresenter<AppManagerContract.IAppManagerModel, AppManagerContract.AppManagerView> {

    @Inject
    public AppManagerPresenter(AppManagerContract.IAppManagerModel iAppManagerModel, AppManagerContract.AppManagerView appManagerView) {
        super(iAppManagerModel, appManagerView);
    }

    public void getDownlodingApps() {

        mModel.getDownloadRecord().compose(RxSchedulers.<List<DownloadRecord>>io_main())

                .subscribe(new ProgressSubcriber<List<DownloadRecord>>(mContext, mView) {
                    @Override
                    public void onNext(List<DownloadRecord> downloadRecords) {

                        mView.showDownloading(downloadRecordFilter(downloadRecords));

                    }
                });
    }

    public void getLocalApks() {
        mModel.getLocalApks()
                .compose(RxSchedulers.<List<AndroidApk>>io_main())
                .subscribe(new ProgressSubcriber<List<AndroidApk>>(mContext, mView) {
                    @Override
                    public void onNext(List<AndroidApk> androidApks) {
                        mView.showApps(androidApks);
                    }
                });
    }

    public RxDownload getRxDowanload() {
        return mModel.getRxDownload();
    }

    private List<DownloadRecord> downloadRecordFilter(List<DownloadRecord> downloadRecords) {
        List<DownloadRecord> newList = new ArrayList<>();
        for (DownloadRecord r : downloadRecords) {
            if (r.getFlag() != DownloadFlag.COMPLETED) {
                newList.add(r);
            }
        }
        return newList;
    }
}
