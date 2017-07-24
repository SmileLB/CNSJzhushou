package com.example.smile.cnsjzhushou.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.example.smile.cnsjzhushou.ui.adapter.DownloadingAdapter;

import java.util.List;

import zlc.season.rxdownload2.entity.DownloadRecord;


public class DownloadingFragment extends AppManangerFragment{

    private DownloadingAdapter mAdapter;

    @Override
    public void init() {
        super.init();
        mPresenter.getDownlodingApps();
    }

    @Override
    protected RecyclerView.Adapter setupAdapter() {
        mAdapter = new DownloadingAdapter(mPresenter.getRxDowanload());
        return mAdapter;
    }

    @Override
    public void showDownloading(List<DownloadRecord> downloadRecords) {
        mAdapter.addData(downloadRecords);
    }
}
