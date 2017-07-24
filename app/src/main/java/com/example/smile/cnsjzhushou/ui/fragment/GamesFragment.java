package com.example.smile.cnsjzhushou.ui.fragment;

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


}
