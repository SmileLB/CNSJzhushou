package com.example.smile.cnsjzhushou.common.util;

import android.content.Context;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;


public class PermissionUtil {

    public static Observable<Boolean> requestPermisson(Context activity, String permission){

        RxPermissions rxPermissions =  RxPermissions.getInstance(activity);

        return rxPermissions.request(permission);
    }
}
