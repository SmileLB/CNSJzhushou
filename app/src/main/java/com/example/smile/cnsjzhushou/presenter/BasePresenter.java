package com.example.smile.cnsjzhushou.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.smile.cnsjzhushou.ui.BaseView;

/**
 * Created by LiBing
 * on 2017/6/19 0019
 * describe:
 */

public class BasePresenter<M, V extends BaseView> {

    protected M mModel;

    protected V mView;

    protected Context mContext;

    public BasePresenter(M model, V view) {
        mModel = model;
        mView = view;
        initContext();
    }

    private void initContext() {

        if (mView instanceof Fragment) {
            mContext = ((Fragment) mView).getActivity();
        } else {
            mContext = (Activity) mView;
        }
    }
}
