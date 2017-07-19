package com.example.smile.cnsjzhushou.ui.fragment;

import com.example.smile.cnsjzhushou.di.component.AppComponent;
import com.example.smile.cnsjzhushou.di.component.DaggerAppInfoComponent;
import com.example.smile.cnsjzhushou.di.module.AppInfoModule;
import com.example.smile.cnsjzhushou.presenter.AppInfoPresenter;
import com.example.smile.cnsjzhushou.ui.adapter.AppInfoAdapter;

/**
 * Created by Administrator on 2017/6/17 0017.
 */

public class GamesFragment extends BaseAppInfoFragment {

    @Override
    public AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showPosition(false).showBrief(true).showCategoryName(true).build();
    }

    @Override
    public int type() {
        return AppInfoPresenter.GAME;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder()
                .appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this))
                .build()
                .injectGamesFragment(this);
    }
}
