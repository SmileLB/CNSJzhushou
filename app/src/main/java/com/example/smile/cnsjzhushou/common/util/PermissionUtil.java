package com.example.smile.cnsjzhushou.common.util;

import android.content.Context;

import com.tbruyelle.rxpermissions2.RxPermissions;


public class PermissionUtil {

    public static io.reactivex.Observable<Boolean> requestPermisson(Context activity, String permission){

        RxPermissions rxPermissions =  RxPermissions.getInstance(activity);

        return rxPermissions.request(permission);
    }
}
