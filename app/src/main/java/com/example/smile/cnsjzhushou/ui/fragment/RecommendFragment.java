package com.example.smile.cnsjzhushou.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.smile.cnsjzhushou.R;
import com.example.smile.cnsjzhushou.bean.IndexBean;
import com.example.smile.cnsjzhushou.di.component.AppComponent;
import com.example.smile.cnsjzhushou.di.component.DaggerRemmendComponent;
import com.example.smile.cnsjzhushou.di.module.RemmendModule;
import com.example.smile.cnsjzhushou.presenter.RecommendPresenter;
import com.example.smile.cnsjzhushou.presenter.contract.AppInfoContract;
import com.example.smile.cnsjzhushou.ui.adapter.IndexMultipleAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import zlc.season.rxdownload2.RxDownload;

/**
 * Created by Administrator on 2017/6/17 0017.
 * 推荐
 */

public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements AppInfoContract.View {

    @BindView(R.id.id_recycler_view)
    RecyclerView mRecyclerView;

    private IndexMultipleAdapter mAdatper;

    @Inject
    RxDownload mRxDownload;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerRemmendComponent.builder().appComponent(appComponent)
                .remmendModule(new RemmendModule(this))
                .build().inject(this);
    }

    @Override
    public void init() {
        initRecyclerView();
        mPresenter.requestDatas();
//        mPresenter.requestPermissions();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义)
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onEmptyViewClick() {
        mPresenter.requestDatas();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(IndexBean indexBean) {
        mAdatper = new IndexMultipleAdapter(getActivity(),mRxDownload);
        mAdatper.setData(indexBean);
        mRecyclerView.setAdapter(mAdatper);
    }

    @Override
    public void onRequestPermissonSuccess() {

    }

    @Override
    public void onRequestPermissonError() {
        Toast.makeText(getActivity(),"你已拒绝授权",Toast.LENGTH_LONG).show();
    }
}
