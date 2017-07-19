package com.example.smile.cnsjzhushou.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.example.smile.cnsjzhushou.common.apkparset.AndroidApk;
import com.example.smile.cnsjzhushou.di.component.AppComponent;
import com.example.smile.cnsjzhushou.di.component.DaggerAppManagerComponent;
import com.example.smile.cnsjzhushou.di.module.AppManagerModule;
import com.example.smile.cnsjzhushou.ui.adapter.AndroidApkAdapter;

import java.util.List;


public class DownloadedFragment extends AppManangerFragment {

    AndroidApkAdapter mAdapter;

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerAppManagerComponent.builder()
                .appComponent(appComponent)
                .appManagerModule(new AppManagerModule(this))
                .build().injectDownloaded(this);
    }

    @Override
    public void init() {
        super.init();
        mPresenter.getLocalApks();
    }

    @Override
    protected RecyclerView.Adapter setupAdapter() {
        mAdapter = new AndroidApkAdapter(AndroidApkAdapter.FLAG_APK);
        return mAdapter;
    }

    @Override
    public void showApps(List<AndroidApk> apps) {
        mAdapter.addData(apps);
    }
}
