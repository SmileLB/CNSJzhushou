package com.example.smile.cnsjzhushou.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.smile.cnsjzhushou.R;
import com.example.smile.cnsjzhushou.bean.AppInfo;
import com.example.smile.cnsjzhushou.bean.PageBean;
import com.example.smile.cnsjzhushou.di.component.AppComponent;
import com.example.smile.cnsjzhushou.di.component.DaggerAppInfoComponent;
import com.example.smile.cnsjzhushou.di.module.AppInfoModule;
import com.example.smile.cnsjzhushou.presenter.AppInfoPresenter;
import com.example.smile.cnsjzhushou.presenter.contract.AppInfoContract;
import com.example.smile.cnsjzhushou.ui.activity.AppDetailActivity;
import com.example.smile.cnsjzhushou.ui.adapter.AppInfoAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import zlc.season.rxdownload2.RxDownload;

/**
 * Created by LiBing
 * on 2017/7/13 0013
 * describe:
 */

public abstract class BaseAppInfoFragment extends ProgressFragment<AppInfoPresenter>
        implements AppInfoContract.AppInfoView,BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.id_recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    RxDownload mRxDownload;

    private AppInfoAdapter mAdapter;

    int page=0;

    abstract public AppInfoAdapter buildAdapter();

    abstract public int type();

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {

        DaggerAppInfoComponent.builder()
                .appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this))
                .build()
                .injectTopListFragment(this);

    }

    @Override
    public void init() {
        initRecyclerView();
        mPresenter.requestData(type(),page);
    }

    protected void initRecyclerView() {
        mAdapter=buildAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnLoadMoreListener(this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                AppInfo appInfo = mAdapter.getItem(position);
                mApplication.setView(view);
                Intent intent  = new Intent(getActivity(), AppDetailActivity.class);
                intent.putExtra("appinfo",appInfo);
                startActivity(intent);
            }
        });
    }

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void showResult(PageBean<AppInfo> pageBean) {
        mAdapter.addData(pageBean.getDatas());
        if(pageBean.isHasMore()){
            page++;
        }
        mAdapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.requestData(type(),page);
    }

    @Override
    public void onEmptyViewClick() {
        mPresenter.requestData(type(),page);
    }

}
