package com.example.smile.cnsjzhushou.ui.fragment;

import android.annotation.SuppressLint;

import com.example.smile.cnsjzhushou.di.component.AppComponent;
import com.example.smile.cnsjzhushou.di.component.DaggerAppInfoComponent;
import com.example.smile.cnsjzhushou.di.module.AppInfoModule;
import com.example.smile.cnsjzhushou.ui.adapter.AppInfoAdapter;

/**
 * Created by LiBing
 * on 2017/7/15 0015
 * describe:
 */

@SuppressLint("ValidFragment")
public class CategoryAppFragment extends BaseAppInfoFragment{

    private int categoryId;
    private int mFlagType;

    @SuppressLint("ValidFragment")
    public CategoryAppFragment(int categoryId, int flagType){
        this.categoryId = categoryId;
        this.mFlagType = flagType;
    }

    @Override
    public void init() {
        mPresenter.requestCategoryApps(categoryId,page,mFlagType);
        initRecyclerView();
    }

    @Override
    public AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showPosition(false).showBrief(true).showCategoryName(false).build();
    }

    @Override
    public int type() {
        return 0;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder()
                .appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this))
                .build()
                .injectCategoryAppFragment(this);
    }
}
