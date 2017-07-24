package com.example.smile.cnsjzhushou.di.module;


import com.example.smile.cnsjzhushou.data.SearchModel;
import com.example.smile.cnsjzhushou.data.http.ApiService;
import com.example.smile.cnsjzhushou.di.FragmentScope;
import com.example.smile.cnsjzhushou.presenter.contract.SearchContract;

import dagger.Module;
import dagger.Provides;


@Module
public class SearchModule {


    private SearchContract.SearchView mView;


    public SearchModule(SearchContract.SearchView view){

        this.mView = view;
    }

    @FragmentScope
    @Provides
    public SearchContract.ISearchModel provideModel(ApiService apiService){

        return  new SearchModel(apiService);
    }

    @FragmentScope
    @Provides
    public SearchContract.SearchView provideView(){

        return  mView;
    }
}
