package com.example.smile.cnsjzhushou.common.rx.subscriber;

import android.content.Context;

import com.example.smile.cnsjzhushou.common.exception.BaseException;
import com.example.smile.cnsjzhushou.ui.BaseView;

import io.reactivex.disposables.Disposable;

/**
 * Created by LiBing
 * on 2017/7/8 0008
 * describe:
 */

public abstract class ProgressSubcriber<T> extends ErrorHandlerSubscriber<T> {

    private BaseView mView;


    public ProgressSubcriber(Context context, BaseView view) {
        super(context);
        this.mView = view;

    }

    public boolean isShowProgress(){
        return true;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if(isShowProgress()){
            mView.showLoading();
        }
    }

    @Override
    public void onComplete() {
        mView.dismissLoading();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        BaseException baseException =  mErrorHandler.handleError(e);
        mView.showError(baseException.getDisplayMessage());
    }

}
