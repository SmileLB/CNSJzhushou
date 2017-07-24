package com.example.smile.cnsjzhushou.ui.fragment;


import com.example.smile.cnsjzhushou.bean.PageBean;
import com.example.smile.cnsjzhushou.bean.Subject;
import com.example.smile.cnsjzhushou.bean.SubjectDetail;
import com.example.smile.cnsjzhushou.di.component.AppComponent;
import com.example.smile.cnsjzhushou.di.component.DaggerSubjectComponent;
import com.example.smile.cnsjzhushou.di.module.SubjectModule;
import com.example.smile.cnsjzhushou.presenter.SubjectPresenter;
import com.example.smile.cnsjzhushou.presenter.contract.SubjectContract;

public  abstract  class BaseSubjectFragment extends ProgressFragment<SubjectPresenter> implements SubjectContract.SubjectView {

    @Override
    public void showSubjects(PageBean<Subject> subjects) {

    }

    @Override
    public void onLoadMoreComplete() {

    }

    @Override
    public void showSubjectDetail(SubjectDetail detail) {

    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerSubjectComponent.builder()
                .appComponent(appComponent)
                .subjectModule(new SubjectModule(this))
                .build().inject(this);
    }
}
