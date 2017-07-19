package com.example.smile.cnsjzhushou.presenter.contract;

import com.example.smile.cnsjzhushou.bean.BaseBean;
import com.example.smile.cnsjzhushou.bean.Category;
import com.example.smile.cnsjzhushou.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by LiBing
 * on 2017/7/15 0015
 * describe:
 */

public interface CategoryContract {

    interface ICagegoryModel{
        Observable<BaseBean<List<Category>>> getCategories();
    }

    interface ICagegoryView extends BaseView{
        void showData(List<Category> categories);
    }
}
