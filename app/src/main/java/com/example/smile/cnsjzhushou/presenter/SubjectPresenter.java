package com.example.smile.cnsjzhushou.presenter;


import com.example.smile.cnsjzhushou.bean.PageBean;
import com.example.smile.cnsjzhushou.bean.Subject;
import com.example.smile.cnsjzhushou.bean.SubjectDetail;
import com.example.smile.cnsjzhushou.common.rx.RxHttpReponseCompat;
import com.example.smile.cnsjzhushou.common.rx.subscriber.ErrorHandlerSubscriber;
import com.example.smile.cnsjzhushou.common.rx.subscriber.ProgressSubcriber;
import com.example.smile.cnsjzhushou.presenter.contract.SubjectContract;

import javax.inject.Inject;

import io.reactivex.Observer;


public class SubjectPresenter extends BasePresenter<SubjectContract.ISubjectModel, SubjectContract.SubjectView> {

    @Inject
    public SubjectPresenter(SubjectContract.ISubjectModel iSubjectModel,
                            SubjectContract.SubjectView subjectView) {
        super(iSubjectModel, subjectView);
    }

    public void getSubjects(int page) {

        Observer subscriber = null;

        if (page == 0) {

            subscriber = new ProgressSubcriber<PageBean<Subject>>(mContext, mView) {
                @Override
                public void onNext(PageBean<Subject> pageBean) {
                    mView.showSubjects(pageBean);
                }
            };
        } else {
            subscriber = new ErrorHandlerSubscriber<PageBean<Subject>>(mContext) {
                @Override
                public void onComplete() {
                    mView.onLoadMoreComplete();
                }

                @Override
                public void onNext(PageBean<Subject> pageBean) {

                    mView.showSubjects(pageBean);
                }
            };
        }

        mModel.getSubjects(page)
                .compose(RxHttpReponseCompat.<PageBean<Subject>>compatResult())
                .subscribe(subscriber);
    }


    public void getSubjectDetail(int id) {

        mModel.getSubjectDetail(id).compose(RxHttpReponseCompat.<SubjectDetail>compatResult())
                .subscribe(new ProgressSubcriber<SubjectDetail>(mContext, mView) {
                    @Override
                    public void onNext(SubjectDetail subjectDetail) {

                        mView.showSubjectDetail(subjectDetail);
                    }
                });
    }
}
