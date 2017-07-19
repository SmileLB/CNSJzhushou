package com.example.smile.cnsjzhushou.di.module;

import com.example.smile.cnsjzhushou.data.LoginModel;
import com.example.smile.cnsjzhushou.data.http.ApiService;
import com.example.smile.cnsjzhushou.presenter.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LiBing
 * on 2017/7/14 0014
 * describe:
 */
@Module
public class LoginModule {

    private LoginContract.ILoginView mView;

    public LoginModule(LoginContract.ILoginView view){
        this.mView = view;
    }

    @Provides
    public LoginContract.ILoginView ProvidesLoginView(){
        return mView;
    }

    @Provides
    public LoginContract.ILoginModel ProvidesLoginModel(ApiService service){
        return new LoginModel(service);
    }

}
