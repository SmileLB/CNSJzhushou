package com.example.smile.cnsjzhushou.di.module;


import com.example.smile.cnsjzhushou.data.MainModel;
import com.example.smile.cnsjzhushou.data.http.ApiService;
import com.example.smile.cnsjzhushou.di.FragmentScope;
import com.example.smile.cnsjzhushou.presenter.contract.MainContract;

import dagger.Module;
import dagger.Provides;


@Module
public class MainModule {

    private MainContract.MainView mView;

    public MainModule(MainContract.MainView view){
        this.mView = view;
    }

    @FragmentScope
    @Provides
    public MainContract.MainView provideView(){
        return  mView;
    }

    @FragmentScope
    @Provides
    public MainContract.IMainModel provideModel(ApiService apiService){
        return  new MainModel(apiService);
    }

}
