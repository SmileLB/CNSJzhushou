package com.example.smile.cnsjzhushou.common.rx.subscriber;

import android.content.Context;

import com.example.smile.cnsjzhushou.common.util.ProgressDialogHandler;

/**
 * Created by LiBing
 * on 2017/7/8 0008
 * describe:
 */

public abstract class ProgressDialogSubscriber<T> extends ErrorHandlerSubscriber<T> implements ProgressDialogHandler.OnProgressCancelListener {

    private ProgressDialogHandler mProgressDialogHandler;

    public ProgressDialogSubscriber(Context context) {
        super(context);
        mProgressDialogHandler = new ProgressDialogHandler(context,true,this);
    }


    protected boolean isShowProgressDialog(){
        return true;
    }

    @Override
    public void onCancelProgress() {
        unsubscribe();
    }

    @Override
    public void onStart() {
        if(isShowProgressDialog()){
            this.mProgressDialogHandler.showProgressDialog();
        }
    }

    @Override
    public void onCompleted() {
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
