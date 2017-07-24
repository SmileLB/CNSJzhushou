package com.example.smile.cnsjzhushou.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.smile.cnsjzhushou.R;
import com.example.smile.cnsjzhushou.bean.PageBean;
import com.example.smile.cnsjzhushou.bean.Subject;
import com.example.smile.cnsjzhushou.common.rx.RxBus;
import com.example.smile.cnsjzhushou.ui.adapter.SubjectAdapter;
import com.example.smile.cnsjzhushou.ui.widget.SpaceItemDecoration2;

import butterknife.BindView;


public class SubjectFragment extends BaseSubjectFragment implements  BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.id_recycler_view)
    RecyclerView mRecyclerView;

    private SubjectAdapter mAdapter;

    int page =0;
    @Override
    public void init() {
        initRecyclerView();
        mPresenter.getSubjects(page);
    }

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    protected void initRecyclerView(){

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setLayoutManager(layoutManager);

        SpaceItemDecoration2 dividerDecoration = new SpaceItemDecoration2(5);
        mRecyclerView.addItemDecoration(dividerDecoration);


        mAdapter = new SubjectAdapter();

        mAdapter.setOnLoadMoreListener(this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                Subject subject = mAdapter.getItem(position);

                RxBus.getDefault().post(subject);

            }
        });

    }

    @Override
    public void showSubjects(PageBean<Subject> subjects) {

        mAdapter.addData(subjects.getDatas());

        if(subjects.isHasMore()){
            page++;
        }
        mAdapter.setEnableLoadMore(subjects.isHasMore());
    }

    @Override
    public void onLoadMoreRequested() {

        mPresenter.getSubjects(page);
    }
}
