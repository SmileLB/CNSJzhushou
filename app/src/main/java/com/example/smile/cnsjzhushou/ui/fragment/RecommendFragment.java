package com.example.smile.cnsjzhushou.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smile.cnsjzhushou.R;
import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.di.DaggerRemmendComponent;
import com.example.smile.cnsjzhushou.di.RemmendModule;
import com.example.smile.cnsjzhushou.presenter.contract.RecommendContract;
import com.example.smile.cnsjzhushou.ui.adapter.RecommendAdapter;
import com.example.smile.cnsjzhushou.ui.decoration.DividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/6/17 0017.
 * 推荐
 */

public class RecommendFragment extends Fragment implements RecommendContract.View {

    @BindView(R.id.id_recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    @Inject
    RecommendContract.Presenter mPresenter;
    @Inject
    ProgressDialog mDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recomend, container, false);
        unbinder = ButterKnife.bind(this, view);
        //mPresenter = new RecommendPresenter(this);
        //mDialog = new ProgressDialog(getActivity());

        initData();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DaggerRemmendComponent.builder().remmendModule(new RemmendModule(this))
                .build().inject(this);
    }

    private void initData() {
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
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
