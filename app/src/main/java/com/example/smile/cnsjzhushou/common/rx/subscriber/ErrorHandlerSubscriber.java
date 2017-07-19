package com.example.smile.cnsjzhushou.common.rx.subscriber;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.smile.cnsjzhushou.common.exception.BaseException;
import com.example.smile.cnsjzhushou.common.rx.RxErrorHandler;
import com.example.smile.cnsjzhushou.ui.activity.LoginActivity;

import io.reactivex.disposables.Disposable;

/**
 * Created by LiBing
 * on 2017/7/5 0005
 * describe:
 */

public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscriber<T> {

    protected RxErrorHandler mErrorHandler = null;

    protected Context mContext;

    public ErrorHandlerSubscriber(Context context) {
        this.mContext = context;
        mErrorHandler = new RxErrorHandler(context);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        BaseException baseException = mErrorHandler.handleError(e);
        if (baseException == null) {
            e.printStackTrace();
            Log.d("ErrorHandlerSubscriber", e.getMessage());
        } else {
            mErrorHandler.showErrorMessage(baseException);
            if (baseException.getCode() == BaseException.ERROR_TOKEN) {
                toLogin();
            }
        }
    }

    private void toLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }
}
