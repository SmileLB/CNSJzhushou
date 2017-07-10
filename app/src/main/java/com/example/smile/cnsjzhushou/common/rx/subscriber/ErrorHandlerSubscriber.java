package com.example.smile.cnsjzhushou.common.rx.subscriber;

import android.content.Context;

import com.example.smile.cnsjzhushou.common.exception.BaseException;
import com.example.smile.cnsjzhushou.common.rx.RxErrorHandler;

/**
 * Created by LiBing
 * on 2017/7/5 0005
 * describe:
 */

public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscriber<T>{

    protected RxErrorHandler mErrorHandler = null;

    protected  Context mContext;

    public ErrorHandlerSubscriber(Context context){
        this.mContext=context;
        mErrorHandler = new RxErrorHandler(context);
    }

    @Override
    public void onError(Throwable e) {
        BaseException baseException = mErrorHandler.handleError(e);
        mErrorHandler.showErrorMessage(baseException);

    }
}
