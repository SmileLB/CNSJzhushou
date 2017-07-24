package com.example.smile.cnsjzhushou.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.smile.cnsjzhushou.R;
import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.common.apkparset.AndroidApk;
import com.example.smile.cnsjzhushou.di.component.AppComponent;
import com.example.smile.cnsjzhushou.di.component.DaggerAppManagerComponent;
import com.example.smile.cnsjzhushou.di.module.AppManagerModule;
import com.example.smile.cnsjzhushou.presenter.AppManagerPresenter;
import com.example.smile.cnsjzhushou.presenter.contract.AppManagerContract;
import com.example.smile.cnsjzhushou.ui.decoration.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import zlc.season.rxdownload2.entity.DownloadRecord;


public abstract class AppManangerFragment extends ProgressFragment<AppManagerPresenter>  implements AppManagerContract.AppManagerView {

    @BindView(R.id.id_recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public void init() {
        setupRecyclerView();
    }

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    private void setupRecyclerView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()) );

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);

        mRecyclerView.addItemDecoration(itemDecoration);


        mRecyclerView.setAdapter(setupAdapter());
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {

        DaggerAppManagerComponent.builder()
                .appComponent(appComponent)
                .appManagerModule(new AppManagerModule(this))
                .build().inject(this);
    }

    @Override
    public void showApps(List<AndroidApk> apps) {

    }

    @Override
    public void showDownloading(List<DownloadRecord> downloadRecords) {

    }

    @Override
    public void showUpdateApps(List<AppInfo> appInfos) {

    }

    protected abstract RecyclerView.Adapter setupAdapter();

}
