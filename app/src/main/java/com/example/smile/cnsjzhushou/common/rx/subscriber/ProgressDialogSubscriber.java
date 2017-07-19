package com.example.smile.cnsjzhushou.common.rx.subscriber;

import android.content.Context;

import com.example.smile.cnsjzhushou.common.util.ProgressDialogHandler;

import io.reactivex.disposables.Disposable;

/**
 * Created by LiBing
 * on 2017/7/8 0008
 * describe:
 */

public abstract class ProgressDialogSubscriber<T> extends ErrorHandlerSubscriber<T> implements ProgressDialogHandler.OnProgressCancelListener {

    private ProgressDialogHandler mProgressDialogHandler;

    private Disposable mDisposable;

    public ProgressDialogSubscriber(Context context) {
        super(context);
        mProgressDialogHandler = new ProgressDialogHandler(context,true,this);
    }


    protected boolean isShowProgressDialog(){
        return true;
    }

    @Override
    public void onCancelProgress() {
        mDisposable.dispose();
    }

    public void onSubscribe(Disposable d) {
        mDisposable = d;
        if(isShowProgressDialog()){
            this.mProgressDialogHandler.showProgressDialog();
        }
    }

    @Override
    public void onComplete() {
        if(isShowProgressDialog()){
            this.mProgressDialogHandler.dismissProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);

        if(isShowProgressDialog()){
            this.mProgressDialogHandler.dismissProgressDialog();
        }
    }
}
