package com.example.smile.cnsjzhushou.presenter.contract;

import com.example.smile.cnsjzhushou.bean.BaseBean;
import com.example.smile.cnsjzhushou.bean.LoginBean;
import com.example.smile.cnsjzhushou.ui.BaseView;

import io.reactivex.Observable;

/**
 * Created by LiBing
 * on 2017/7/14 0014
 * describe:
 */

public interface LoginContract {

    interface ILoginView extends BaseView{
        void checkPhoneError();
        void checkPhoneSuccess();
        void loginSuccess(LoginBean bean);
    }

    interface ILoginModel{
       Observable<BaseBean<LoginBean>> login(String phone, String pwd);
    }
}
