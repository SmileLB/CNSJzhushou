package com.example.smile.cnsjzhushou.data;

import com.example.smile.cnsjzhushou.bean.BaseBean;
import com.example.smile.cnsjzhushou.bean.LoginBean;
import com.example.smile.cnsjzhushou.bean.requestbean.LoginRequestBean;
import com.example.smile.cnsjzhushou.data.http.ApiService;
import com.example.smile.cnsjzhushou.presenter.contract.LoginContract;

import io.reactivex.Observable;


/**
 * Created by LiBing
 * on 2017/7/14 0014
 * describe:
 */

public class LoginModel implements LoginContract.ILoginModel{

    private ApiService mApiService;

    public LoginModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<LoginBean>> login(String phone, String pwd) {
        LoginRequestBean param = new LoginRequestBean();
        param.setEmail(phone);
        param.setPassword(pwd);

        return mApiService.login(param);
    }
}
