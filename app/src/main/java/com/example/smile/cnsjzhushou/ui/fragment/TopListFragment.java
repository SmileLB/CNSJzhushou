package com.example.smile.cnsjzhushou.ui.fragment;

import com.example.smile.cnsjzhushou.R;
import com.example.smile.cnsjzhushou.di.component.AppComponent;
import com.example.smile.cnsjzhushou.presenter.TopListPresenter;

/**
 * Created by Administrator on 2017/6/17 0017.
 */

public class TopListFragment extends ProgressFragment<TopListPresenter>{



    @Override
    public void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

    }

    @Override
    public int setLayout() {
        return R.layout.fragment_ranking;
    }
}
