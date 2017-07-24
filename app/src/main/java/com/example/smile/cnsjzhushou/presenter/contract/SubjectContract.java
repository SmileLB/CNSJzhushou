package com.example.smile.cnsjzhushou.presenter.contract;


import com.example.smile.cnsjzhushou.bean.BaseBean;
import com.example.smile.cnsjzhushou.bean.PageBean;
import com.example.smile.cnsjzhushou.bean.Subject;
import com.example.smile.cnsjzhushou.bean.SubjectDetail;
import com.example.smile.cnsjzhushou.ui.BaseView;

import io.reactivex.Observable;


public class SubjectContract {

    public interface SubjectView extends BaseView {
        void showSubjects(PageBean<Subject> subjects);

        void onLoadMoreComplete();

        void showSubjectDetail(SubjectDetail detail);
    }

    public interface ISubjectModel {
        Observable<BaseBean<PageBean<Subject>>> getSubjects(int page);

        Observable<BaseBean<SubjectDetail>> getSubjectDetail(int id);
    }
}
