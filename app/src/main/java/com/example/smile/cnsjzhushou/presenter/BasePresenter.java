package com.example.smile.cnsjzhushou.presenter;

import com.example.smile.cnsjzhushou.ui.BaseView;

/**
 * Created by LiBing
 * on 2017/6/19 0019
 * describe:
 */

public class BasePresenter<M,V extends BaseView> {

    protected M mModel;

    protected V mView;

    public BasePresenter(M model, V view) {
        mModel = model;
        mView = view;
    }
}
