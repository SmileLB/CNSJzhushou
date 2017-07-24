package com.example.smile.cnsjzhushou.ui.fragment;

import com.example.smile.cnsjzhushou.presenter.AppInfoPresenter;
import com.example.smile.cnsjzhushou.ui.adapter.AppInfoAdapter;

/**
 * Created by Ivan on 16/9/26.
 */

public class HotAppFragment extends BaseAppInfoFragment {
    @Override
    public AppInfoAdapter buildAdapter() {
        return  AppInfoAdapter.builder()
                .showPosition(true)
                .showBrief(false)
                .showCategoryName(true)
                .rxDownload(mRxDownload)
                .build();
    }

    @Override
    public int type() {
        return AppInfoPresenter.HOT_APP_LIST;
    }

}
