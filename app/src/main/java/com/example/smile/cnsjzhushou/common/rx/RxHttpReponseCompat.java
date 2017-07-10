package com.example.smile.cnsjzhushou.common.rx;

import com.example.smile.cnsjzhushou.bean.BaseBean;
import com.example.smile.cnsjzhushou.common.exception.ApiException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by LiBing
 * on 2017/7/4 0004
 * describe:
 */

public class RxHttpReponseCompat {

    public static  <T> Observable.Transformer<BaseBean<T>,T> compatResult(){


        return  new Observable.Transformer<BaseBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseBean<T>> baseBeanObservable) {



                return baseBeanObservable.flatMap(new Func1<BaseBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(final BaseBean<T> tBaseBean) {

                        if(tBaseBean.success()){


                            return Observable.create(new Observable.OnSubscribe<T>() {
                                @Override
                                public void call(Subscriber<? super T> subscriber) {

                                    try {
                                        subscriber.onNext(tBaseBean.getData());
                                        subscriber.onCompleted();
                                    }
                                    catch (Exception e){
                                        subscriber.onError(e);
                                    }


                                }
                            });


                        }
                        else {
                            return  Observable.error(new ApiException(tBaseBean.getStatus(),tBaseBean.getMessage()));
                        }

                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            }
        };
    }
}
