package com.example.smile.cnsjzhushou.ui.fragment;

import com.example.smile.cnsjzhushou.di.component.AppComponent;
import com.example.smile.cnsjzhushou.di.component.DaggerAppInfoComponent;
import com.example.smile.cnsjzhushou.di.module.AppInfoModule;
import com.example.smile.cnsjzhushou.presenter.AppInfoPresenter;
import com.example.smile.cnsjzhushou.ui.adapter.AppInfoAdapter;

/**
 * Created by Administrator on 2017/6/17 0017.
 */

public class TopListFragment extends BaseAppInfoFragment{

    @Override
    public AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showPosition(true).showBrief(false)
                .showCategoryName(true)
                .rxDownload(mRxDownload)
                .build();
    }

    @Override
    public int type() {
        return AppInfoPresenter.TOP_LIST;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder()
                .appInfoModule(new AppInfoModule(this))
                .appComponent(appComponent)
                .build()
                .injectTopListFragment(this);
    }

}
