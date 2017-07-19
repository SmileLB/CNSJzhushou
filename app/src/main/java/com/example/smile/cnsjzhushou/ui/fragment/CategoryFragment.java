package com.example.smile.cnsjzhushou.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.smile.cnsjzhushou.R;
import com.example.smile.cnsjzhushou.bean.Category;
import com.example.smile.cnsjzhushou.common.Constant;
import com.example.smile.cnsjzhushou.di.component.AppComponent;
import com.example.smile.cnsjzhushou.di.component.DaggerCateogryComponent;
import com.example.smile.cnsjzhushou.di.module.CateogryModule;
import com.example.smile.cnsjzhushou.presenter.CateogryPresenter;
import com.example.smile.cnsjzhushou.presenter.contract.CategoryContract;
import com.example.smile.cnsjzhushou.ui.activity.CategoryAppActivity;
import com.example.smile.cnsjzhushou.ui.adapter.CategoryAdapter;
import com.example.smile.cnsjzhushou.ui.decoration.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/17 0017.
 */

public class CategoryFragment extends ProgressFragment<CateogryPresenter> implements CategoryContract.ICagegoryView{

    @BindView(R.id.id_recycler_view)
    RecyclerView mRecycleView;

    private CategoryAdapter mAdapter;

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerCateogryComponent.builder()
                .appComponent(appComponent)
                .cateogryModule(new CateogryModule(this))
                .build().inject(this);
    }

    @Override
    public void init() {
        initRecyclerView();
        mPresenter.getAllCategory();
    }

    private void initRecyclerView(){


        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()) );

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);

        mRecycleView.addItemDecoration(itemDecoration);
        mAdapter = new CategoryAdapter();

        mRecycleView.setAdapter(mAdapter);

        mRecycleView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), CategoryAppActivity.class);
                intent.putExtra(Constant.CATEGORY,mAdapter.getData().get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void showData(List<Category> categories) {
        mAdapter.addData(categories);
    }
}
