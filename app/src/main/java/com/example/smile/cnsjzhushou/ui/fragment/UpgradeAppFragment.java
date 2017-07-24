package com.example.smile.cnsjzhushou.ui.fragment;


import android.support.v7.widget.RecyclerView;

import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.ui.adapter.AppInfoAdapter;

import java.util.List;



public class UpgradeAppFragment extends AppManangerFragment {

    AppInfoAdapter mAdapter;

    public UpgradeAppFragment() {
    }

    @Override
    public void init() {
        super.init();
        mPresenter.getUpdateApps();
    }

    @Override
    protected RecyclerView.Adapter setupAdapter() {
        mAdapter = AppInfoAdapter.builder().updateStatus(true).rxDownload(mPresenter.getRxDowanload()).build();
        return mAdapter;
    }

    @Override
    public void showUpdateApps(List<AppInfo> appInfos) {
        mAdapter.addData(appInfos);
    }
}
