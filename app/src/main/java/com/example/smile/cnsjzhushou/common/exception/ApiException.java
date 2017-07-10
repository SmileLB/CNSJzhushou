package com.example.smile.cnsjzhushou.common.exception;

/**
 * Created by LiBing
 * on 2017/7/4 0004
 * describe:
 */

public class ApiException extends BaseException {




    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
