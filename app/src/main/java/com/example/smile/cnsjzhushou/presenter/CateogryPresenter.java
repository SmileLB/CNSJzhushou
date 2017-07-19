package com.example.smile.cnsjzhushou.presenter;

import com.example.smile.cnsjzhushou.bean.Category;
import com.example.smile.cnsjzhushou.common.rx.RxHttpReponseCompat;
import com.example.smile.cnsjzhushou.common.rx.subscriber.ProgressSubcriber;
import com.example.smile.cnsjzhushou.presenter.contract.CategoryContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by LiBing
 * on 2017/7/15 0015
 * describe:
 */

public class CateogryPresenter extends BasePresenter<CategoryContract.ICagegoryModel,CategoryContract.ICagegoryView> {

    @Inject
    public CateogryPresenter(CategoryContract.ICagegoryModel model, CategoryContract.ICagegoryView view) {
        super(model, view);
    }

    public void getAllCategory(){
        mModel.getCategories().compose(RxHttpReponseCompat.<List<Category>>compatResult())
        .subscribe(new ProgressSubcriber<List<Category>>(mContext,mView) {
            @Override
            public void onNext(List<Category> categories) {
                mView.showData(categories);
            }
        });
    }



}
