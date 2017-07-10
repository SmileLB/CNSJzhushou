package com.example.smile.cnsjzhushou.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.smile.cnsjzhushou.R;
import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.di.component.AppComponent;
import com.example.smile.cnsjzhushou.di.component.DaggerRemmendComponent;
import com.example.smile.cnsjzhushou.di.module.RemmendModule;
import com.example.smile.cnsjzhushou.presenter.RecommendPresenter;
import com.example.smile.cnsjzhushou.presenter.contract.RecommendContract;
import com.example.smile.cnsjzhushou.ui.adapter.RecommendAdapter;
import com.example.smile.cnsjzhushou.ui.decoration.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/17 0017.
 * 推荐
 */

public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements RecommendContract.View {

    @BindView(R.id.id_recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public int setLayout() {
        return R.layout.fragment_recomend;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerRemmendComponent.builder().appComponent(appComponent)
                .remmendModule(new RemmendModule(this))
                .build().inject(this);
    }

    @Override
    public void init() {
        mPresenter.requestDatas();
    }

    private void initRecyclerView(List<AppInfo> data) {
        RecommendAdapter adapter = new RecommendAdapter(getActivity(), data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义)
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onEmptyViewClick() {
        mPresenter.requestDatas();
    }

    @Override
    public void showNoData() {
        Toast.makeText(getActivity(), "没有获取到数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(List<AppInfo> datas) {
        initRecyclerView(datas);
    }


}
