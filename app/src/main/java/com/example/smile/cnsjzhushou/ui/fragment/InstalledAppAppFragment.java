package com.example.smile.cnsjzhushou.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.example.smile.cnsjzhushou.common.apkparset.AndroidApk;
import com.example.smile.cnsjzhushou.ui.adapter.AndroidApkAdapter;

import java.util.List;

/**
 * Created by LiBing
 * on 2017/7/20 0020
 * describe:
 */

public class InstalledAppAppFragment extends AppManangerFragment{

    private AndroidApkAdapter mAdapter;

    @Override
    public void init() {
        super.init();
        mPresenter.getInstalledApps();
    }
    @Override
    protected RecyclerView.Adapter setupAdapter() {
        mAdapter = new AndroidApkAdapter(AndroidApkAdapter.FLAG_APP);
        return mAdapter;
    }

    @Override
    public void showApps(List<AndroidApk> apps) {
        mAdapter.addData(apps);
    }
}
